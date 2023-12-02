package com.pathshala.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyMaterialDTO {
    private int id;
    private String name;
    private int courseId;
    private int topicId;
    private String description;
    private String filePath;
}
