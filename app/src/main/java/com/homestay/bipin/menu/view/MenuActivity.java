package com.homestay.bipin.menu.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.homestay.bipin.R;
import com.homestay.bipin.data.HomeStayDbHelper;
import com.homestay.bipin.menu.FoodMenu;

import com.homestay.bipin.menu.FoodMenuAdapter;
import com.homestay.bipin.menu.MenuDatabaseAdapter;
import com.homestay.bipin.menu.presenter.MenuPresenter;
import com.homestay.bipin.menu.presenter.MenuPresenterImpl;
import com.homestay.bipin.order.Order;
import com.homestay.bipin.order.view.OrderActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements FoodMenuView,View.OnClickListener {


    RecyclerView recyclerView;
    MenuPresenter menuPresenter;
    TextView proceed;
    FoodMenuAdapter adapter;
    LinearLayoutManager recyclerManager;



    Integer user_id;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        proceed= (TextView) findViewById(R.id.proceed_to_order_id);
        recyclerView = (RecyclerView) findViewById(R.id.menu_recycler_id);
        intent = getIntent();
        user_id = intent.getIntExtra("user_id",0);
        ArrayList foodName = new
                ArrayList(Arrays.asList("chowmein","MOMO","chowmein"));
        ArrayList<Integer> foodPrice =
                new ArrayList(Arrays.asList(300,400,500));
        ArrayList foodtype=new
                ArrayList(Arrays.asList("local","local","special"));

//        MenuDatabaseAdapter mda = new MenuDatabaseAdapter(this);
//        for (int i=0;i<3;i++){
//            mda.insertMenu(foodName.get(i).toString(),
//                    foodPrice.get(i),
//                    foodtype.get(i).toString());
//        }

        System.out.println("la aaiyo easy");
        proceed.setOnClickListener(this);
        menuPresenter = new MenuPresenterImpl(this);



    }

    @Override
    public Integer getUser_id() {
        return user_id;
    }

    @Override
    protected void onResume() {
        super.onResume();
        menuPresenter.onResume();
    }

    @Override
    public void setItems(Cursor menuCursor) {
        List<FoodMenu> foodMenu = new ArrayList<>();
        while(!menuCursor.isAfterLast()){
            Integer id = menuCursor.getInt(menuCursor.getColumnIndex(HomeStayDbHelper.MENU_ID));
            String food=menuCursor.getString(menuCursor.getColumnIndex(HomeStayDbHelper.MENU_FOOD_NAME));
            Integer price=menuCursor.getInt(menuCursor.getColumnIndex(HomeStayDbHelper.MENU_PRICE));
            String type=menuCursor.getString(menuCursor.getColumnIndex(HomeStayDbHelper.MENU_TYPE));

            FoodMenu singleFood = new FoodMenu(id,price,food,type,0);

            System.out.println(singleFood.getFood());
            System.out.println(singleFood.getPrice());
            foodMenu.add(singleFood);
            menuCursor.moveToNext();
        }

        System.out.println(foodMenu.size());

        adapter = new FoodMenuAdapter(MenuActivity.this,foodMenu);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerManager);

    }

    @Override
    public void addItemToMenu(Integer id,String foodName,Integer price,Integer quantity) {
        System.out.println("menu add" +id + price + " " + quantity);
        menuPresenter.addToOrder(id,foodName,price,quantity);
    }

    @Override
    public void navigateToOrder(List<Order> orderList) {
        Intent intent = new Intent(this, OrderActivity.class);
        Gson gs = new Gson();
        Bundle args = new Bundle();
        args.putSerializable("order_array",(Serializable) orderList);

        intent.putExtra("orders",args);
        //menuPresenter.onStop();

//        String orderArrayString = gs.toJson(orderList,Order.class);
//        intent.putExtra("order_array",orderArrayString);

        startActivity(intent);
    }


    @Override
    public void onClick(View view) {

        navigateToOrder(menuPresenter.getOrderList());
    }
}
