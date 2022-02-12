package com.example.pasarmalam.Database;

public class Menu {
    String foodName, foodPrice, foodDescription;

    public Menu(){

    }
    public Menu(String foodName,String foodPrice, String foodDescription) {
       this.foodName = foodName;
       this.foodPrice = foodPrice;
       this.foodDescription = foodDescription;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

}
