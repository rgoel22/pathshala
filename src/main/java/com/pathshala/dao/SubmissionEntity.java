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

@Entity(name = "Submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionEntity extends MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    // foreign key assignmentId

    @Column(name = "uploadedDocumentPath")
    private String uploadedDocumentPath;

    @Column(name = "gradeReceived")
    private float gradeReceived;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionEntity that = (SubmissionEntity) o;
        return id == that.id && Float.compare(that.gradeReceived, gradeReceived) == 0 && Objects.equals(name, that.name) && Objects.equals(uploadedDocumentPath, that.uploadedDocumentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, uploadedDocumentPath, gradeReceived);
    }

    @Override
    public String toString() {
        return "SubmissionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uploadedDocumentPath='" + uploadedDocumentPath + '\'' +
                ", gradeReceived=" + gradeReceived +
                '}';
    }
}
