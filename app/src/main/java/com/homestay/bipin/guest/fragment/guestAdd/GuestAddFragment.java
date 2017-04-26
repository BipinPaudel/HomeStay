package com.homestay.bipin.guest.fragment.guestAdd;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.homestay.bipin.R;
import com.homestay.bipin.guest.guestList.view.GuestView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Bipin on 4/25/17.
 */

public class GuestAddFragment extends DialogFragment {

    GuestView guestView;

    EditText guestName;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_guest_add, container, false);
        unbinder = ButterKnife.bind(this, view);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guestView = (GuestView) getActivity();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View textEntryView = factory.inflate(R.layout.dialog_guest_add, null);
        guestName = (EditText) textEntryView.findViewById(R.id.guest_name_editText);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater

        builder.setView(textEntryView)
                // Add action buttons
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        String name = guestName.getText().toString();
                        System.out.println("name takne " + name + " is name");
                        guestView.getGuestName(name);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GuestAddFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
