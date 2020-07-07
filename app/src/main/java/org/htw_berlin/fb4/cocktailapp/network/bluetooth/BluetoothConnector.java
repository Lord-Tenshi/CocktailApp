package org.htw_berlin.fb4.cocktailapp.network.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BluetoothConnector implements IBluetoothConnector {
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothConnector connector = null;
    private BluetoothAdapter mBluetoothAdapter;
    private Activity activity;

    public static final int DEFAULT_VISIBILITY_TIME = 120;
    public static int visibilityTimeInSeconds = DEFAULT_VISIBILITY_TIME;

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
    public void startDiscoverable(){
        this.startDiscoverable(BluetoothConnector.DEFAULT_VISIBILITY_TIME);
    }

    private void startDiscoverable(int time){
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, time);
//      activity.onActivityForResult(discoverableIntent);
    }
    private boolean startDiscovery(){
        if(this.mBluetoothAdapter.startDiscovery()){
            Log.d(this.getLogStart(), "started Bluetooth discovery");
            return true;
        }else {
            Log.e(this.getLogStart(), "couldn't start Bluetooth discovery");
            return false;
        }
    }


    private void setup() {
        Log.d(this.getLogStart(), "check if BT is enabled");
        if (!mBluetoothAdapter.isEnabled()) {
            Log.i(this.getLogStart(), "Bluetooth disabled - ask application to start BT");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }
    }

    private Map<String, BluetoothSocket> openSockets = new HashMap<>();

    synchronized void handleBTSocket(BluetoothSocket socket) throws IOException{
        String address = socket.getRemoteDevice().getAddress();

        BluetoothSocket bluetoothSocket = openSockets.get(address);
        if(bluetoothSocket != null){

            if(bluetoothSocket.isConnected()){
                Log.d(getLogStart(), "connection already open and active " + address);
                socket.close();
                return;
            }else{
                Log.d(this.getLogStart(), "connection was there, but is gone now " + address);
            }
        }

        this.openSockets.put(address, socket);

        start();
    }
    void deviceFound(BluetoothDevice btDevice, BluetoothClass btClass) {
        mBluetoothAdapter.cancelDiscovery();

        Log.d(this.getLogStart(), "create a BT client socket thread");
        new ClientSocketThread(this, btDevice).start();
    }

}
