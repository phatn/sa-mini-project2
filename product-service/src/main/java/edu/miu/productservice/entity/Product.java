package edu.miu.productservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String vendor;

    private int quantity;

    private double price;
    @ManyToOne
    @JsonBackReference
    private Category category;
}
