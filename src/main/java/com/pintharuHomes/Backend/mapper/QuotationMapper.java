package com.pintharuHomes.Backend.mapper;

import com.pintharuHomes.Backend.dto.QuotationDto;
import com.pintharuHomes.Backend.entity.Quotation;

public class QuotationMapper {
    public static QuotationDto mapToQuotationDto(Quotation quotation){
        return new QuotationDto(
                quotation.getId(),
                quotation.getName(),
                quotation.getUnit_price()
        );
    }

    public static Quotation mapToQuotation(QuotationDto quotationDto){
        return new Quotation(
                quotationDto.getId(),
                quotationDto.getName(),
                quotationDto.getUnit_price()
        );
    }
}
