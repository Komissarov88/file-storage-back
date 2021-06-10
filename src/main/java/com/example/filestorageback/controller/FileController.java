package com.example.filestorageback.controller;

import com.example.filestorageback.service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Controller
public class FileController {

    private FileStoreService fileStoreService;

    @Autowired
    public void setFileStoreService(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @PostMapping("/storefile")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("subtype") int subType
            ) throws IOException, NoSuchAlgorithmException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is Empty");
        }

        String hash = fileStoreService.storeFile(file.getBytes(), file.getOriginalFilename(), subType);
        return ResponseEntity.ok(hash);
    }

    @GetMapping("/getfile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("hash")UUID hash) throws IOException {
        byte[] array = fileStoreService.getFile(hash);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(array));
    }

    @GetMapping("/getfiles")
    public ResponseEntity<?> getFiles(@RequestParam("subtype")int subtype) throws IOException {
        return ResponseEntity.ok(fileStoreService.getMetaFiles(subtype));
    }
}
