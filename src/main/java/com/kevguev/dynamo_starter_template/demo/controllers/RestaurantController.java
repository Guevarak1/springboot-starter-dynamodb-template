package com.kevguev.dynamo_starter_template.demo.controllers;

import com.kevguev.dynamo_starter_template.demo.controllers.resources.AddressResource;
import com.kevguev.dynamo_starter_template.demo.controllers.resources.RestaurantResource;
import com.kevguev.dynamo_starter_template.demo.services.interfaces.RestaurantService;
import com.kevguev.dynamo_starter_template.demo.services.models.Address;
import com.kevguev.dynamo_starter_template.demo.services.models.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResource> getRestaurant(@PathVariable String id) {
        Restaurant Restaurant = restaurantService.retrieveRestaurant(id);

        return ResponseEntity.ok(new RestaurantResource(Restaurant));
    }

    @PostMapping
    public ResponseEntity<RestaurantResource> createRestaurant(@RequestBody RestaurantResource restaurantResource) {
        Restaurant Restaurant = restaurantService.createRestaurant(new Restaurant(restaurantResource));

        return new ResponseEntity<>(new  RestaurantResource(Restaurant), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestaurantResource> updateRestaurant(@PathVariable String id, @RequestBody RestaurantResource restaurantResource) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, new Restaurant(restaurantResource));

        return ResponseEntity.ok(new RestaurantResource(updatedRestaurant));
    }

    @PatchMapping("/{id}/address")
    public ResponseEntity<RestaurantResource> updateRestaurantsAddress(@PathVariable String id, @RequestBody AddressResource addressResource) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurantsAddress(id, new Address(addressResource));

        return ResponseEntity.ok(new RestaurantResource(updatedRestaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.ok().build();
    }

    private static List<RestaurantResource> convertToRestaurantResources(List<Restaurant> Restaurants) {
        List<RestaurantResource> RestaurantResources = new ArrayList<>();
        for (Restaurant Restaurant : Restaurants) {
            RestaurantResource RestaurantResource = new RestaurantResource(Restaurant);
            RestaurantResources.add(RestaurantResource);
        }

        return RestaurantResources;
    }
}
