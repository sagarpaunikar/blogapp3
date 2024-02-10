package com.blogapp3.blogapp3.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public String uploadImage(String path, MultipartFile file) throws IOException;
}
