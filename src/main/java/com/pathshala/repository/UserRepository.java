package com.pathshala.repository;

import com.pathshala.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByUserIdAndPassword(String userId, String password);
}

  