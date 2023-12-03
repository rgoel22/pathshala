package com.pathshala.service;

import com.pathshala.config.PropertyConfig;
import com.pathshala.exception.BaseRuntimeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class FileService {

    private PropertyConfig config;
    public String uploadFile(MultipartFile file) {
        String uploadPath = config.getPropertyByName("filePath");
        try{
            Path uploadFile = Paths.get(uploadPath);
            if(!Files.exists(uploadFile)){
                Files.createDirectory(uploadFile);
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath + fileName);
            Files.copy(file.getInputStream(), filePath);
//            FileCopyUtils.copy(file.getBytes(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch(Exception e) {
            throw new BaseRuntimeException("","");
        }
    }
}
