package edu.miu.paypalservice.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private int id;
    private String zipCode;
    private String street;
    private String city;
}
