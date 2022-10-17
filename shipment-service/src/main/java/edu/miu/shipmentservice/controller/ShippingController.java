package edu.miu.shipmentservice.controller;

import edu.miu.shipmentservice.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/service/shipping")
@RestController
public class ShippingController {
    @PostMapping
    public void shippingToAddress(@RequestBody Payment payment) {
        log.info("SHIPPING To: "+ payment.getFirstName() + " " + payment.getLastName());
        log.info("SHIPPING Address: " + payment.getStreet() + ", " + payment.getCity() + ", " + payment.getZipCode());
    }
}
