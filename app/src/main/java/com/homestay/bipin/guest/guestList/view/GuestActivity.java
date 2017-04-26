package com.homestay.bipin.guest.guestList.view;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.homestay.bipin.R;
import com.homestay.bipin.data.HomeStayDbHelper;
import com.homestay.bipin.guest.fragment.guestAdd.GuestAddFragment;
import com.homestay.bipin.guest.fragment.guestAdd.GuestEditFragment;
import com.homestay.bipin.guest.fragment.guestList.GuestListFragment;
import com.homestay.bipin.guest.fragment.guestList.GuestListFragmentImpl;
import com.homestay.bipin.guest.fragment.guestOption.GuestOptionFragment;
import com.homestay.bipin.guest.guestList.GuestDataBaseAdapter;
import com.homestay.bipin.guest.guestList.presenter.GuestPresenter;
import com.homestay.bipin.guest.guestList.presenter.GuestPresenterImpl;
import com.homestay.bipin.guest.guestOption.view.GuestOptionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuestActivity extends AppCompatActivity implements GuestView, GuestListFragmentImpl.PassListToGuestActivity {


//    @BindView(R.id.fab) FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;
    GuestPresenter guestPresenter;
    FragmentManager fragmentManager;
    GuestListFragmentImpl guestListFragment;
    GuestOptionFragment guestOptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        ButterKnife.bind(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        Cursor cursor = new GuestDataBaseAdapter(getApplicationContext()).getAllGuests();
//        while(!cursor.isAfterLast()) {
//            System.out.println(cursor.getString(cursor.getColumnIndex(HomeStayDbHelper.GUEST_GID)));
//            System.out.println(cursor.getString(cursor.getColumnIndex(HomeStayDbHelper.GUEST_NAME)));
//            System.out.println(cursor.getString(cursor.getColumnIndex(HomeStayDbHelper.GUEST_DATE)));
//            cursor.moveToNext();
//        }


        fragmentManager = getFragmentManager();
        guestListFragment = (GuestListFragmentImpl) fragmentManager.findFragmentById(R.id.guest_list_fragment);
        guestListFragment.setPassToGuest(this);



        guestPresenter= new GuestPresenterImpl(this);

//        guestListFragment.
    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("resume load");
//
        guestPresenter.loadGuestData();
//        Intent intent = getIntent();
//        finish();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("restarted");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("paused");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("started");
    }



    @Override
    public void getGuestName(String name) {
        System.out.println("Name in activity "+name);
        guestPresenter.validateGuest(name);
//        reloadActivity();
    }

    private void reloadActivity() {
        Intent intent = getIntent();
        finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    public void getEditGuestName(Integer id, String name) {
        guestPresenter.validateEditGuest(id,name);
        guestPresenter.loadGuestData();
//        reloadActivity();


    }

    @Override
    public void guestNotAddedSnack() {
        Snackbar.make(coordinatorLayout,"Name not added",Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void guestAddedSnack() {
        Snackbar.make(coordinatorLayout,"Name added",Snackbar.LENGTH_SHORT).show();

//        reloadActivity();
    }

    @Override
    public void passCursorToFragment(Cursor cursor) {
        if (guestListFragment!=null)
        guestListFragment.passCursor(cursor);
    }


    @Override
    public void passCoordinateLayout(CoordinatorLayout coordinatorLayout) {
        System.out.println("coord came");
        this.coordinatorLayout= coordinatorLayout;
    }

    @Override
    public void passListInfo(Integer id,String data) {
        guestOptionFragment = (GuestOptionFragment) fragmentManager.findFragmentById(R.id.guest_option_fragment);
        if (guestOptionFragment!=null && guestOptionFragment.isVisible()){
        guestOptionFragment.changeData(id,data);
        }
        else{
            Intent intent = new Intent(GuestActivity.this, GuestOptionActivity.class);
            intent.putExtra("data",data);
            intent.putExtra("id",id);
            startActivity(intent);
        }
    }

    @Override
    public void passEditInfo(Integer id, String name) {
        GuestEditFragment gef=null;
        if (gef==null) {
            gef=new GuestEditFragment();
            Bundle b = new Bundle();
            b.putInt("id", id);
            b.putString("name", name);
            gef.setArguments(b);

            gef.show(getFragmentManager(), "EEE");
        }
    }
}
