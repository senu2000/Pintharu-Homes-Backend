package com.pintharuHomes.Backend.repository;

import com.pintharuHomes.Backend.entity.Cart;
import com.pintharuHomes.Backend.entity.Paint;
import com.pintharuHomes.Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(User user);

    List<Cart> findByPaint(Paint paint);

}
