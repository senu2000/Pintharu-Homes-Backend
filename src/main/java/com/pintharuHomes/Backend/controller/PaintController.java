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

    //        Build get all paint by name REST API
    @GetMapping("/name/{name}")
    public ResponseEntity<List<PaintDto>> getPaintsByName(@PathVariable("name") String name){
        List<PaintDto> paintDto = paintService.getPaintsByName(name);
        return new  ResponseEntity<>(paintDto, HttpStatus.OK);
    }

    //        Build get all paint by category REST API
    @GetMapping("/category/{category}")
    public ResponseEntity<List<PaintDto>> getPaintsByCategory(@PathVariable("category") String category){
        List<PaintDto> paintDto = paintService.getPaintsByCategory(category);
        return new  ResponseEntity<>(paintDto, HttpStatus.OK);
    }

    //        Build get all paint by brand REST API
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<PaintDto>> getPaintsByBrand(@PathVariable("brand") String brand){
        List<PaintDto> paintDto = paintService.getPaintsByBrand(brand);
        return new  ResponseEntity<>(paintDto, HttpStatus.OK);
    }

    //        Build get paint by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<PaintDto> getPaintById(@PathVariable("id") Integer id){
        PaintDto paintDto = paintService.getPaintById(id);
        return ResponseEntity.ok(paintDto);
    }

    //        Build update paint REST API
    @PutMapping("{id}")
    public ResponseEntity<?> updatePaint(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile file, @ModelAttribute PaintDto paintDto){
        PaintDto updatedPaint = paintService.updatePaint(id, file, paintDto);
        return new ResponseEntity<>(updatedPaint, HttpStatus.OK);
    }

    //        Build delete paint REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePaint(@PathVariable("id") Integer id){
        paintService.deletePaint(id);
        return ResponseEntity.ok("Paint Deleted Successfully");
    }
}
