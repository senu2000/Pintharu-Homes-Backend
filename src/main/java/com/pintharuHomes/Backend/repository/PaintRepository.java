package com.pintharuHomes.Backend.repository;

import com.pintharuHomes.Backend.entity.Paint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintRepository extends JpaRepository<Paint, Integer> {
}
