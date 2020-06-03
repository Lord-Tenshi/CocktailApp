package org.htw_berlin.fb4.cocktailapp.network;

import android.util.Log;

public interface IBluetoothConnector {
    /**
     * enables Bluetooth if possible and starts discovery
     */
    public void start();

    /**
     * stops discovery and disables Bluetooth
     */
    public void stop();
}
