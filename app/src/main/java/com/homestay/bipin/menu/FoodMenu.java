package com.homestay.bipin.menu;

/**
 * Created by Bipin on 4/29/17.
 */

public class FoodMenu {
    private Integer id;
    private Integer price;
    private String food;
    private String type;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




    public FoodMenu(Integer id, Integer price,String food,String type,Integer quantity){
        this.id= id;
        this.price= price;
        this.food=food;
        this.type=type;
        this.quantity=quantity;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
