package com.homestay.bipin.guest.guestList.view;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.homestay.bipin.R;
import com.homestay.bipin.data.HomeStayDbHelper;
import com.homestay.bipin.guest.fragment.guestAdd.GuestAddFragment;
import com.homestay.bipin.guest.fragment.guestAdd.GuestEditFragment;
import com.homestay.bipin.guest.fragment.guestList.GuestListFragment;
import com.homestay.bipin.guest.fragment.guestList.GuestListFragmentImpl;
import com.homestay.bipin.guest.fragment.guestOption.GuestOptionFragment;
import com.homestay.bipin.guest.guestList.Guest;
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
    ActionMode actionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        ButterKnife.bind(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);


        fragmentManager = getFragmentManager();
        guestListFragment = (GuestListFragmentImpl) fragmentManager.findFragmentById(R.id.guest_list_fragment);
        guestListFragment.setPassToGuest(this);


        guestPresenter = new GuestPresenterImpl(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
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
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void getGuestName(String name) {


        guestPresenter.validateGuest(name);
        guestListFragment.nullFragment();
        // guestPresenter.loadGuestData();
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
        guestPresenter.validateEditGuest(id, name);
        guestPresenter.loadGuestData();
//        reloadActivity();
    }

    @Override
    public void guestNotAddedSnack() {
        Snackbar snack=Snackbar.make(coordinatorLayout, "Name not added", Snackbar.LENGTH_SHORT);
        View view=snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.RED);
        snack.show();

    }

    @Override
    public void guestAddedSnack() {
        Snackbar.make(coordinatorLayout, "Name added", Snackbar.LENGTH_SHORT).show();
        guestPresenter.loadGuestData();

//        reloadActivity();
    }

    @Override
    public void guestDeletedSnack() {
        Snackbar.make(coordinatorLayout,"Guest Deleted Successfully",Snackbar.LENGTH_SHORT).show();
        guestPresenter.loadGuestData();
    }

    @Override
    public void guestNotDeletedSnack() {
        Snackbar snack=Snackbar.make(coordinatorLayout,"Guest Deleted Successfully",Snackbar.LENGTH_SHORT);
        View view=snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.RED);
        snack.show();

    }

    @Override
    public void passCursorToFragment(Cursor cursor) {

        if (guestListFragment != null)
            guestListFragment.passCursor(cursor);
    }


    @Override
    public void passCoordinateLayout(CoordinatorLayout coordinatorLayout) {

        this.coordinatorLayout = coordinatorLayout;
    }

    @Override
    public void passListInfo(Integer id, String data) {
        guestOptionFragment = (GuestOptionFragment) fragmentManager.findFragmentById(R.id.guest_option_fragment);
        if (guestOptionFragment != null && guestOptionFragment.isVisible()) {
            guestOptionFragment.changeData(id, data);
        } else {
            Intent intent = new Intent(GuestActivity.this, GuestOptionActivity.class);
            System.out.println("la etai");
            intent.putExtra("data", data);
            intent.putExtra("id", id);

            startActivity(intent);
        }
    }

    @Override
    public void passEditInfo(Guest guest) {

        clearContextualMenu();
        GuestEditFragment gef = new GuestEditFragment();
        Bundle b = new Bundle();
        b.putInt("id", guest.getId());
        b.putString("name", guest.getName());
        gef.setArguments(b);
        gef.show(getFragmentManager(), "EEE");


    }

    @Override
    public void passDeleteInfo(final Guest guestInfo) {
        clearContextualMenu();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure you want to delete "+guestInfo.getName() +" ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getDeleteOk(guestInfo);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();

    }

    @Override
    public void clearContextualMenu() {
        if (actionMode!=null){
            actionMode.finish();
        }
    }

    @Override
    public void getDeleteOk(Guest guestInfo) {
        Log.d("D","delete ok "+guestInfo.getName());
        guestPresenter.validateDelete(guestInfo);

    }

    @Override
    public void openContextualMenu(Guest guestInfo) {
         actionMode=GuestActivity.this.startActionMode(new MyContextualCallback(guestInfo));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    class MyContextualCallback implements ActionMode.Callback{

        Guest guest;
        public MyContextualCallback(Guest guest){
            this.guest=guest;

        }
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_guest_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Edit Guest");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.edit:
                    passEditInfo(guest);

                    break;
                case R.id.delete:
                    passDeleteInfo(guest);
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mode.invalidate();
        }
    }
}
