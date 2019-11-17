package com.kevguev.dynamo_starter_template.demo.controllers.resources;

import com.kevguev.dynamo_starter_template.demo.services.models.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantResource {
    private String name;
    private AddressResource address;

    public RestaurantResource(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.address = restaurant.getAddress() != null ? new AddressResource(restaurant.getAddress()) : null;
    }
}
