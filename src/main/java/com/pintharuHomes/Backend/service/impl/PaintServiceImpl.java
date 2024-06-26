package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.dto.PaintDto;
import com.pintharuHomes.Backend.entity.Cart;
import com.pintharuHomes.Backend.entity.Paint;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.mapper.PaintMapper;
import com.pintharuHomes.Backend.repository.CartRepository;
import com.pintharuHomes.Backend.repository.PaintRepository;
import com.pintharuHomes.Backend.service.PaintService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaintServiceImpl implements PaintService {

    private PaintRepository paintRepository;

    private CartRepository cartRepository;

    private final String FOLDER_PATH = "D:/sem6/Project II/Pintharu-Homes-Backend/src/main/java/com/pintharuHomes/Backend/upload_img/";

//    testing file with given name
    public String uploadImageToFileSystem(MultipartFile file, String name) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        Paint fileData=paintRepository.save(Paint.builder()
                .name(name)
                .image(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    @Override
    public PaintDto createPaint(MultipartFile file, PaintDto paintDto) {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        Paint savedPaint =paintRepository.save(Paint.builder()
                .name(paintDto.getName())
                .image(filePath)
                .brand(paintDto.getBrand())
                .quantity(paintDto.getQuantity())
                .category(paintDto.getCategory())
                .price(paintDto.getPrice())
                .volume(paintDto.getVolume()).
                build());
        try {
            file.transferTo(new File(filePath));
        }catch (Exception e){
            System.out.println(e);
        }

        return PaintMapper.mapToPaintDto(savedPaint);
    }

    @Override
    public List<PaintDto> getAllPaints() {
        List<Paint> paints = paintRepository.findAll();
        return paints.stream().map(
                paint -> {
                    PaintDto paintDto = PaintMapper.mapToPaintDto(paint);
                    try {
                        paintDto.setImageData(getImageData(paint.getImage()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return paintDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PaintDto getPaintById(Integer id) {
        Paint paint = paintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No paint exist for ID:" + id));
        PaintDto paintDto = PaintMapper.mapToPaintDto(paint);
        try {
            paintDto.setImageData(getImageData(paint.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return paintDto;
    }

    @Override
    public List<PaintDto> getPaintsByName(String name) {
        List<Paint> paints = paintRepository.findPaintsByName(name);
        return paints.stream().map(
                paint -> {
                    PaintDto paintDto = PaintMapper.mapToPaintDto(paint);
                    try {
                        paintDto.setImageData(getImageData(paint.getImage()));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return paintDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<PaintDto> getPaintsByCategory(String category) {
        List<Paint> paints = paintRepository.findPaintsByCategory(category);
        return paints.stream().map(
                paint -> {
                    PaintDto paintDto = PaintMapper.mapToPaintDto(paint);
                    try {
                        paintDto.setImageData(getImageData(paint.getImage()));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return paintDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<PaintDto> getPaintsByBrand(String brand) {
        List<Paint> paints = paintRepository.findPaintsByBrand(brand);
        return paints.stream().map(
                paint -> {
                    PaintDto paintDto = PaintMapper.mapToPaintDto(paint);
                    try {
                        paintDto.setImageData(getImageData(paint.getImage()));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return paintDto;
                }).collect(Collectors.toList());
    }

    @Override
    public PaintDto updatePaint(Integer id, MultipartFile file, PaintDto paintDto) {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        Paint paint = paintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No paint exist for ID:" + id));
        paint.setName(paintDto.getName());
        paint.setImage(filePath);
        paint.setBrand(paintDto.getBrand());
        paint.setQuantity(paintDto.getQuantity());
        paint.setCategory(paintDto.getCategory());
        paint.setPrice(paintDto.getPrice());
        paint.setVolume(paintDto.getVolume());

        try {
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            System.out.println(e);
        }
        Paint updatedPaint = paintRepository.save(paint);
        return PaintMapper.mapToPaintDto(updatedPaint);
    }

    @Override
    public void deletePaint(Integer id) {
        Paint paint = paintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No paint exist for ID:" + id));
        String filePath = paint.getImage();
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }

        List<Cart> cartPaints = cartRepository.findByPaint(paint);
        if (cartPaints != null && !cartPaints.isEmpty()) {
            for (Cart cart : cartPaints) {
                cart.setPaint(null);
                cartRepository.delete(cart);
            }
        }

        paintRepository.deleteById(id);
    }

    //change private to public static
    public static byte[] getImageData(String filePath) throws IOException{
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        try (FileInputStream inputStream = new FileInputStream(file);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toByteArray();
        }
    }


}
