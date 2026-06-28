package com.example;

import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@YAMLMapper
public class Address {

    private String street;

    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
