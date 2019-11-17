package com.kevguev.dynamo_starter_template.demo.services.interfaces;

import com.kevguev.dynamo_starter_template.demo.services.models.Address;
import com.kevguev.dynamo_starter_template.demo.services.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant retrieveRestaurant(String id);

    List<Restaurant> retrieveRestaurants();

    List<Restaurant> retrieveRestaurants(String lastName);

    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(String id, Restaurant restaurant);

    Restaurant updateRestaurantsAddress(String id, Address address);

    void deleteRestaurantById(String restaurantId);
}
