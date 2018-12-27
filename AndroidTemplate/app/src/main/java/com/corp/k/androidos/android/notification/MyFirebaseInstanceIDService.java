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


//public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
//
//    private final String TAG = "MyFirebaseIIDService";
//    private final String PUSH_ID = "PushNotificationID";
//
//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//        sendRegistrationToServer(refreshedToken);
//    }
//    // [END refresh_token]
//
//    /**
//     * @param token The new token.
//     */
//    private void sendRegistrationToServer(String token) {
//        Log.e(TAG, "sendRegistrationToServer: " + token);
//        SharedPreference.getInstance().putString(PUSH_ID, token);
//        if(!UchiNetApp.getInstance().getUser().getBuilderId().isEmpty()){
//            handleRegistrationToServer(token, true);
//        }
//    }
//
//    private void handleRegistrationToServer(final String token, final boolean retry) {
//        SessionController.getInstance().registerPushNotification(token, new ICommonCallback() {
//            @Override
//            public void onSuccess() {
//                Log.i("Push Notification", "Successfully");
//                //Toast.makeText(getApplicationContext(),"Set push notification token successfully!!!", Toast.LENGTH_SHORT).show();
//                SharedPreference.getInstance().putBoolean("PushNotificationStatus", true);
//            }
//
//            @Override
//            public void onError() {
//                Log.i("Push Notification", "Failed");
//                //Toast.makeText(getApplicationContext(),"Failed to set push notification!!!", Toast.LENGTH_SHORT).show();
//                if (retry) {
//                    handleRegistrationToServer(token, false);
//                } else {
//                    SharedPreference.getInstance().putBoolean("PushNotificationStatus", false);
//                }
//            }
//        });
//    }
//
//
//}
