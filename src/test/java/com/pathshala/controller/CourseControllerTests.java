package com.pathshala.controller;

import com.pathshala.dto.CourseDTO;
import com.pathshala.security.TokenService;
import com.pathshala.service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Disabled
public class CourseControllerTests {

    private CourseController courseController;

    @Mock
    private CourseService courseService;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setup() {
        courseController = new CourseController(courseService, tokenService);
    }

    @AfterEach
    void tearDown() {
        courseController = null;
    }

    @Test
    void testFindAll() {
        Mockito.when(courseService.findAll()).thenReturn(testCourseDTOList());
        ResponseEntity<List<CourseDTO>> testCourseList = courseController.findAll();
        Assertions.assertTrue(testCourseList.hasBody());
        Assertions.assertEquals(testCourseList.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(testCourseList.getBody().size(), 2);
    }

    @Test
    void testSaveOrUpdateCourse() {
        Mockito.when(courseService.saveOrUpdate(Mockito.any())).thenReturn(testCourseDTO());
        ResponseEntity<CourseDTO> testSavedCourse = courseController.saveOrUpdate(testCourseDTO());
        Assertions.assertTrue(testSavedCourse.hasBody());
        Assertions.assertEquals(testSavedCourse.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(testSavedCourse.getBody().getId(), 1L);
    }

    @Test
    void testFindById() {
        Mockito.when(courseService.findById(Mockito.any())).thenReturn(testCourseDTO());
        ResponseEntity<CourseDTO> testResponse = courseController.findById(1L);
        Assertions.assertTrue(testResponse.hasBody());
        Assertions.assertEquals(testResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(testResponse.getBody().getId(), 1L);
    }

    @Test
    void testEnrollUserInCourse() {
        Mockito.when(courseService.enrollUserInCourse(Mockito.any(), Mockito.anyLong())).thenReturn("Successfully Enrolled");
        ResponseEntity<String> testResponse = courseController.enrollUserInCourse("testUser", 1L);
        Assertions.assertTrue(testResponse.hasBody());
        Assertions.assertEquals(testResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(testResponse.getBody(), "Successfully Enrolled");
    }

    private List<CourseDTO> testCourseDTOList() {

        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseDTOList.add(new CourseDTO(1L, "TestCourse", 1L, "testCode", "testDesc", "testSyllabus", 1L));
        courseDTOList.add(new CourseDTO(2L, "TestCourse2", 2L, "testCode2", "testDesc2", "testSyllabus2", 2L));
        return courseDTOList;

    }

    private CourseDTO testCourseDTO() {
        return new CourseDTO(1L, "TestCourse", 1L, "testCode", "testDesc", "testSyllabus", 1L);
    }
}

