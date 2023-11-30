package com.pathshala.repository;

import com.pathshala.dao.UserCourseMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCourseMappingRepository extends JpaRepository<UserCourseMappingEntity, Long> {

    Optional<UserCourseMappingEntity> findByUserIdAndCourseId(String userId, Long courseId);

}
