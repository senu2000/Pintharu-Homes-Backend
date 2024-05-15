package com.pintharuHomes.Backend.mapper;

import com.pintharuHomes.Backend.dto.PaintDto;
import com.pintharuHomes.Backend.entity.Paint;

public class PaintMapper {

    public static PaintDto mapToPaintDto(Paint paint){
        return new PaintDto(
                paint.getId(),
                paint.getName(),
                null,
//                paint.getImage(),
                paint.getBrand(),
                paint.getQuantity(),
                paint.getCategory(),
                paint.getPrice(),
                paint.getVolume()
        );
    }

    public static Paint mapToPaint(PaintDto paintDto){
        return new Paint(
                paintDto.getId(),
                paintDto.getName(),
                null,
//                paintDto.getImage(),
                paintDto.getBrand(),
                paintDto.getQuantity(),
                paintDto.getCategory(),
                paintDto.getPrice(),
                paintDto.getVolume()
        );
    }
}
