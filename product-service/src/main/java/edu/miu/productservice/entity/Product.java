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
    private int id;
    private String name;
    private String vendor;
    private int quantity;

    @ManyToOne
    @JsonBackReference
    private Category category;
}
