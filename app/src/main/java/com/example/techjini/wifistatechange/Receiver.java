package com.example.techjini.wifistatechange;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by dhruv on 12/3/17.
 */

public class Receiver extends BroadcastReceiver {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        WifiManager wifiManager = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (networkInfo != null && wifiManager != null) {

            if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                sendCheckInNotification("CONNECTED*****");
            } else if (networkInfo.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                sendCheckInNotification("DISCONNECTED*****");
            }
        }
    }

    private void sendCheckInNotification(String contentText) {
        int mNotificationId = 001;
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(mContext.getString(R.string.app_name))
                        .setContentText(contentText)
                        .setAutoCancel(true);
        NotificationManager mNotifyMgr =
                (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
