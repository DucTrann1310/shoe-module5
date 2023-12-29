package com.cg.model.dto;

import com.cg.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerReqDTO {

    private String fullname;

    private String address;

    private String email;

    private String mobile;

    public Customer toCustomer(){
        return new Customer()
                .setFullname(fullname)
                .setAddress(address)
                .setEmail(email)
                .setMobile(mobile);
    }
}
