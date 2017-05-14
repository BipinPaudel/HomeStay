package com.homestay.bipin.order.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.homestay.bipin.R;
import com.homestay.bipin.RecyclerItemClickListener;
import com.homestay.bipin.order.Order;
import com.homestay.bipin.order.OrderAdapter;
import com.homestay.bipin.order.fragment.OrderEditFragment;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderView {


    OrderAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager recyclerManager;
    List<Order> orderList;

    TextView totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        totalPriceView = (TextView) findViewById(R.id.total_price_id);
        recyclerView = (RecyclerView) findViewById(R.id.order_recycler_id);
        Intent intent = getIntent();
        String orderArrayString = intent.getStringExtra("order_array");
        Bundle args = intent.getBundleExtra("orders");
        orderList = (List<Order>) args.getSerializable("order_array");
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
        adapter = new OrderAdapter(this, orderList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerManager);
        printTotal();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.ClickListener() {
            @Override
            public boolean onItemClick(View view, int position) {
                Order order = adapter.getId(position);
                OrderEditFragment editFragment = new OrderEditFragment();
                Bundle args = new Bundle();
                args.putSerializable("order_object",(Serializable) order);
                editFragment.setArguments(args);
                editFragment.show(getFragmentManager(),"EEE");
                return true;
            }

            @Override
            public boolean onLongClick(View view, int position) {
                return false;
            }
        }));
    }


    @Override
    public void getChangedOrder(Order myOrder) {

        Iterator<Order> myIterator = orderList.iterator();

        while(myIterator.hasNext()){
            Order order1=myIterator.next();
            if (order1.getFoodId()==myOrder.getFoodId()){
                if (myOrder.getFoodQuantity()==0){
                    myIterator.remove();
                }else{
                    order1.setFoodQuantity(myOrder.getFoodQuantity());
                    order1.setFoodTotal(myOrder.getFoodQuantity()*myOrder.getFoodPrice());
                }

            }
        }

        adapter.notifyDataSetChanged();
        printTotal();
//        for (Order order:orderList){
//            if (order.getFoodId()==myOrder.getFoodId()){
//                order.setFoodQuantity();
//            }
//        }
    }

    public void printTotal(){
        Integer total=0;
        for (Order order:orderList){
            total+=order.getFoodTotal();
        }
        totalPriceView.setText(String.valueOf(total));
    }
}
