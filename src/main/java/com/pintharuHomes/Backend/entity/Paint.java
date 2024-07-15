package com.pintharuHomes.Backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Table(name = "Paint")
public class Paint {

    @Id
    @Column(name = "paint_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "brand")
    private String brand;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "category")
    private String category;

    //new column
    @Column(name = "price_without_dis")
    private Integer noDisPrice;

    @Column(name = "price")
    private Integer price;

    @Column(name = "volume")
    private String volume;

    @OneToMany(mappedBy = "paint")
    private List<Cart> carts;

    @OneToMany(mappedBy = "paint")
    private List<OrderDetail> orderDetails;

    public Paint(Integer id, String name, String image, String brand, Integer quantity, String category, Integer price, String volume) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.volume = volume;
    }

    public Paint(Integer id, String name, String image, String brand, Integer quantity, String category, Integer noDisPrice, Integer price, String volume) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.quantity = quantity;
        this.category = category;
        this.noDisPrice = noDisPrice;
        this.price = price;
        this.volume = volume;
    }
}
