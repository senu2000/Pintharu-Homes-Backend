package com.pintharuHomes.Backend.dto;

import com.pintharuHomes.Backend.entity.Paint;
import com.pintharuHomes.Backend.entity.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDto {
    private Integer id;
    private PaintDto paintDto;
    private UserDto userDto;
}
