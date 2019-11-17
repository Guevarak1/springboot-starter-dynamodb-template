package com.kevguev.dynamo_starter_template.demo.data.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.kevguev.dynamo_starter_template.demo.services.models.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@DynamoDBTable(tableName = "Restaurants")
public class RestaurantModel {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = AddressModelTypeConverter.class)
    private AddressModel address;

    public RestaurantModel(Restaurant restaurant) {
        name = restaurant.getName();
        address = restaurant.getAddress() != null ? new AddressModel(restaurant.getAddress()) : null;
    }

    //https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBMapper.ArbitraryDataMapping.html
    static public class AddressModelTypeConverter implements DynamoDBTypeConverter<Map<String, String>, AddressModel> {

        @Override
        public Map<String, String> convert(AddressModel addressModel) {
            if (addressModel == null)
                return null;

            Map<String, String> addressModelMap = new HashMap<>();
            addressModelMap.put("addressLine1", addressModel.getAddressLine1());
            addressModelMap.put("city", addressModel.getCity());
            addressModelMap.put("state", addressModel.getState());
            addressModelMap.put("zipCode", addressModel.getZipCode());

            return addressModelMap;
        }

        @Override
        public AddressModel unconvert(Map<String, String> addressMap) {
            if (addressMap == null)
                return null;

            AddressModel model = new AddressModel();
            model.setAddressLine1(addressMap.get("addressLine1"));
            model.setCity(addressMap.get("city"));
            model.setState(addressMap.get("state"));
            model.setZipCode(addressMap.get("zipCode"));

            return model;
        }
    }
}