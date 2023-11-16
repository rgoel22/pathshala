package com.pathshala.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "Assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEntity extends MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deadline;

    @Column(name = "points")
    private float points;

    @Column(name = "uploadedDocumentPath")
    private String uploadedDocumentPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentEntity that = (AssignmentEntity) o;
        return id == that.id && Float.compare(that.points, points) == 0 && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(deadline, that.deadline) && Objects.equals(uploadedDocumentPath, that.uploadedDocumentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, deadline, points, uploadedDocumentPath);
    }

    @Override
    public String toString() {
        return "AssignmentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", points=" + points +
                ", uploadedDocumentPath='" + uploadedDocumentPath + '\'' +
                '}';
    }
}
