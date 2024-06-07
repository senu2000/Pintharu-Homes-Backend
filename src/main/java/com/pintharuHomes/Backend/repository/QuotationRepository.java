package com.pintharuHomes.Backend.repository;

import com.pintharuHomes.Backend.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
}
