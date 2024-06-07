package com.pintharuHomes.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Integer id;
    private String name;
    private String description;
    private MultipartFile imageFile1;
    private MultipartFile imageFile2;
    private MultipartFile imageFile3;
    private MultipartFile imageFile4;

    private byte[] imageData1;
    private byte[] imageData2;
    private byte[] imageData3;
    private byte[] imageData4;

    public ProjectDto(Integer id, String name, String description, MultipartFile imageFile1, MultipartFile imageFile2, MultipartFile imageFile3, MultipartFile imageFile4) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageFile1 = imageFile1;
        this.imageFile2 = imageFile2;
        this.imageFile3 = imageFile3;
        this.imageFile4 = imageFile4;
    }
}
