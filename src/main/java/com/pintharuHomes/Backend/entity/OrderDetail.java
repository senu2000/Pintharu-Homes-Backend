package com.pintharuHomes.Backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "order_fullname")
    private String orderFullName;

    @Column(name = "order_address")
    private String orderFullAddress;

    @Column(name = "order_contactNo")
    private String orderContactNo;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_amount")
    private Integer orderAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paint_id", nullable = false)
    private Paint paint;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public OrderDetail(String orderFullName, String orderFullAddress, String orderContactNo, String orderStatus, Integer orderAmount, Paint paint, User user) {
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderContactNo = orderContactNo;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.paint = paint;
        this.user = user;
    }
}
