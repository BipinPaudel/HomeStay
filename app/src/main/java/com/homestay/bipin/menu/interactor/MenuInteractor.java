package com.homestay.bipin.menu.interactor;

import android.database.Cursor;

/**
 * Created by Bipin on 4/29/17.
 */

public interface MenuInteractor {

    void loadMenu(OnMenuLoadedListener listener);

    interface OnMenuLoadedListener{
        void OnMenuLoadSuccess(Cursor cursor);
        void onMenuLoadError();
    }
}
