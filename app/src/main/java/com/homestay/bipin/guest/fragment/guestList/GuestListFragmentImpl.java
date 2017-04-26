package com.homestay.bipin.guest.fragment.guestList;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.homestay.bipin.R;
import com.homestay.bipin.RecyclerItemClickListener;
import com.homestay.bipin.data.HomeStayDbHelper;
import com.homestay.bipin.guest.fragment.guestAdd.GuestAddFragment;
import com.homestay.bipin.guest.fragment.guestAdd.GuestEditFragment;
import com.homestay.bipin.guest.guestList.Guest;
import com.homestay.bipin.guest.guestList.GuestAdapter;
import com.homestay.bipin.guest.guestList.GuestDataBaseAdapter;
import com.homestay.bipin.guest.guestList.interactor.GuestInteractor;
import com.homestay.bipin.guest.guestList.interactor.GuestInteractorImpl;
import com.homestay.bipin.guest.guestList.presenter.GuestPresenter;
import com.homestay.bipin.guest.guestList.view.GuestView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bipin on 4/26/17.
 */

public class GuestListFragmentImpl extends Fragment implements GuestListFragment{

    FloatingActionButton fab;

    CoordinatorLayout coordinatorLayout;
    PassListToGuestActivity passToGuest;

    RecyclerView recyclerView;
    GuestAdapter guestAdapter;
    LinearLayoutManager recyclerManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_guest_list, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coord);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        recyclerView = (RecyclerView) view.findViewById(R.id.guest_recycler_id);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuestAddFragment fr = new GuestAddFragment();
                fr.show(getFragmentManager(), "TTT");


            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        passToGuest.passCoordinateLayout(coordinatorLayout);

    }

    public void setPassToGuest(PassListToGuestActivity passToGuest) {
        this.passToGuest= passToGuest;

    }

    @Override
    public void passCursor(Cursor cursor) {
        List<Guest> g = new ArrayList<>();
//        Cursor cursor = new GuestDataBaseAdapter(getActivity()).getAllGuests();
        while(!cursor.isAfterLast()) {
            Integer id =cursor.getInt(cursor.getColumnIndex(HomeStayDbHelper.GUEST_GID));
            String name = cursor.getString(cursor.getColumnIndex(HomeStayDbHelper.GUEST_NAME));
            String date = cursor.getString(cursor.getColumnIndex(HomeStayDbHelper.GUEST_DATE));
            Guest guest = new Guest(id,name,date);
            g.add(guest);
            System.out.println(guest.getName());
            cursor.moveToNext();
        }

        guestAdapter = new GuestAdapter(getActivity(),g);
        guestAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(guestAdapter);
        recyclerManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),recyclerView,
                new RecyclerItemClickListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {
                        Integer id = guestAdapter.getId(position).getId();
                        String name = guestAdapter.getId(position).getName();
                        passToGuest.passListInfo(id,name);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Integer id = guestAdapter.getId(position).getId();
                        String name = guestAdapter.getId(position).getName();
                        passToGuest.passEditInfo(id,name);

                    }
                }));
    }



    public interface PassListToGuestActivity {
        void passCoordinateLayout(CoordinatorLayout cl);

        void passListInfo(Integer id,String data);

        void passEditInfo(Integer id, String name);
    }

}
