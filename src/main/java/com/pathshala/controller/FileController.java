package com.pathshala.controller;

import com.pathshala.dto.FileDTO;
import com.pathshala.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@Valid @RequestBody FileDTO fileDto){
        MultipartFile file = fileDto.getFile();
        String filePath = fileService.uploadFile(file);
        return ResponseEntity.ok().body(filePath);
    }
}
