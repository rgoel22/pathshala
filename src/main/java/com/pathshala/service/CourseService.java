package com.pathshala.service;

import com.pathshala.dao.CourseEntity;
import com.pathshala.dao.UserCourseMappingEntity;
import com.pathshala.dto.CourseDTO;
import com.pathshala.exception.ErrorCodes;
import com.pathshala.exception.NotFoundException;
import com.pathshala.repository.CourseRepository;
import com.pathshala.exception.GenericExceptions;
import com.pathshala.repository.UserCourseMappingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private UserCourseMappingRepository userCourseMappingRepository;
    private ModelMapper modelMapper;
    public List<CourseDTO> findAll(){
        List<CourseEntity> courseEntities = courseRepository.findAll();
        return courseEntities.stream().map(courseEntity -> modelMapper.map(courseEntity, CourseDTO.class))
                .collect(Collectors.toList());
    }


    public CourseDTO saveOrUpdate(CourseDTO courseDTO) {
        CourseEntity course = new CourseEntity();
        if (courseDTO.getId() != null){
            course = findEntityById(courseDTO.getId());
        }
        modelMapper.map(courseDTO, course);
        CourseEntity savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    public CourseDTO findById(Long id) {
        CourseEntity course = this.findEntityById(id);
        return modelMapper.map(course, CourseDTO.class);
    }

    public String enrollUserInCourse(String userId, Long courseId){
        Optional<UserCourseMappingEntity> optionalUserCourseMapping =
                userCourseMappingRepository.findByUserIdAndCourseId(userId, courseId);
        if(optionalUserCourseMapping.isPresent()){
            throw new GenericExceptions(ErrorCodes.USER_COURSE_PRESENT, "User already enrolled in Course");
        }
        return "Successfully enrolled";
    }

    private CourseEntity findEntityById(Long id) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if (!course.isPresent()){
            throw new NotFoundException(ErrorCodes.COURSE_NOT_FOUND, "Course not found!");
        }
        return course.get();
    }
}
