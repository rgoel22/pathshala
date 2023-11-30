package com.pathshala.controller;

import com.pathshala.dto.SubmissionDTO;
import com.pathshala.service.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/submission")
public class SubmissionController {

    private SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionDTO> saveOrUpdateSubmission(@RequestBody @Valid SubmissionDTO submissionDTO){
        SubmissionDTO submissionResponse = submissionService.saveOrUpdateSubmission(submissionDTO);
        return ResponseEntity.ok().body(submissionResponse);
    }

    @PostMapping
    public ResponseEntity<SubmissionDTO> updateSubmissionGrade(@RequestBody @Valid SubmissionDTO submissionDTO){
        SubmissionDTO submissionResponse = submissionService.updateGrade(submissionDTO);
        return ResponseEntity.ok().body(submissionResponse);
    }



}
