package com.kevguev.dynamo_starter_template.demo.data.repositories;

import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.kevguev.dynamo_starter_template.demo.data.repositories.interfaces.RestaurantRepositoryCustom;
import com.kevguev.dynamo_starter_template.demo.services.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final DynamoDB client;

    @Autowired
    public RestaurantRepositoryImpl(DynamoDB client) {
        this.client = client;
    }

    @Override
    public void updateRestaurantName(String id, String name) {
        Table restaurantTable = client.getTable("Restaurants");

        PrimaryKey primaryKey = new PrimaryKey("id", id);
        Item restaurant = restaurantTable.getItem(primaryKey);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found in database with id: " + id);
        }

        restaurantTable.updateItem(primaryKey, new AttributeUpdate("name").put(name));
    }

    @Override
    public void updateRestaurantAddress(String id, Address address) {
        Table restaurantTable = client.getTable("Restaurants");

        PrimaryKey primaryKey = new PrimaryKey("id", id);
        Item restaurant = restaurantTable.getItem(primaryKey);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found in database with id: " + id);
        }

        //https://stackoverflow.com/questions/39161097/putting-updating-item-in-dynamodb-fails-for-the-updateexpression-syntax
        //reserved keyword state. throws exception
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#s", "address.state");

        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey(primaryKey)
                .withUpdateExpression("set address.addressLine1 = :a1, address.city = :c, #s = :s, address.zipCode = :z")
                .withNameMap(nameMap)
                .withValueMap(new ValueMap()
                        .withString(":a1", address.getAddressLine1())
                        .withString(":c", address.getCity())
                        .withString(":s", address.getState())
                        .withString(":z", address.getZipCode()))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        restaurantTable.updateItem(spec);
    }
}
