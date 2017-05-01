package com.homestay.bipin.menu.interactor;

import android.content.Context;
import android.database.Cursor;

import com.homestay.bipin.menu.MenuDatabaseAdapter;
import com.homestay.bipin.menu.view.FoodMenuView;

/**
 * Created by Bipin on 4/29/17.
 */

public class MenuInteractorImpl implements MenuInteractor {

    Context foodMenuView;
    MenuDatabaseAdapter menuDatabaseAdapter;

    public MenuInteractorImpl(FoodMenuView foodMenuView){
        this.foodMenuView = (Context) foodMenuView;
        menuDatabaseAdapter = new MenuDatabaseAdapter(this.foodMenuView);
    }
    @Override
    public void loadMenu(OnMenuLoadedListener listener){
        Cursor cursor = menuDatabaseAdapter.getMenuItems();
        listener.OnMenuLoadSuccess(cursor);
    }



}
