package com.example.zitate;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class ZitateApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);

    }
}
