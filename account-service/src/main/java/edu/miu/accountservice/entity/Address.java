package edu.miu.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String zipCode;
    private String street;
    private String city;

    @ManyToOne
    @JsonBackReference
    private Account owner;
}
