package com.risakokato.zerosupport;

import android.app.Application;

import io.realm.Realm;

public class ZeroSupport extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(getApplicationContext());

    }
}
