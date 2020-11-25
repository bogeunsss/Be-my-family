package com.web.blog.service;

import com.web.blog.config.FileStorageConfig;
import com.web.blog.exception.FileNotFoundException;
import com.web.blog.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageStorageService {

    private final Path imageStorageLocation;

    @Autowired
    public ImageStorageService(FileStorageConfig fileStorageConfig) {
        this.imageStorageLocation = Paths.get(fileStorageConfig.getUploadDir()+"/image").toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.imageStorageLocation);
        }catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded file will be stored.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.imageStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.imageStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            }else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        }catch (MalformedURLException e) {
            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }
}
