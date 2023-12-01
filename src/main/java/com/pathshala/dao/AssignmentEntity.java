package com.pathshala.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private Long id;

    private String name;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deadline;
    private Float points;
    private String filePath;
    private Long topicId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentEntity that = (AssignmentEntity) o;
        return Float.compare(points, that.points) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(deadline, that.deadline) && Objects.equals(filePath, that.filePath) && Objects.equals(topicId, that.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, deadline, points, filePath, topicId);
    }

    @Override
    public String toString() {
        return "AssignmentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", points=" + points +
                ", filePath='" + filePath + '\'' +
                ", topicId=" + topicId +
                '}';
    }
}
