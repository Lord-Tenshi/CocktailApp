package org.htw_berlin.fb4.cocktailapp.network.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;

public class ClientSocketThread extends Thread {
    private BluetoothSocket btSocket;
    private final BluetoothConnector btConnector;

    ClientSocketThread(BluetoothConnector btConnector, BluetoothDevice btDevice){
        this.btConnector = btConnector;
    }

    public void run(){
        try{
            btSocket.connect();

        } catch (IOException e) {
            Log.e(getLogStart(),"could not connect: " + e.getLocalizedMessage());
        }
    }

    private String getLogStart() {
        return this.getClass().getSimpleName();
    }
}
