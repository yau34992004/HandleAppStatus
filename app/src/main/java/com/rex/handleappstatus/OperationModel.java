package com.rex.handleappstatus;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rex.yau on 6/30/2015.
 */
public class OperationModel {

    private static OperationModel model;
    public static final String IDLE = "idle";
    public static final String COMPLETE = "complete";
    public static final String LOADING = "loading";

    private String mState;
    private int mProgress;

    @StringDef({IDLE, COMPLETE, LOADING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Progress {
    }

    public static OperationModel getInstance() {
        if (model == null) {
            model = new OperationModel();
        }
        return model;
    }

    private OperationModel() {
        mState = IDLE;
    }

    public int getProgress() {
        return mProgress;
    }

    @Progress
    public String getState() {
        return mState;
    }

    public void setProgress(int progress) {
        mProgress = progress;
    }

    public void setState(@Progress String state) {
        mState = state;
    }
}
