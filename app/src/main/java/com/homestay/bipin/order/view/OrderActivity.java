package com.homestay.bipin.order.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.homestay.bipin.R;
import com.homestay.bipin.order.Order;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String orderArrayString = intent.getStringExtra("order_array");
        Bundle args = intent.getBundleExtra("orders");
        List<Order> orderList= (List<Order>) args.getSerializable("order_array");
        for (Order order : orderList) {
            System.out.println(order.getFoodId());
            System.out.println(order.getFoodName());
            System.out.println(order.getFoodPrice());
            System.out.println(order.getFoodQuantity());
            System.out.println(order.getFoodTotal());
            System.out.println(order.getUserOrderId());
            System.out.println();
            System.out.println();
        }
    }
}
