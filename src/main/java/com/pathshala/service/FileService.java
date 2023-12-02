package com.pathshala.service;

import com.pathshala.config.PropertyConfig;
import com.pathshala.exception.BaseRuntimeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@AllArgsConstructor
public class FileService {

    private PropertyConfig config;
    public String uploadFile(MultipartFile file) {
        String uploadPath = config.getPropertyByName("filePath");
        try{
            File directory = new File(uploadPath);
            if(!directory.exists()){
                directory.mkdir();
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Path.of(uploadPath, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch(Exception e) {
            throw new BaseRuntimeException("","");
        }
    }
}
