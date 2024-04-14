package com.example.hnm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.hnm.service.StorageService;

@CrossOrigin(origins = "*")
@RestController
public class StorageController {
    private final StorageService service;

    @Autowired
    public StorageController(StorageService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public void postMember(
        @RequestParam(value = "image") MultipartFile file) {

        service.uploadFile(file);
}

}
