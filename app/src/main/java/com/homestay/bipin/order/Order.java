package com.homestay.bipin.order;

import java.io.Serializable;

/**
 * Created by Bipin on 5/1/17.
 */

public class Order implements Serializable{

    private Integer foodId;
    private String foodName;
    private Integer foodPrice;
    private Integer foodQuantity;
    private Integer foodTotal;
    private Integer userOrderId;

public Order(Integer foodId,String foodName,Integer foodPrice,Integer foodQuantity,Integer foodTotal,Integer userOrderId){
    this.foodId=foodId;
    this.foodName=foodName;
    this.foodPrice=foodPrice;
    this.foodQuantity=foodQuantity;
    this.foodTotal=foodTotal;
    this.userOrderId= userOrderId;
}

    public Integer getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public Integer getFoodQuantity() {
        return foodQuantity;
    }

    public Integer getFoodTotal() {
        return foodTotal;
    }

    public Integer getUserOrderId() {
        return userOrderId;
    }
}
