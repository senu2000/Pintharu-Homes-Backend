package com.pintharuHomes.Backend.repository;

import com.pintharuHomes.Backend.entity.OrderDetail;
import com.pintharuHomes.Backend.entity.Paint;
import com.pintharuHomes.Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByUser(User user);

    List<OrderDetail> findByPaint(Paint paint);
}
