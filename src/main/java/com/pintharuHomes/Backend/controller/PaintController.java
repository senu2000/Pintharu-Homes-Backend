package com.pintharuHomes.Backend.controller;

import com.pintharuHomes.Backend.dto.PaintDto;
import com.pintharuHomes.Backend.service.PaintService;
import com.pintharuHomes.Backend.service.impl.PaintServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/paint")
public class PaintController {

    private PaintService paintService;

    private PaintServiceImpl paintServiceImpl;

    //        Build test add image with name REST API
    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file,
                                                     @RequestParam("name") String name) throws IOException {
        String uploadImage = paintServiceImpl.uploadImageToFileSystem(file, name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    //        Build add paint REST API
    @PostMapping("/createpaint")
    public ResponseEntity<?> createPaint(@RequestParam("image") MultipartFile file, @ModelAttribute PaintDto paintDto){
        PaintDto savedPaint = paintService.createPaint(file, paintDto);
        return new ResponseEntity<>(savedPaint, HttpStatus.OK);
    }

    //        Build get all paint REST API
    @GetMapping
    public ResponseEntity<List<PaintDto>> getAllPaints(){
        List<PaintDto> paintDto = paintService.getAllPaints();
        return new  ResponseEntity<>(paintDto, HttpStatus.OK);
    }

}
