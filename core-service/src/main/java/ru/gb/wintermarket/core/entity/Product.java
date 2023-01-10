package ru.gb.wintermarket.core.entity;


import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @CreationTimestamp//hibernate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp//hibernate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
