//  상품 저장용 폼

package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long itemId;
    private String itemName;
    private MultipartFile attachFile; // => 멀티파트는 모델어트리뷰트에서 사용할 수 있다.
    private List<MultipartFile> imageFiles; //  => 이미지를 다중 업로드 하기 위해 멀티파트파일을 사용했다.
}
