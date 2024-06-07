package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.dto.QuotationDto;
import com.pintharuHomes.Backend.entity.Quotation;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.mapper.QuotationMapper;
import com.pintharuHomes.Backend.repository.QuotationRepository;
import com.pintharuHomes.Backend.service.QuotationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuotationServiceImpl implements QuotationService {

    private QuotationRepository quotationRepository;
    @Override
    public QuotationDto createQuotation(QuotationDto quotationDto) {
        Quotation quotation = QuotationMapper.mapToQuotation(quotationDto);
        Quotation savedQuotation = quotationRepository.save(quotation);
        return QuotationMapper.mapToQuotationDto(savedQuotation);
    }

    @Override
    public List<QuotationDto> getAllQuotations() {
        List<Quotation> allQuotations = quotationRepository.findAll();
        return allQuotations.stream().map((quotation) -> QuotationMapper.mapToQuotationDto(quotation))
                .collect(Collectors.toList());
    }

    @Override
    public QuotationDto getQuotationById(Integer id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No quotation found"));
        return QuotationMapper.mapToQuotationDto(quotation);
    }

    @Override
    public QuotationDto updateQuotation(Integer id, QuotationDto quotationDto) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No quotation found"));
        quotation.setName(quotationDto.getName());
        quotation.setUnit_price(quotationDto.getUnit_price());
        Quotation savedQuotation = quotationRepository.save(quotation);
        return QuotationMapper.mapToQuotationDto(savedQuotation);
    }
}
