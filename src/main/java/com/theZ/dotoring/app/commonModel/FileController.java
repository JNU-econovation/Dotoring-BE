package com.theZ.dotoring.app.commonModel;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import com.theZ.dotoring.common.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileUtils fileUtils;
    private final ResourceLoader resourceLoader;

    @GetMapping("/files/{fileName}")
    public ResponseEntity<Resource> findFile(@PathVariable String fileName) throws IOException {

        String ext = fileUtils.extractExt(fileName); // 파일의 확장명 뽑기!
        MediaType mediaType = fileUtils.toMediaType(ext);

        Resource resource = resourceLoader.getResource("classpath:static/files/" + fileName);
        return ResponseEntity.ok().contentType(mediaType).body(resource);
    }


}
