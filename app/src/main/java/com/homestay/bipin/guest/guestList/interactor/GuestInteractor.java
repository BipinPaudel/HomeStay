package com.homestay.bipin.guest.guestList.interactor;

import android.database.Cursor;

/**
 * Created by Bipin on 4/26/17.
 */

public interface GuestInteractor {


    interface OnGuestAddedListener{
        void onGuestAddSuccess();
        void onGuestAddError();
    }

    interface OnGuestLoadedListener{
        void onGuestLoadedSuccess(Cursor cursor);
        void OnGuestLoadedError();
    }

    void addGuestToDatabase(String name,OnGuestAddedListener listener);
    void editGuestToDatabase(Integer id,String name,OnGuestAddedListener listener);

    void loadGuest(OnGuestLoadedListener listener);
}
