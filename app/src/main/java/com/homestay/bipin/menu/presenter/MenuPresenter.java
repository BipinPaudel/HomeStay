package com.homestay.bipin.menu.presenter;

import com.homestay.bipin.order.Order;

import java.util.ArrayList;

/**
 * Created by Bipin on 4/29/17.
 */

public interface MenuPresenter {

    void onResume();

    void onDestroy();

    void onStop();

    void addToOrder(Integer food_id,String food_name,Integer price,Integer quantity);

    ArrayList<Order> getOrderList();

}
