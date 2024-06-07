package com.pintharuHomes.Backend.repository;

import com.pintharuHomes.Backend.entity.Paint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaintRepository extends JpaRepository<Paint, Integer> {

    @Query("SELECT p FROM Paint p WHERE p.name LIKE %:name%")
    List<Paint> findPaintsByName(String name);

    List<Paint> findPaintsByCategory(String category);

    List<Paint> findPaintsByBrand(String brand);
}
