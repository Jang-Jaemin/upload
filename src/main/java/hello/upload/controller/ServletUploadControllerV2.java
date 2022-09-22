//  서블릿이 제공하는 Part에 대해 알아 보고 실제파일도 서버에 업로드 해보자.
//  먼저 파일을 업로드를 하려면 실제 파일이 저장되는 경로가 필요하다.
//  해당 경로에 실제 폴더를 만들어두자.
//  그리고 다음에 만들어진 경로를 입력해두자.

//  file --->) application.properties
//  file.dir=파일 업로드 경로 설정(예): /Users/kimyounghan/study/file/

//  ☆ 주의 ☆
//  1. 꼭해당경로에실제폴더를미리만들어두자.
//  2. application.properties에서 설정할 때 마지막에/(슬래시)가 포함된 것에 주의하자.


package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);

        for (Part part : parts) {
            log.info("==== PART ====");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {}: {}", headerName, part.getHeader(headerName));
            }
            //편의 메서드
            //content-disposition; filename
            log.info("submittedFilename={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize()); //part body size

            //데이터 읽기
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body={}", body);

            //파일에 저장하기
            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("파일 저장 fullPath={}", fullPath);
                part.write(fullPath);
            }
        }

        return "upload-form";
    }
}

//  멀티파트형식은전송데이터를하나하나각각부분( Part)으로나누어전송한다.
//  parts에는이렇게 나누어진데이터가각각담긴다.
//  서블릿이제공하는Part는멀티파트형식을편리하게읽을수있는다양한메서드를제공한다.

//  Part 주요 메서드
//  part.getSubmittedFileName(): 클라이언트가 전달한 파일명.
//  part.getInputStream(): Part의 전송 데이터를 읽을수있다.
//  part.write(...): Part를 통해 전송된 데이터를 저장할수있다.

//  파일저장경로에가보면실제파일이저장된것을확인할수있다. 만약저장이되지않았다면파일저장 경로를다시확인하자.
//  참고
//  큰용량의파일을업로드를테스트할때는로그가너무많이남아서다음옵션을끄는것이좋다.
//  logging.level.org.apache.coyote.http11=debug

//  다음부분도파일의바이너리데이터를모두출력하므로끄는것이좋다.
//  log.info("body={}", body);