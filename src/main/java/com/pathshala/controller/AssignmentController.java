package com.pathshala.controller;

import com.pathshala.dto.AssignmentDTO;
import com.pathshala.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assignment")
@AllArgsConstructor
public class AssignmentController {
    private AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> findAll(){
        List<AssignmentDTO> courseDTOList = assignmentService.findAll();
        return ResponseEntity.ok().body(courseDTOList);
    }

    @PostMapping
    public ResponseEntity<AssignmentDTO> saveOrUpdate(@RequestBody @Valid AssignmentDTO course) {
        AssignmentDTO savedCourse = assignmentService.saveOrUpdate(course);
        return ResponseEntity.ok().body(savedCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> findById(@PathVariable Long id){
        AssignmentDTO courseDTO = assignmentService.findById(id);
        return ResponseEntity.ok().body(courseDTO);
    }

    @GetMapping("/topic")
    public ResponseEntity<AssignmentDTO> findByTopicId(@RequestParam Long topicId){
        AssignmentDTO courseDTO = assignmentService.findByTopicId(topicId);
        return ResponseEntity.ok().body(courseDTO);
    }

}
