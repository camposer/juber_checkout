package com.geeks.juber.checkout.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RestaurantServiceTest {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Nested
    class GetAllRestaurants {
        @Test
        public void shouldReturnARestaurant() {
            // prepare
            List<RestaurantEntity> restaurants = Arrays.asList(new RestaurantEntity[] {
                    new RestaurantEntity("Name 1", "Address 1"),
                    new RestaurantEntity("Name 2", "Address 2"),
            });
            when(restaurantRepository.findAll()).thenReturn(restaurants);

            // execute
            GetAllRestaurantsResponse response = restaurantService.getAllRestaurants();

            // assert
            Assertions.assertEquals(2, response.getData().size());
        }
    }

    @Configuration
    static class Config {
        @Bean
        public RestaurantService restaurantService(RestaurantRepository restaurantRepository) {
            return new RestaurantService(restaurantRepository);
        }

        @Bean
        public RestaurantRepository restaurantRepository() {
            return mock(RestaurantRepository.class);
        }
    }
}
