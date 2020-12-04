package com.geeks.juber.checkout.restaurant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// THIS CLASS IS NOT THREAD SAFE!!!
public class RestaurantInMemoryRepository implements RestaurantRepository {
    private Map<Long, RestaurantEntity> restaurants;
    private long currentId;

    public RestaurantInMemoryRepository() {
        restaurants = new LinkedHashMap<>();
        currentId = 0;
    }

    @Override
    public RestaurantEntity save(RestaurantEntity restaurant) {
        RestaurantEntity newRestaurant =
                new RestaurantEntity(restaurant.getName(), restaurant.getAddress());
        currentId++;
        newRestaurant.setId(currentId);
        this.restaurants.put(currentId, newRestaurant);
        return newRestaurant;
    }

    @Override
    public RestaurantEntity findById(long id) {
        return restaurants.get(id);
    }

    @Override
    public List<RestaurantEntity> findAll() {
        return new ArrayList<>(restaurants.values());
    }
}
