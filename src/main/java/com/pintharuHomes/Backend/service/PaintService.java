package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.PaintDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaintService {

    public PaintDto createPaint(MultipartFile file, PaintDto paintDto);

    List<PaintDto> getAllPaints();

    Integer getPaintCount();

    PaintDto getPaintById(Integer id);

    List<PaintDto> getPaintsByName(String name);

    List<PaintDto> getPaintsByCategory(String category);

    List<PaintDto> getPaintsByBrand(String brand);

    PaintDto updatePaint(Integer id, MultipartFile file, PaintDto paintDto);

    void deletePaint(Integer id);

}
