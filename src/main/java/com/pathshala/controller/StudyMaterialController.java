package com.pathshala.controller;

import com.pathshala.dto.StudyMaterialDTO;
import com.pathshala.service.StudyMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/studyMaterial")
public class StudyMaterialController {

    private StudyMaterialService studyMaterialService;

    @PostMapping
    public ResponseEntity<StudyMaterialDTO> saveOrUpdateStudyMaterial(@RequestBody StudyMaterialDTO studyMaterialDTO){
        StudyMaterialDTO savedStudyMaterial = studyMaterialService.saveOrUpdateStudyMaterial(studyMaterialDTO);
        return ResponseEntity.ok().body(savedStudyMaterial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyMaterialDTO> findById(@PathVariable Long id){
       StudyMaterialDTO studyMaterialDTO = studyMaterialService.findById(id);
       return ResponseEntity.ok().body(studyMaterialDTO);
    }

}
