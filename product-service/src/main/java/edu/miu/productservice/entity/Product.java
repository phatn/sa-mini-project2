package edu.miu.productservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Product {
    @Id
    private int id;
    private String name;
    private String vendor;
    private int units;

    @Enumerated(EnumType.STRING)
    private ProductType category;
}
