package com.homestay.bipin.guest.guestList.view;

import android.database.Cursor;

/**
 * Created by Bipin on 4/26/17.
 */

public interface GuestView {

    void getGuestName(String name);

    void getEditGuestName(Integer id, String name);

    void guestNotAddedSnack();

    void guestAddedSnack();

    void passCursorToFragment(Cursor cursor);







}
