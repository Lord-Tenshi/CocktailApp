package org.htw_berlin.fb4.cocktailapp.network;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothConnector implements IBluetoothConnector {
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothConnector connector = null;
    private BluetoothAdapter mBluetoothAdapter;
    private Activity activity;

    BluetoothConnector(Context context, Activity activity) {

        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.i(this.getLogStart(), "device does not support bluetooth");
        }
        this.activity = activity;
    }

    private String getLogStart() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void start() {
        Log.d(this.getLogStart(), "start bluetooth");
        this.setup();
    }
    @Override
    public void stop() {
        Log.d(this.getLogStart(), "stop bluetooth");
        
            this.shutdown();
        
        }

    private void shutdown() {
        /// TODO: 27.05.2020 implement
    }




    private void setup() {
        Log.d(this.getLogStart(), "check if BT is enabled");
        if (!mBluetoothAdapter.isEnabled()) {
            Log.i(this.getLogStart(), "Bluetooth disabled - ask application to start BT");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }
    }

}
