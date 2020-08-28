package com.example.storeInspicio.Models.StoreModels;

import java.util.Objects;

public class Properties {
    private String store;
    private String picUrl;
    private String address;
    private String openingHours;
    private String website;
    private String city;
    private Province province;

    public Properties(String store, String picUrl, String address, String openingHours, String website, String city, Province province) {
        this.store = store;
        this.picUrl = picUrl;
        this.address = address;
        this.openingHours = openingHours;
        this.website = website;
        this.city = city;
        this.province = province;
    }

    public String getStore() {
        return store;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getWebsite() {
        return website;
    }

    public String getCity() {
        return city;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return store.equals(that.store) &&
                Objects.equals(picUrl, that.picUrl) &&
                address.equals(that.address) &&
                openingHours.equals(that.openingHours) &&
                Objects.equals(website, that.website) &&
                city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store, picUrl, address, openingHours, website, city);
    }

    @Override
    public String toString() {
        return "Properties{" +
                "store='" + store + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", address='" + address + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", website='" + website + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

