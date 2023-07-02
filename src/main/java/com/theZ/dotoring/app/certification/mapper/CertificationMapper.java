package com.theZ.dotoring.app.certification.mapper;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.commonModel.UploadFile;
import com.theZ.dotoring.enums.DeleteStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CertificationMapper {

    // entity로 변환 : to
    public static List<Certification> to(List<UploadFile> uploadFiles){

        if(uploadFiles == null && uploadFiles.size() >0){
            return null;
        }

        return IntStream.range(0, uploadFiles.size())
                .mapToObj(i -> Certification.builder()
                        .originalFileName(uploadFiles.get(i).getOriginalFileName())
                        .saveFileName(uploadFiles.get(i).getStoreFileName())
                        .delete_yn(DeleteStatus.NO)
                        .build())
                .collect(Collectors.toList());
    }

}
