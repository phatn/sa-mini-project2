package edu.miu.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private Set<Address> address;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private Set<Payment> payment;

    private int preferredPayment;
    private int preferredAddress;
}
