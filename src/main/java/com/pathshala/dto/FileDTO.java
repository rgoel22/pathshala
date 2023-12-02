package com.pathshala.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private MultipartFile file;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDTO fileDto = (FileDTO) o;
        return Objects.equals(file, fileDto.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "file=" + file +
                '}';
    }
}
