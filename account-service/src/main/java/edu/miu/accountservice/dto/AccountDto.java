package edu.miu.accountservice.dto;

import edu.miu.accountservice.entity.Address;
import edu.miu.accountservice.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Address> address;
    private Set<Payment> payment;
    private int preferredPayment;
    private int preferredAddress;
}
