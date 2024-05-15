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
public class PaintDto {
    private Integer id;
    private String name;
//    private String image;
    private MultipartFile imageFile;
    private String brand;
    private Integer quantity;
    private String category;
    private Integer price;
    private String volume;

    private byte[] imageData;

    // Modify the constructor to accept null for imageFile
    public PaintDto(Integer id, String name, MultipartFile imageFile, String brand, Integer quantity, String category, Integer price, String volume) {
        this.id = id;
        this.name = name;
        this.imageFile = imageFile;
        this.brand = brand;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.volume = volume;
    }
}
