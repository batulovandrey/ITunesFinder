package com.github.batulovandrey.itunesfinder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by batul0ve on 04.08.2017.
 */

public class Utils {
    private Utils() {
        throw new IllegalStateException("Can't create object");
    }

    /**
     * Method to check network accessibility
     *
     * @param context Context
     * @return true if there is connection, false otherwise
     */

    public static boolean hasConnection(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        return connected;
    }
}
