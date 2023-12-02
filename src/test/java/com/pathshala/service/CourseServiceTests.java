package com.pathshala.service;

import com.pathshala.dao.CourseEntity;
import com.pathshala.dao.UserCourseMappingEntity;
import com.pathshala.dto.CourseDTO;
import com.pathshala.repository.CourseRepository;
import com.pathshala.repository.UserCourseMappingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Disabled
public class CourseServiceTests {

    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserCourseMappingRepository userCourseMappingRepository;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setup(){
        courseService = new CourseService(courseRepository, userCourseMappingRepository, modelMapper);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    void testFindAll(){
        Mockito.when(courseRepository.findAll()).thenReturn(courseEntityList());
        List<CourseDTO> courseDTOList = courseService.findAll();
        Assertions.assertEquals(courseDTOList.size(), 2);
        Assertions.assertEquals(courseDTOList.get(0).getId(), 1L);
    }

    @Test
    void testSaveOrUpdate(){
        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(courseEntity()));
        Mockito.when(courseRepository.save(Mockito.any())).thenReturn(courseEntity());
        CourseDTO savedCourse = courseService.saveOrUpdate(courseDTO());
        Assertions.assertEquals(savedCourse.getId(), 1L);
    }

    @Test
    void testFindById(){
        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(courseEntity()));
        CourseDTO course = courseService.findById(1L);
        Assertions.assertEquals(course.getId(), 1L);
    }

    @Test
    void enrollUserInClass(){
        String response = courseService.enrollUserInCourse("test", 1L);
        Assertions.assertEquals(response, "Successfully enrolled");
    }

    private List<CourseEntity> courseEntityList(){
        List<CourseEntity> courseEntities = new ArrayList<>();
        courseEntities.add(new CourseEntity(1L, "test", "test101", "desc", "testSyllabus", 1L));
        courseEntities.add(new CourseEntity(2L, "test2", "test102", "desc2", "testSyllabus2", 2L));
        return courseEntities;
    }

    private CourseEntity courseEntity(){
        return new CourseEntity(1L, "test", "test101", "desc", "testSyllabus", 1L);
    }

    private CourseDTO courseDTO(){
        return new CourseDTO(1L, "test", 1L, "test101", "testDesc", "testSyll", 1L);
    }


}
