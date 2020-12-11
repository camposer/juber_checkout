package com.geeks.juber.checkout.restaurant;

import java.util.List;

public interface RestaurantRepository {
    RestaurantEntity save(RestaurantEntity restaurant);

    RestaurantEntity findById(long id);

    List<RestaurantEntity> findAll();

    void deleteById(Long id);

    RestaurantEntity update(RestaurantEntity restaurant);
}
