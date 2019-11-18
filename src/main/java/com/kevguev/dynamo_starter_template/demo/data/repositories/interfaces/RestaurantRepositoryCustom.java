package com.kevguev.dynamo_starter_template.demo.data.repositories.interfaces;

import com.kevguev.dynamo_starter_template.demo.services.models.Address;

public interface RestaurantRepositoryCustom {

    void updateRestaurantName(String id, String name);

    void updateRestaurantAddress(String id, Address address);
}
