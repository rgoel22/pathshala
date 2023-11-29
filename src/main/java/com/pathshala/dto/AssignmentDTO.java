package com.pathshala.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {
    private Long id;
    private String name;
    private String description;
    private Timestamp deadline;
    private Float points;
    private String filePath;
    private Long topicId;
}
