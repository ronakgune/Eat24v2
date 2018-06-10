package com.main.eat24.Model;


public class Restaurant {
    private String id;
    private String name;
    private String address;
    private String rating;
    private String cost;
    private String imageUrl;
    private String currency;
    private double longitude;
    private double latitiude;


    public Restaurant() {
    }



    public Restaurant(String id, String name, String address, String rating, String cost, String imageUrl, String currency, double longitude, double latitiude) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.cost = cost;
        this.imageUrl = imageUrl;
        this.currency = currency;

        this.longitude = longitude;
        this.latitiude = latitiude;
    }

    //ID
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // Name
    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    // Address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //Rating
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    //Cost
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    // Image
    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //Get Currency
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // Long
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Lat
    public double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(double latitiude) {
        this.latitiude = latitiude;
    }
}
