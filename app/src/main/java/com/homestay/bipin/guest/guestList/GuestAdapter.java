package com.homestay.bipin.guest.guestList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homestay.bipin.R;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Bipin on 4/26/17.
 */

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.MyGuestViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<Guest> data = Collections.emptyList();
    public GuestAdapter(Context context, List data){
        this.context= context;
        inflater=LayoutInflater.from(context);
        this.data= data;

    }
    @Override
    public MyGuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.guest_list_single,parent,false);
        MyGuestViewHolder myGuestViewHolder = new MyGuestViewHolder(view);

        return myGuestViewHolder;
    }

    @Override
    public void onBindViewHolder(MyGuestViewHolder holder, int position) {

        final Guest g = data.get(position);
        holder.textName.setText(g.getName());
        holder.textDate.setText(g.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Guest getId(int position){
        return data.get(position);
    }

    class MyGuestViewHolder extends RecyclerView.ViewHolder{



        TextView textName;
        TextView textDate;
        public MyGuestViewHolder(View itemView) {
            super(itemView);
            textName= (TextView) itemView.findViewById(R.id.guest_name_holder);

            textDate = (TextView) itemView.findViewById(R.id.guest_date_holder);
        }
    }
}
