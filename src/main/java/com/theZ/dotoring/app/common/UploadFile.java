package com.theZ.dotoring.app.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {

    private String originalFileName;
    private String storeFileName;

}
