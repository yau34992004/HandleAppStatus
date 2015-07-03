package com.rex.handleappstatus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLoadingProgress();

        ((MyApplication) getApplication()).getBus().register(this);
    }

    private void updateLoadingProgress() {
        TextView textView = (TextView) findViewById(R.id.textView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        
        OperationModel operationModel = OperationModel.getInstance();
        progressBar.setProgress(operationModel.getProgress());
        textView.setText(operationModel.getState());
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((MyApplication) getApplication()).getBus().unregister(this);
    }

    @Subscribe
    public void onUpdateProgress(LoadingEvent loadingEvent) {
        updateLoadingProgress();
    }

}
