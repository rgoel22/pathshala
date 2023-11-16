package com.pathshala.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity(name = "Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "topicId")
    private int topicId;

    // foreign key
//    @Column(name = "studyMaterialId")
//    private int studyMaterialId;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "syllabus")
    private String syllabus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return id == that.id && topicId == that.topicId && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(description, that.description) && Objects.equals(syllabus, that.syllabus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, topicId, code, description, syllabus);
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
                '}';
    }
}
