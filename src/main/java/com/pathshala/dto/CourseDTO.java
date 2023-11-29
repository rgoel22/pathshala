package com.pathshala.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    @NotNull
    private String name;
    private Long topicId;
    @NotNull
    private String code;
    private String description;
    private String syllabus;
    @JsonProperty("instructorId")
    private Long userId;
}
