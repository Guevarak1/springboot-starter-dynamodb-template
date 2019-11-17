package com.kevguev.dynamo_starter_template.demo.services;

import com.kevguev.dynamo_starter_template.demo.data.repositories.RestaurantRepository;
import com.kevguev.dynamo_starter_template.demo.services.interfaces.RestaurantService;
import com.kevguev.dynamo_starter_template.demo.services.models.Address;
import com.kevguev.dynamo_starter_template.demo.services.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant retrieveRestaurant(String id) {
        if (!restaurantRepository.findById(id).isPresent()) {
            throw new RuntimeException("object not found");
        }

        return new Restaurant(restaurantRepository.findById(id).get());
    }

    @Override
    public List<Restaurant> retrieveRestaurants() {
        return null;
    }

    @Override
    public List<Restaurant> retrieveRestaurants(String lastName) {
        return null;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant updateRestaurant(String id, Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant updateRestaurantsAddress(String id, Address address) {
        return null;
    }

    @Override
    public void deleteRestaurantById(String restaurantId) {

    }
}
