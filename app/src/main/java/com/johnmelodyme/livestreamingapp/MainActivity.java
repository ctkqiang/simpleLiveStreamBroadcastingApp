package com.johnmelodyme.livestreamingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.muddzdev.styleabletoast.StyleableToast;
import com.wowza.gocoder.sdk.api.WowzaGoCoder;
import com.wowza.gocoder.sdk.api.broadcast.WOWZBroadcast;
import com.wowza.gocoder.sdk.api.broadcast.WOWZBroadcastConfig;
import com.wowza.gocoder.sdk.api.devices.WOWZAudioDevice;
import com.wowza.gocoder.sdk.api.devices.WOWZCameraView;
import com.wowza.gocoder.sdk.api.errors.WOWZError;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LiveStream";
    private WowzaGoCoder goCoder;
    private WOWZCameraView goCoderCameraView;
    private WOWZAudioDevice goCoderAudioDevice;
    private WOWZBroadcast goCoderBroadcaster;
    private WOWZBroadcastConfig goCoderBroadcastConfig;
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    private boolean mPermissionsGranted = true;
    private String[] mRequiredPermissions = new String[] {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting Application");

        // Initialize the GoCoder SDK
        goCoder = WowzaGoCoder.init(getApplicationContext(),
                "GOSK-8247-010C-1BE3-1E81-4D4B");

        if (goCoder == null) {
            // If initialization failed, retrieve the last error and display it
            WOWZError goCoderInitError = WowzaGoCoder.getLastError();

            new StyleableToast
                    .Builder(MainActivity.this)
                    .text("GoCoder SDK error: " + goCoderInitError.getErrorDescription())
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.rgb(	49,64,44))
                    .show();
        }
    }

    // Enable Android's immersive, sticky full-screen mode
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        View rootView = getWindow()
                .getDecorView()
                .findViewById(android.R.id.content);
        if (rootView != null)
            rootView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
