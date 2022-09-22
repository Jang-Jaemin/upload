//  서블릿과파일업로드 1

package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request); // request.getParts(): multipart/form-data 전송방식에서각각나누어진부분을받아서 확인할 수 있다.

        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);

        return "upload-form";
    }
}


//  참고
//  spring.servlet.multipart.enabled 옵션을켜면스프링의DispatcherServlet에서멀티파트 리졸버( MultipartResolver)를실행한다.
//  멀티파트리졸버는멀티파트요청인경우서블릿컨테이너가전달하는일반적인HttpServletRequest를 MultipartHttpServletRequest로변환해서반환한다.
//  MultipartHttpServletRequest는HttpServletRequest의자식인터페이스이고, 멀티파트와관련된 추가기능을제공한다.

//  스프링이제공하는기본멀티파트리졸버는 MultipartHttpServletRequest 인터페이스를 구현한 StandardMultipartHttpServletRequest를 반환한다.
//  이제컨트롤러에서HttpServletRequest 대신에 MultipartHttpServletRequest를 주입받을수 있는데,
//  이것을 사용하면 멀티파트와 관련된 여러 가지 처리를 편리하게할 수 있다.
//  그런데 이후 설명할 MultipartFile 이라는것을 사용하는것이 더 편하기 때문에 MultipartHttpServletRequest 를 잘사용하지는 않는다.
