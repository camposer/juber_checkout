package com.geeks.juber.checkout.restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public GetAllRestaurantsResponse getAllRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();
        return restaurantEntitiesToGetAllRestaurantsResponse(restaurants);
    }

    // TODO Please, please refactor!!!
    private GetAllRestaurantsResponse restaurantEntitiesToGetAllRestaurantsResponse(List<RestaurantEntity> restaurants) {
        List<GetAllRestaurantsResponse.Restaurant> responseRestaurants = new ArrayList<>();
        restaurants.forEach(r -> {
            responseRestaurants.add(new GetAllRestaurantsResponse.Restaurant(r.getId(), r.getName(), r.getAddress()));
        });
        return new GetAllRestaurantsResponse(responseRestaurants);
    }
}
