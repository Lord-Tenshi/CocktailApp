package org.htw_berlin.fb4.cocktailapp.network.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FoundBTDevicesBroadcastReceiver extends BroadcastReceiver {

    private final BluetoothConnector btConnector;

    public FoundBTDevicesBroadcastReceiver(BluetoothConnector btConnector) {
        this.btConnector = btConnector;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(BluetoothDevice.ACTION_FOUND.equals(action)){
            Log.d(getLogStart(), "onReceive: found intent");
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String deviceName = device.getName();
            String deviceMACAddress = device.getAddress();
            Log.d(getLogStart(), deviceName + ":" +deviceMACAddress);

            BluetoothClass btClass = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);
            Log.d(getLogStart(), "found BT class: " + btClass.toString());

            btConnector.deviceFound(device, btClass);
        }
    }
    private String getLogStart() {
        return this.getClass().getSimpleName();
    }
}
