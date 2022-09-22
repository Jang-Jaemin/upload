//  UploadFile =  업로드파일정보보관


package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName; // uploadFileName : 고객이 업로드한 파일명.
        this.storeFileName = storeFileName; // storeFileName : 서버 내부에서 관리하는 파일명.
    }
}

//  고객이업로드한파일명으로서버내부에파일을저장하면안된다.
//  왜냐하면서로다른고객이같은 파일이름을업로드하는경우기존파일이름과충돌이날수있다.
//  서버에서는저장할파일명이겹치지 않도록내부에서관리하는별도의파일명이필요하다.