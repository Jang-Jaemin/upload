//  item - 상품 도메인


//  요구사항
//  : 상품을 관리
//  : 상품 이름, 첨부파일 하나, 이미지 파일 여러개
//  : 첨부파일을 업로드 다운로드 할 수 있다.
//  : 업로드한 이미지를 웹브라우저에서 확인할 수 있다.


package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}
