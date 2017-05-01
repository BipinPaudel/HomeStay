package com.homestay.bipin.guest.fragment.guestOption;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homestay.bipin.R;
import com.homestay.bipin.menu.view.MenuActivity;

/**
 * Created by Bipin on 4/26/17.
 */

public class GuestOptionFragment extends Fragment {

    TextView textView1;
    TextView goToMenu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_guest_option,container,false);
        textView1 = (TextView) view.findViewById(R.id.ttt);
        goToMenu = (TextView) view.findViewById(R.id.go_to_menu_id);

        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(), MenuActivity.class));
            }
        });
        return view;
    }

    public void changeData(Integer id, String data){
        textView1.setText(String.valueOf(id)+" "+data);
    }


}
