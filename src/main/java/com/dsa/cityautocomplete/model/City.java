package com.dsa.cityautocomplete.model;

public class City {
    Integer id;
    String name;
    Integer state_id;
    String state_code;
    String state_name;
    Integer country_id;
    String country_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String country_name;
    String latitude;
    String longitude;
    String wikiDataId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

    public void setWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", state_id=" + state_id +
                ", state_code='" + state_code + '\'' +
                ", state_name='" + state_name + '\'' +
                ", country_id=" + country_id +
                ", country_code='" + country_code + '\'' +
                ", country_name='" + country_name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", wikiDataId='" + wikiDataId + '\'' +
                '}';
    }
}
