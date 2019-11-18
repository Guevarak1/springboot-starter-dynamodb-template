package com.kevguev.dynamo_starter_template.demo.services;

import com.kevguev.dynamo_starter_template.demo.data.models.RestaurantModel;
import com.kevguev.dynamo_starter_template.demo.data.repositories.interfaces.RestaurantRepository;
import com.kevguev.dynamo_starter_template.demo.services.interfaces.RestaurantService;
import com.kevguev.dynamo_starter_template.demo.services.models.Address;
import com.kevguev.dynamo_starter_template.demo.services.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant retrieveRestaurant(String id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("object not found");
        }

        return new Restaurant(restaurantRepository.findById(id).get());
    }

    @Override
    public List<Restaurant> retrieveRestaurants() {
        Iterable<RestaurantModel> restaurantModels = restaurantRepository.findAll();
        List<Restaurant> restaurants = new ArrayList<>();

        for (RestaurantModel model : restaurantModels) {
            Restaurant restaurant = new Restaurant(model);
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        RestaurantModel restaurantModel = restaurantRepository.save(new RestaurantModel(restaurant));
        return new Restaurant(restaurantModel);
    }

    @Override
    public Restaurant updateRestaurant(String id, String name) {
        restaurantRepository.updateRestaurantName(id, name);
        return retrieveRestaurant(id);
    }

    @Override
    public Restaurant updateRestaurantsAddress(String id, Address address) {
        restaurantRepository.updateRestaurantAddress(id, address);
        return retrieveRestaurant(id);

    }

    @Override
    public void deleteRestaurantById(String restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RuntimeException("object not found");
        }

        restaurantRepository.deleteById(restaurantId);
    }
}
