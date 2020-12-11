package com.geeks.juber.checkout.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = AFTER_METHOD)
public class RestaurantRepositoryTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Nested
    class FindById {
        @Test
        public void shouldReturnARestaurantForAGivenId() {
            // prepare
            RestaurantEntity restaurant = new RestaurantEntity("Name XXX", "Address");
            restaurantRepository.save(restaurant);

            // execute
            RestaurantEntity actualRestaurant = restaurantRepository.findById(1L);

            // assert
            assertEquals(1L, actualRestaurant.getId());
            assertEquals("Name XXX", actualRestaurant.getName());
            assertEquals("Address", actualRestaurant.getAddress());
            assertFalse(actualRestaurant.isOpen());
        }

        @Test
        public void shouldReturnNullWhenIdDoesntExist() {
            // execute
            RestaurantEntity actualRestaurant = restaurantRepository.findById(99L);

            // assert
            assertNull(actualRestaurant);
        }
    }

    @Nested
    class FindAll {
        @Test
        public void shouldReturnAListOfRestaurants() {
            // prepare
            restaurantRepository.save(new RestaurantEntity("Name 1", "Address 1"));
            restaurantRepository.save(new RestaurantEntity("Name 2", "Address 2"));

            // execute
            List<RestaurantEntity> actualRestaurants = restaurantRepository.findAll();

            // assert
            assertEquals(2, actualRestaurants.size());
            assertEquals("Name 1", actualRestaurants.get(0).getName());
            assertEquals("Address 1", actualRestaurants.get(0).getAddress());
            assertFalse(actualRestaurants.get(0).isOpen());
            assertEquals("Name 2", actualRestaurants.get(1).getName());
            assertEquals("Address 2", actualRestaurants.get(1).getAddress());
            assertFalse(actualRestaurants.get(1).isOpen());
        }

        @Test
        public void shouldReturnAnEmptyListWhenThereAreNoRestaurants() {
            assertEquals(0, restaurantRepository.findAll().size());
        }
    }

    @Nested
    class Save {
        @Test
        public void shouldReturnAClassWithId() {
            // prepare
            RestaurantEntity restaurant = new RestaurantEntity("Name", "Address");

            // execute
            RestaurantEntity actualRestaurant = restaurantRepository.save(restaurant);

            // assert
            assertNotNull(actualRestaurant.getId());
            assertEquals("Name", actualRestaurant.getName());
            assertEquals("Address", actualRestaurant.getAddress());
            assertFalse(actualRestaurant.isOpen());
        }
    }

    @Nested
    class DeleteById {
        @Test
        public void shouldRemoveARestaurantById() {
            // prepare
            RestaurantEntity restaurant = new RestaurantEntity("Name", "Address");
            RestaurantEntity actualRestaurant = restaurantRepository.save(restaurant);

            // execute
            restaurantRepository.deleteById(actualRestaurant.getId());

            // assert
            assertEquals(0, restaurantRepository.findAll().size());
        }

        @Test
        public void shouldThrowWhenTheIdDoesntExist() {
            Assertions.assertThrows(InvalidIdException.class, () -> {
                restaurantRepository.deleteById(99L);
            });
        }
    }

    @Nested
    class Update {
        @Test
        public void shouldUpdate() {
            RestaurantEntity restaurant = new RestaurantEntity("Name", "Address");
            RestaurantEntity newRestaurant = restaurantRepository.save(restaurant);

            newRestaurant.setName("New Name");
            newRestaurant.setAddress("New Address");
            RestaurantEntity updatedRestaurant = restaurantRepository.update(newRestaurant);

            assertEquals(1, restaurantRepository.findAll().size());
            assertEquals("New Name", updatedRestaurant.getName());
            assertEquals("New Address", updatedRestaurant.getAddress());
        }

        @Test
        public void shouldThrowWhenTheRestaurantIdIsNull() {
            RestaurantEntity restaurant = new RestaurantEntity("Name", "Address");

            Assertions.assertThrows(RuntimeException.class, () -> {
                restaurantRepository.update(restaurant);
            });
        }

        @Test
        public void shouldThrowWhenTheRestaurantDoesntExist() {
            RestaurantEntity restaurant = new RestaurantEntity("Name", "Address");
            restaurant.setId(99L);

            Assertions.assertThrows(RuntimeException.class, () -> {
                restaurantRepository.update(restaurant);
            });
        }
    }


    @Configuration
    static class Config {
        @Bean
        @Scope("prototype")
        public RestaurantRepository restaurantRepository() {
            return new RestaurantInMemoryRepository();
        }
    }

}
