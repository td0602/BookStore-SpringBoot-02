package com.example.ebookapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String storeBookImage(MultipartFile file);
    String storeUserImage(MultipartFile file);
}
