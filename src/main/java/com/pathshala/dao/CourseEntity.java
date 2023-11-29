package com.pathshala.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity extends MetaData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long topicId;
    private String code;
    private String description;
    private String syllabus;
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity course = (CourseEntity) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(topicId, course.topicId) && Objects.equals(code, course.code) && Objects.equals(description, course.description) && Objects.equals(syllabus, course.syllabus) && Objects.equals(userId, course.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, topicId, code, description, syllabus, userId);
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topicId=" + topicId +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", syllabus='" + syllabus + '\'' +
                ", userId=" + userId +
                '}';
    }

}
