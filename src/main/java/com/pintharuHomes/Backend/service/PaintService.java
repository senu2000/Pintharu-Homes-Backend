package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.PaintDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaintService {

    public PaintDto createPaint(MultipartFile file, PaintDto paintDto);

    List<PaintDto> getAllPaints();

}
