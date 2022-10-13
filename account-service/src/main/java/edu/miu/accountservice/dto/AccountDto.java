package edu.miu.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
