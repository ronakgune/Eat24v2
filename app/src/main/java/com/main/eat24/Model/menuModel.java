package com.main.eat24.Model;

/**
 * Created by ronakgune on 6/8/18.
 */

public class menuModel {
    public  String foodName;
    public String cost;

    public menuModel(){

    }

    // Setters
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    // Getters
    public String getFoodName() {
        return foodName;
    }

    public String getCost() {
        return cost;
    }

    // Constructor
    public menuModel(String foodName, String cost) {
        this.foodName = foodName;
        this.cost = cost;
    }
}
