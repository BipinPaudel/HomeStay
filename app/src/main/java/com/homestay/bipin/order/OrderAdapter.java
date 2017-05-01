package com.homestay.bipin.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homestay.bipin.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Bipin on 5/1/17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyOrderViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<Order> data = Collections.emptyList();
    public OrderAdapter(Context context, List data){
        this.context=context;
        this.data=data;
    }

    @Override
    public MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_list_single,parent,false);
        MyOrderViewHolder myOrderViewHolder= new MyOrderViewHolder(view);

        return myOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(MyOrderViewHolder holder, int position) {
        Order order = data.get(position);

        holder.foodName.setText(order.getFoodName());
        holder.quantity.setText(order.getFoodQuantity());
        holder.price.setText(order.getFoodPrice());
        holder.total.setText(order.getFoodTotal());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyOrderViewHolder extends RecyclerView.ViewHolder{

        TextView foodName,quantity,price,total;
        public MyOrderViewHolder(View itemView) {
            super(itemView);

            foodName = (TextView) itemView.findViewById(R.id.food_name_id);
            quantity= (TextView) itemView.findViewById(R.id.food_quantity_id);
            price = (TextView) itemView.findViewById(R.id.food_price_id);
            total= (TextView) itemView.findViewById(R.id.food_total_id);
        }
    }
}
