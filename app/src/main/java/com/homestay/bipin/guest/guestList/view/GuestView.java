package com.homestay.bipin.guest.guestList.view;

import android.database.Cursor;

import com.homestay.bipin.guest.guestList.Guest;

/**
 * Created by Bipin on 4/26/17.
 */

public interface GuestView {

    void getGuestName(String name);

    void passEditInfo(Guest guestInfo);

    void passDeleteInfo(Guest guestInfo);


    void clearContextualMenu();

    void getDeleteOk(Guest guestInfo);

    void getEditGuestName(Integer id, String name);

    void guestNotAddedSnack();

    void guestAddedSnack();

    void guestDeletedSnack();
    void guestNotDeletedSnack();

    void passCursorToFragment(Cursor cursor);







}
