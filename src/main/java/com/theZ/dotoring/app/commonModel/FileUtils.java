package com.theZ.dotoring.app.commonModel;

import com.theZ.dotoring.common.MessageCode;
import com.theZ.dotoring.exception.ExtentionNotAllowedException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileUtils {


    private final String rootPath = System.getProperty("user.dir");

    private final String fileDir = rootPath + "/src/main/resources/static/";

    private final List<String> fileExts = List.of("pdf","img");

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                if(multipartFile.getSize() > 10L){
                    throw new MaxUploadSizeExceededException(10);
                }
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException,IllegalStateException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }


    // 파일명 뽑기 -
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // 확장명 뽑기
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        for (String fileExt: fileExts) {
            if(!Objects.equals(fileExt,ext)){
                throw  new ExtentionNotAllowedException(MessageCode.NOT_ALLOWED_FILE_EXT);
            }
        }
        return ext;
    }
}
