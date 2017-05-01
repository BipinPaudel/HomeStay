package com.homestay.bipin.menu.view;

import android.database.Cursor;


import com.homestay.bipin.order.Order;

import java.util.List;

/**
 * Created by Bipin on 4/29/17.
 */

public interface FoodMenuView {

    void setItems(Cursor menuCursor);

     Integer getUser_id();


        void addItemToMenu(Integer id,String foodName,Integer price,Integer quantity);
//        void subtractItemToMenu(Integer id,Integer price,Integer quantity);
    void navigateToOrder(List<Order> orderList);

}
