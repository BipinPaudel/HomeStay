package com.homestay.bipin.guest.guestOption.view;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.homestay.bipin.R;
import com.homestay.bipin.guest.fragment.guestOption.GuestOptionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuestOptionActivity extends AppCompatActivity {


    GuestOptionFragment guestOptionFragment;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_option);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        Integer id = intent.getIntExtra("id",0);
        manager= getFragmentManager();
        guestOptionFragment = (GuestOptionFragment) manager.findFragmentById(R.id.guest_option_fragment);
        if (guestOptionFragment!=null){
            guestOptionFragment.changeData(id,data);
        }


    }
}
