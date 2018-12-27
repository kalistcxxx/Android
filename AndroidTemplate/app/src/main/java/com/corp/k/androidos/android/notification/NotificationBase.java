package com.corp.k.androidos.android.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import com.corp.k.androidos.R;
import com.corp.k.androidos.kotlin.example.questionfeed.HomeActivity;

import java.lang.ref.WeakReference;

public class NotificationBase extends Builder{
    private WeakReference<Activity> act;
    private int currentId;
    private int smallIcon = R.mipmap.ic_launcher;
    private Uri defaultSoundUri;
    private long defaultWhen;

    public NotificationBase(Activity activity){
        super(activity);
        this.act = new WeakReference<>(activity);
        defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        defaultWhen = System.currentTimeMillis();
    }

    public NotificationBase setSmallIcon(int icon){
        this.smallIcon = icon;
        return this;
    }

    public NotificationBase setSoundUri(Uri uriSound){
        this.defaultSoundUri = uriSound;
        return this;
    }

    public NotificationBase setId(int i){
        if(i==-1) currentId ++;
        else currentId = i;
        return this;
    }

    public void sendNotifications(String messageBody, String title) {
        Intent notificationIntent = new Intent();
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(act.get(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Builder notificationBuilder = new Builder(act.get())
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setTicker("")
                .setWhen(defaultWhen)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) act.get().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(currentId, notificationBuilder.build());
    }

    public void sendNotifications(String messageBody, String title, Class<?> tartgetActivity){
        Intent notificationIntent = new Intent();
        notificationIntent = new Intent(act.get().getApplicationContext(), tartgetActivity);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(act.get(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Builder notificationBuilder = new Builder(act.get())
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setTicker("")
                .setWhen(defaultWhen)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) act.get().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(currentId, notificationBuilder.build());
    }

}
