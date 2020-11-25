package com.web.blog.controller.file;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.web.blog.service.ImageStorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {
    
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    ImageStorageService imageStorageService;

    @GetMapping("/image/{imageName:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {

        Resource resource = imageStorageService.loadFileAsResource(imageName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException e) {
            logger.info("Could not determine file type");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
