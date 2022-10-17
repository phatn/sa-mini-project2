package edu.miu.sa.paymentservice.entity;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String firstName;
    private String lastName;
    private Set<Address> address;
    private int preferredAddress;
}
