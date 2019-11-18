package com.kevguev.dynamo_starter_template.demo.data.repositories.interfaces;

import com.kevguev.dynamo_starter_template.demo.data.models.RestaurantModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface RestaurantRepository extends CrudRepository<RestaurantModel, String>, RestaurantRepositoryCustom {

}
