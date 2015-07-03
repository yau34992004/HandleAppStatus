package com.rex.handleappstatus;

import android.app.Application;
import android.os.Handler;

import com.squareup.otto.Bus;

/**
 * Created by rex.yau on 6/30/2015.
 */
public class MyApplication extends Application {

    private Bus mBus;
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mBus = new Bus();
        mHandler = new Handler();
    }

    public Handler getHandler() {
        return mHandler;
    }

    public Bus getBus() {
        return mBus;
    }
}
