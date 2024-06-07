package com.pintharuHomes.Backend.controller;

import com.pintharuHomes.Backend.dto.QuotationDto;
import com.pintharuHomes.Backend.service.QuotationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/quotation")
public class QuotationController {
    private QuotationService quotationService;

    //        Build add Quotation REST API
    @PostMapping("/createQuotation")
    public ResponseEntity<QuotationDto> createQuotation(@RequestBody QuotationDto quotationDto){
        QuotationDto quotation = quotationService.createQuotation(quotationDto);
        return new ResponseEntity<>(quotation, HttpStatus.CREATED);
    }

    //        Build get all Quotation REST API
    @GetMapping
    public ResponseEntity<List<QuotationDto>> getAllQuotations(){
        List<QuotationDto> allQuotations = quotationService.getAllQuotations();
        return ResponseEntity.ok(allQuotations);
    }

    //        Build get Quotation by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<QuotationDto> getQuotationById(@PathVariable("id") Integer id){
        QuotationDto quotationById = quotationService.getQuotationById(id);
        return ResponseEntity.ok(quotationById);
    }

    //        Build update Quotation REST API
    @PutMapping("{id}")
    public ResponseEntity<QuotationDto> updateQuotation(@PathVariable("id") Integer id,
                                                        @RequestBody QuotationDto quotationDto){
        QuotationDto updateQuotation = quotationService.updateQuotation(id, quotationDto);
        return ResponseEntity.ok(updateQuotation);
    }
}
