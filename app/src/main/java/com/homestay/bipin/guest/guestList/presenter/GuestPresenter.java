package com.homestay.bipin.guest.guestList.presenter;

import com.homestay.bipin.guest.guestList.Guest;

/**
 * Created by Bipin on 4/26/17.
 */

public interface GuestPresenter {


    void validateGuest(String name);

    void validateEditGuest(Integer id, String name);

    void loadGuestData();

    void validateDelete(Guest guest);


}
