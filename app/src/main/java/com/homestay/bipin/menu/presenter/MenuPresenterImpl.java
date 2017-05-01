package com.homestay.bipin.menu.presenter;

import android.database.Cursor;
import android.widget.ProgressBar;

import com.homestay.bipin.menu.MenuDatabaseAdapter;
import com.homestay.bipin.menu.interactor.MenuInteractor;
import com.homestay.bipin.menu.interactor.MenuInteractorImpl;
import com.homestay.bipin.menu.view.FoodMenuView;
import com.homestay.bipin.order.Order;

import java.util.ArrayList;

/**
 * Created by Bipin on 4/29/17.
 */

public class MenuPresenterImpl implements MenuPresenter, MenuInteractor.OnMenuLoadedListener {

    FoodMenuView foodMenuView;
    MenuInteractor menuInteractor;

    @Override
    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    ArrayList<Order> orderList;

    public MenuPresenterImpl(FoodMenuView foodMenuView){
        this.foodMenuView=foodMenuView;
        menuInteractor= new MenuInteractorImpl(foodMenuView);
        orderList= new ArrayList<>();
    }




    @Override
    public void OnMenuLoadSuccess(Cursor cursor) {
        System.out.println("menu successfully loaded");
        foodMenuView.setItems(cursor);
    }

    @Override
    public void onMenuLoadError() {

    }

    @Override
    public void onResume() {
        menuInteractor.loadMenu(this);
    }

    @Override
    public void onDestroy() {
        if (foodMenuView!=null){
            foodMenuView=null;
        }
    }

    @Override
    public void onStop() {
        if (orderList!=null){
            orderList=null;
        }
    }

    @Override
    public void addToOrder(Integer food_id, String food_name, Integer price, Integer quantity) {
        Integer userId=foodMenuView.getUser_id();
        Order myOrder = new Order(food_id,food_name,price,quantity,quantity*price ,userId);
        for (Order order: orderList){
            if (order.getFoodId().equals(myOrder.getFoodId())){
                orderList.remove(order);
            }
        }
        orderList.add(myOrder);


    }
}
