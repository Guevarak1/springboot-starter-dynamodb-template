package com.kevguev.dynamo_starter_template.demo.services.models;

import com.kevguev.dynamo_starter_template.demo.controllers.resources.RestaurantResource;
import com.kevguev.dynamo_starter_template.demo.data.models.RestaurantModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Restaurant {
    private String name;
    private Address address;

    public Restaurant(RestaurantResource restaurantResource) {
        name = restaurantResource.getName();
        address = restaurantResource.getAddress() != null ? new Address(restaurantResource.getAddress()) : null;
    }

    public Restaurant(RestaurantModel restaurantModel) {
        name = restaurantModel.getName();
        address = restaurantModel.getAddress() != null ? new Address(restaurantModel.getAddress()) : null;
    }
}
