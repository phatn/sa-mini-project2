package edu.miu.sa.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountEmail;

    private double total;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<OrderItem> orderItems;
}
