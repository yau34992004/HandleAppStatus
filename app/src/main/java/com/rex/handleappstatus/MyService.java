package com.rex.handleappstatus;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by rex.yau on 6/30/2015.
 */
public class MyService extends IntentService {

    private static final String TAG = "MyService";

    public MyService() {
        this("MyService");
    }


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        for (int i = 0; i < 100; i++) {
            OperationModel.getInstance().setState(OperationModel.LOADING);
            OperationModel.getInstance().setProgress(i);
            Log.d(TAG,"checkProgress->"+i);
            ((MyApplication) getApplication()).getHandler().post(new Runnable() {
                @Override
                public void run() {
                    ((MyApplication) getApplication()).getBus().post(new LoadingEvent());
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        OperationModel.getInstance().setState(OperationModel.COMPLETE);

    }
}
