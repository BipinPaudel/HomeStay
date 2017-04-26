package com.homestay.bipin.guest.guestList.presenter;

/**
 * Created by Bipin on 4/26/17.
 */

public interface GuestPresenter {


    void validateGuest(String name);

    void validateEditGuest(Integer id, String name);

    void loadGuestData();


}
