package com.homestay.bipin.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.homestay.bipin.R;
import com.homestay.bipin.menu.view.FoodMenuView;
import com.homestay.bipin.menu.view.MenuActivity;
import com.homestay.bipin.order.Order;

import java.util.Collections;
import java.util.List;

/**
 * Created by Bipin on 4/29/17.
 */

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.MyMenuViewHoler> {

    FoodMenuView foodMenuView;
    LayoutInflater inflater;
    Context context;



    List<FoodMenu> data= Collections.emptyList();

    public FoodMenuAdapter(Context context,List data){
        this.context=context;
        foodMenuView =(MenuActivity) context;
        this.data=data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyMenuViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_list_single,parent,false);
        MyMenuViewHoler myMenuViewHoler = new MyMenuViewHoler(view);
        return myMenuViewHoler;
    }

    @Override
    public void onBindViewHolder(final MyMenuViewHoler holder, final int position) {
        final FoodMenu foodMenu = data.get(position);
        holder.foodView.setText(foodMenu.getFood());
        holder.priceView.setText(String.valueOf(foodMenu.getPrice()));
        holder.typeView.setText(foodMenu.getType());
        holder.elegantNumberButton.setNumber(String.valueOf(foodMenu.getQuantity()));

        System.out.println("adapter set hai hai");

        holder.elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer quantity = Integer.parseInt(holder.elegantNumberButton.getNumber());
//                Order order = new Order(foodMenu.getId(),foodMenu.getFood(),foodMenu.getPrice(),foodMenu.getQuantity());

                foodMenuView.addItemToMenu(foodMenu.getId(),foodMenu.getFood(),foodMenu.getPrice(),quantity,position);
                //foodMenuView.test(position);
            }
        });


        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d("TAG", String.format("oldValue: %d   newValue: %d", oldValue, newValue));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyMenuViewHoler extends RecyclerView.ViewHolder{

        TextView foodView, priceView, typeView;

        ElegantNumberButton elegantNumberButton;

        public MyMenuViewHoler(View itemView) {
            super(itemView);
            foodView= (TextView) itemView.findViewById(R.id.food_id);
            priceView= (TextView) itemView.findViewById(R.id.price_id);
            typeView= (TextView) itemView.findViewById(R.id.type_id);

            elegantNumberButton = (ElegantNumberButton) itemView.findViewById(R.id.elegant_id);


        }
    }
}
