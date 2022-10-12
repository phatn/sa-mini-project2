package edu.miu.accountservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {
    @Id
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    @Embedded
    private Address address;
    @Embedded
    private Payment payment;
}
