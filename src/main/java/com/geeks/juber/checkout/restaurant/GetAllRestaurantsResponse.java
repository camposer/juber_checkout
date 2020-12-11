package com.geeks.juber.checkout.restaurant;

import java.util.List;

public class GetAllRestaurantsResponse {
    private List<Restaurant> data;

    public GetAllRestaurantsResponse(List<Restaurant> restaurants) {
        this.data = restaurants;
    }

    public List<Restaurant> getData() {
        return data;
    }

    static class Restaurant {
        private Long id;
        private String name;
        private String address;

        public Restaurant(Long id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
