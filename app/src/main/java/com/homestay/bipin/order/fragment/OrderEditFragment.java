package com.homestay.bipin.order.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.homestay.bipin.R;
import com.homestay.bipin.order.Order;
import com.homestay.bipin.order.view.OrderView;

/**
 * Created by Bipin on 5/14/17.
 */

public class OrderEditFragment extends DialogFragment {

    ElegantNumberButton elegantNumberButton;
    OrderView orderView;
    Order order;
    Integer quantity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderView= (OrderView) getActivity();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle b = getArguments();
        order = (Order) b.getSerializable("order_object");


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View textEntryView = factory.inflate(R.layout.dialog_order_edit, null);


        elegantNumberButton= (ElegantNumberButton) textEntryView.findViewById(R.id.elegant_id);
        elegantNumberButton.setNumber(String.valueOf(order.getFoodQuantity()));

        elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity=Integer.parseInt(elegantNumberButton.getNumber());
            }
        });

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(textEntryView)
                .setTitle(order.getFoodName())
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        order.setFoodQuantity(quantity);
                        orderView.getChangedOrder(order);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OrderEditFragment.this.getDialog().dismiss();
                    }
                });

        return builder.create();

    }
}
