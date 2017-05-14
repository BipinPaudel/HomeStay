package com.homestay.bipin.guest.guestList.presenter;

import android.database.Cursor;

import com.homestay.bipin.guest.guestList.Guest;
import com.homestay.bipin.guest.guestList.interactor.GuestInteractor;
import com.homestay.bipin.guest.guestList.interactor.GuestInteractorImpl;
import com.homestay.bipin.guest.guestList.view.GuestView;

/**
 * Created by Bipin on 4/26/17.
 */

public class GuestPresenterImpl implements GuestPresenter,GuestInteractor.OnGuestAddedListener,GuestInteractor.OnGuestLoadedListener,
GuestInteractor.OnGuestDeletedListener{


    GuestView guestView;
    GuestInteractor guestInteractor;


    public GuestPresenterImpl(GuestView guestView){
        this.guestView = guestView;
        guestInteractor=new GuestInteractorImpl(guestView);
    }
    @Override
    public void validateGuest(String name) {
        if (guestView!=null){

        }
        guestInteractor.addGuestToDatabase(name,this);


    }

    @Override
    public void validateEditGuest(Integer id, String name) {
        guestInteractor.editGuestToDatabase(id,name,this);
    }

    @Override
    public void loadGuestData() {
        System.out.println("inside presenter load data");
        guestInteractor.loadGuest(this);
    }

    @Override
    public void validateDelete(Guest guest) {
        guestInteractor.deleteGuestInDatabase(guest.getId(),this);
    }

    @Override
    public void onGuestAddSuccess() {
        if (guestView!=null){
            guestView.guestAddedSnack();

        }


    }

    @Override
    public void onGuestAddError() {
        if (guestView!=null){
            guestView.guestNotAddedSnack();
        }

    }

    @Override
    public void onGuestLoadedSuccess(Cursor cursor) {
        guestView.passCursorToFragment(cursor);
    }

    @Override
    public void OnGuestLoadedError() {

    }

    @Override
    public void onGuestDeleteSuccess() {
        if (guestView!=null){
            guestView.guestDeletedSnack();
        }
    }

    @Override
    public void onGuestDeleteError() {
        if (guestView!=null){
            guestView.guestNotDeletedSnack();
        }
    }
}
