package com.corp.k.androidos.android.view.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.corp.k.androidos.R;
import com.corp.k.androidos.android.models.enums.DialogType;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DlgFactory extends DialogFragment {
    private final String TAG = DlgFactory.class.getSimpleName();
    private static DlgFactory instance = new DlgFactory();
    private WeakReference<Activity> act;

    public static DlgFactory getInstance(){
        if(instance == null){
            instance = new DlgFactory();
        }
        return instance;
    }

    public void setActivity(Activity activity){
        act = new WeakReference<>(activity);
    }

    public AlertDialog getDialog(DialogType type, int v, String title, String content, OnDlgListener listener){
        switch (type){
            case DLG_OK:
                return showOKDialog(v, title, content, listener);
            case DLG_NEUTRAL:
                return showNeutralDialog(v, title, content, listener);
            case DLG_OK_CANCEL:
                return showOKCancelDialog(v, title, content, listener);
            default:
                break;
        }
        return null;
    }

    public <T extends Object> AlertDialog getDialogMultiData(DialogType type, int v, String title, String content, OnDlgListener listener, ArrayList<T> listData){
        switch (type){
            case DLG_LIST:
                return showListDialog(v, title, content, listener, listData);
            case DLG_MULTI_SELECT:
                return showMultiDialog(v, title, content, listener, listData);
            case DLG_SINGLE_SELECT:
                return showSingleDialog(v, title, content, listener, listData);
            default:
                break;
        }
        return null;
    }

    private <T extends Object> AlertDialog showSingleDialog(int v, String title, String content, OnDlgListener listener, ArrayList<T> listData) {
        return null;
    }

    private <T extends Object> AlertDialog showMultiDialog(int v, String title, String content, OnDlgListener listener, ArrayList<T> listData) {
        return null;
    }

    private <T extends Object> AlertDialog showListDialog(int v, String title, String content, OnDlgListener listener, ArrayList<T> listData) {
        return null;
    }

    private AlertDialog showOKCancelDialog(int v, String title, String content, final OnDlgListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act.get());
        builder.setTitle(title).setMessage(content)
                .setPositiveButton(R.string.dlg_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((DlgOKCancel)listener).onPositiveClick();
                    }})
                .setNegativeButton(R.string.dlg_Cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((DlgOKCancel)listener).onNegativeClick();
                    }
                });
        builder.setView(v);
        return builder.create();
    }

    private AlertDialog showNeutralDialog(int v, String title, String content, final OnDlgListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act.get());
        builder.setTitle(title).setMessage(content)
                .setPositiveButton(R.string.dlg_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((DlgNeutral)listener).onPositiveClick();
                    }})
                .setNegativeButton(R.string.dlg_Cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((DlgNeutral)listener).onPositiveClick();
                    }
                })
                .setNeutralButton(R.string.dlg_Neutral, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((DlgNeutral)listener).onNeutralClick();
                    }
                });
        builder.setView(v);
        return builder.create();
    }

    private AlertDialog showOKDialog(int v, String title, String content, final OnDlgListener listener) {
//        ((DlgOK)listener).onPositiveClick();
        AlertDialog.Builder builder = new AlertDialog.Builder(act.get());
        builder.setTitle(title).setMessage(content).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((DlgOK)listener).onPositiveClick();
            }
        });
        builder.setView(v);
        return builder.create();
    }

    interface OnDlgListener{

    }

    public static class DlgOK implements OnDlgListener{
        void onPositiveClick(){ }
    }

    public static class DlgOKCancel implements OnDlgListener{
        void onPositiveClick(){ }
        void onNegativeClick(){ }
    }

    public static class DlgNeutral implements OnDlgListener{
        void onPositiveClick(){ }
        void onNegativeClick(){ }
        void onNeutralClick(){ }
    }
}
