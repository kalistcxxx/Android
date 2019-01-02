package com.corp.k.androidos.android.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.corp.k.androidos.R;
import com.corp.k.androidos.android.models.enums.DialogType;
import com.corp.k.androidos.android.notification.NotificationBase;
import com.corp.k.androidos.android.utils.SharedPreference;
import com.corp.k.androidos.android.view.dialogs.DlgFactory;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationBase test = new NotificationBase(this);
//        test.sendNotifications("Test","TEst 2", MainActivity2.class);
//
//        SharedPreference.getInstance().putString("abc", "cba");


        DlgFactory.getInstance().setActivity(this);

//        AlertDialog dialog = DlgFactory.getInstance().getDialog(DialogType.DLG_NEUTRAL, R.layout.activity_main, "", "", new DlgFactory.DlgNeutral(){
//            @Override
//            public void onPositiveClick() {
////                super.onPositiveClick();
//                Log.i("Go here", "Go here");
//            }
//
//            @Override
//            public void onNeutralClick() {
////                super.onNeutralClick();
//                Log.i("Go here", "Go here 2");
//            }
//        });
//        dialog.show();
        View v = getCurrentFocus();
    }

    int count = 0;
    private final Object obj = new Object();

    public int getInteger(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                OnCounting c = new OnCounting() {
                    @Override
                    public int onCounted() {
                        return count;
                    }
                };
                try {
                    Thread.sleep(1000);
                    count++;
//                    return c.onCounted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return 0;
    }

    interface OnCounting{
        public int onCounted();
    }
}
