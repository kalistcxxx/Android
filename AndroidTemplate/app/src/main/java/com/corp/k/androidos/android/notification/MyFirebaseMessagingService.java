/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.corp.k.androidos.android.notification;

//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    private static final String TAG = "MyFirebaseMsgService";
//    public static final String INTENT_FILTER = "INTENT_FILTER";
//    /**
//     * Called when message is received.
//     *
//     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
//     */
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//        }
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getTitle());
//            String title = remoteMessage.getNotification().getTitle()==null?"":remoteMessage.getNotification().getTitle();
//            String message = remoteMessage.getNotification().getBody()==null?"":remoteMessage.getNotification().getBody();
//            sendNotifications(message, title);
//            Intent intent = new Intent(INTENT_FILTER);
//            sendBroadcast(intent);
//        }
////        String message = null;
////        try {
////            message = remoteMessage.getData().get("notification");
////            JSONObject jsonObject = new JSONObject(message);
////            message = jsonObject.getString("body");
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
////        if(message!=null && !message.isEmpty()) {
////            sendNotifications(message, "");
////        }
//
//    }
//
//    private void sendNotifications(String messageBody, String title) {
//        Intent notificationIntent = new Intent();
////        notificationIntent = new Intent(this, HomeActivity.class);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setTicker("")
//                .setWhen(System.currentTimeMillis())
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0, notificationBuilder.build());
//    }
//
//    //eOF8idG0W_w:APA91bGNDvXU1H4Bg2yhgHYAM3HBZY-mezvrZ-SYr_U6Tmf-RA7Inmtc7iR0ZA8dLFRRBaqtOivlZhN8zCXzXrBh1RM7my6ml_cMo9_Rnb4vET22KHWDotL_OCNkaMINXrOIffmlEM39
//}
