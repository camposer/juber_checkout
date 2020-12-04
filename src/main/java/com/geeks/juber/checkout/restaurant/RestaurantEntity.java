package com.geeks.juber.checkout.restaurant;

import java.util.List;

// TODO Add when it opens and when it closes
public class RestaurantEntity {
    private Long id;
    private String name;
    private String address; // TODO Refactor make it a value object
    private List<String> plates; // TODO Refactor this a one-to-many
    private boolean open;

    public RestaurantEntity(String name, String address) {
        this.name = name;
        this.address = address;
        this.open = false;
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

    public List<String> getPlates() {
        return plates;
    }

    public void setPlates(List<String> plates) {
        this.plates = plates;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
