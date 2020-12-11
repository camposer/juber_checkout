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

    @Override // FIXME What about the other fields?
    public RestaurantEntity save(RestaurantEntity restaurant) {
        RestaurantEntity newRestaurant =
                new RestaurantEntity(restaurant.getName(), restaurant.getAddress()); // clone
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

    @Override
    public void deleteById(Long id) {
        if (restaurants.get(id) == null) // guard clause
            throw new InvalidIdException(String.format("There is no restaurant with id = %d", id));
        restaurants.remove(id);
    }

    @Override
    public RestaurantEntity update(RestaurantEntity restaurant) {
        if (restaurant.getId() == null || restaurants.get(restaurant.getId()) == null)
            throw new RuntimeException("Invalid ....");
        return restaurants.put(restaurant.getId(), restaurant);
    }
}
