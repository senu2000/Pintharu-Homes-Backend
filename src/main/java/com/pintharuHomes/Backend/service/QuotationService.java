package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.QuotationDto;

import java.util.List;

public interface QuotationService {
    QuotationDto createQuotation(QuotationDto quotationDto);

    List<QuotationDto> getAllQuotations();

    QuotationDto getQuotationById(Integer id);

    QuotationDto updateQuotation(Integer id, QuotationDto quotationDto);
}
