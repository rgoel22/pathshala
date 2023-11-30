package com.pathshala.controller;

import com.pathshala.dto.CourseDTO;
import com.pathshala.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080/")
    public ResponseEntity<List<CourseDTO>> findAll(){
        List<CourseDTO> courseDTOList = courseService.findAll();
        return ResponseEntity.ok().body(courseDTOList);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> saveOrUpdate(@RequestBody @Valid CourseDTO course) {
        CourseDTO savedCourse = courseService.saveOrUpdate(course);
        return ResponseEntity.ok().body(savedCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable @NotNull Long id){
        CourseDTO courseDTO = courseService.findById(id);
        return ResponseEntity.ok().body(courseDTO);
    }

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<String> enrollUserInCourse(@PathVariable @NotNull String userId, @PathVariable @NotNull Long courseId){
        String response = courseService.enrollUserInCourse(userId, courseId);
        return ResponseEntity.ok().body(response);
    }

}
