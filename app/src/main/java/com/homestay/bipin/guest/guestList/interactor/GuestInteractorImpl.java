package com.homestay.bipin.guest.guestList.interactor;

import android.content.Context;
import android.database.Cursor;

import com.homestay.bipin.guest.guestList.GuestDataBaseAdapter;
import com.homestay.bipin.guest.guestList.view.GuestView;

/**
 * Created by Bipin on 4/26/17.
 */

public class GuestInteractorImpl implements GuestInteractor{

    Context guestView;
    GuestDataBaseAdapter guestDataBaseAdapter;

    public GuestInteractorImpl(GuestView guestView){
        this.guestView = (Context) guestView;
        guestDataBaseAdapter = new GuestDataBaseAdapter(this.guestView);
    }
    @Override
    public void addGuestToDatabase(final String name,final OnGuestAddedListener listener) {
        if (name.trim().equals("")){
            listener.onGuestAddError();
            return;
        }

        long id=guestDataBaseAdapter.insertGuest(name,0,0,0);
        System.out.println("This is id "+id);
        if (id<0){

            listener.onGuestAddError();

        }else{
            listener.onGuestAddSuccess();
        }

    }

    @Override
    public void editGuestToDatabase(final Integer id, final String name, final OnGuestAddedListener listener){
        if (name.trim().equals("")){
            listener.onGuestAddError();
            return;
        }
        long result= guestDataBaseAdapter.editGuest(id,name);
        System.out.println("this is edit result "+result);
        if (result<0){
            listener.onGuestAddError();
        }
        else{
            listener.onGuestAddSuccess();
        }

    }

    @Override
    public void loadGuest(OnGuestLoadedListener listener) {
        System.out.println("inside load Guest");
        Cursor cursor = guestDataBaseAdapter.getAllGuests();
        listener.onGuestLoadedSuccess(cursor);
    }


}
