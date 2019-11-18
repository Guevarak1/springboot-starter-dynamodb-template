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

    @GetMapping
    public ResponseEntity<List<RestaurantResource>> getRestaurants() {
        List<Restaurant> restaurants = restaurantService.retrieveRestaurants();

        return ResponseEntity.ok(convertToRestaurantResources(restaurants));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResource> getRestaurant(@PathVariable String id) {
        Restaurant restaurant = restaurantService.retrieveRestaurant(id);

        return ResponseEntity.ok(new RestaurantResource(restaurant));
    }

    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantResource restaurantResource) {
        //Check that all our required fields are present
        if (restaurantResource.getName() == null) {
            return ResponseEntity.badRequest().body("missing name");
        }

        if (restaurantResource.getAddress() == null) {
            return ResponseEntity.badRequest().body("missing address");
        } else if (restaurantResource.getAddress().getAddressLine1() == null ||
                restaurantResource.getAddress().getCity() == null ||
                restaurantResource.getAddress().getState() == null ||
                restaurantResource.getAddress().getZipCode() == null) {
            return ResponseEntity.badRequest().body("missing required address fields");
        }

        Restaurant restaurant = restaurantService.createRestaurant(new Restaurant(restaurantResource));
        return new ResponseEntity<>(new  RestaurantResource(restaurant), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<RestaurantResource> updateRestaurantName(@PathVariable String id, @RequestBody String name) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, name);

        return ResponseEntity.ok(new RestaurantResource(updatedRestaurant));
    }

    @PatchMapping("/{id}/address")
    public ResponseEntity<?> updateRestaurantsAddress(@PathVariable String id, @RequestBody AddressResource addressResource) {
        if (addressResource == null) {
            return ResponseEntity.badRequest().body("missing address");
        } else if (addressResource.getAddressLine1() == null ||
                addressResource.getCity() == null ||
                addressResource.getState() == null ||
                addressResource.getZipCode() == null) {
            return ResponseEntity.badRequest().body("missing required address fields");
        }

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
