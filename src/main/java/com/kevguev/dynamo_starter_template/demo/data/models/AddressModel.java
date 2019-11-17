package com.kevguev.dynamo_starter_template.demo.data.models;

import com.kevguev.dynamo_starter_template.demo.services.models.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressModel {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String zipCode;

    public AddressModel(Address address) {
        this.addressLine1 = address.addressLine1;
        this.addressLine2 = address.addressLine2;
        this.addressLine3 = address.addressLine3;
        this.city = address.city;
        this.state = address.state;
        this.zipCode = address.zipCode;
    }
}
