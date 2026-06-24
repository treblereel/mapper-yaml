package com.example;

import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlProperty;

@YAMLMapper
public class Address {

    private String street;

    @YamlProperty("zip-code")
    private String zipCode;

    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
