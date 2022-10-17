package edu.miu.bankservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String firstName;
    private String lastName;
    private Set<Address> address;
    private Set<Payment> payment;
    private int preferredAddress;
    private int preferredPayment;
}
