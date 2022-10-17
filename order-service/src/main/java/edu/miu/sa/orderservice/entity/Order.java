package edu.miu.sa.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountEmail;

    private double total;

    private String paymentType;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<OrderItem> orderItems;

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.orderItems.forEach(o -> o.setOrder(this));
    }
}
