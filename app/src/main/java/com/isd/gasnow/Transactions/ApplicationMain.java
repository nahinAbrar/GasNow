package com.isd.gasnow.Transactions;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class ApplicationMain extends Application implements UpdateSelectedItems{

    private static Context context;
    ArrayList<OrderListModel> orderListModels;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        orderListModels = new ArrayList<>();
    }

    public static Context getMyContext(){
        return  context;
    }

    @Override
    public void addItems(String name, int price) {
        orderListModels.add(new OrderListModel(name,price));
    }

    @Override
    public ArrayList<OrderListModel> getItems() {
        return orderListModels;
    }
}
