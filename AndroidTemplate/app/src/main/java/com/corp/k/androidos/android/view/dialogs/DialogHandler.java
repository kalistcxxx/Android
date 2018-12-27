/**
 *
 */
package com.corp.k.androidos.android.view.dialogs;

import android.app.Activity;
import com.corp.k.androidos.R;
import com.corp.k.androidos.android.utils.CommonUtils;
import com.corp.k.androidos.android.utils.LogUtils;
import com.corp.k.androidos.android.models.restapi.PBError;

import java.lang.ref.WeakReference;

/**

 */
public class DialogHandler implements DlgWaitFragment.OnWaitDialogListener, DlgNotify.Callbacks {
    public final static int ID_WAIT_DIALOG = 2;
    private final static int ID_ERR_DIALOG_ = 1;
    private static DialogHandler sInstance;
    private WeakReference<Activity> sAct = null;
    private final int WAIT_TIMEOUT = 600000;
    private DlgNotify mErrorDialog;
    private DlgWaitFragment mWaitDialog;


    private DialogHandler() {
        super();
    }

    private synchronized Activity getCurrentActivity() {
        return sAct.get();
    }

    public synchronized void setCurrentAct(Activity act) {
        sAct = new WeakReference<>(act);
    }

    public static synchronized DialogHandler getInstance() {
        if (sInstance == null) {
            sInstance = new DialogHandler();
            return sInstance;
        } else {
            return sInstance;
        }
    }

    /**
     * Display error dialog with given message
     */
    public void displayMessage(final int errCode, final String errMsg, final int featureID) {
        sAct.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (sAct != null) {
                    if (mErrorDialog != null) {
                        return;
                    }
                    try {
                        String mesg = createDisplayMsg(errCode, errMsg, featureID);
                        if (mesg != null && !mesg.isEmpty()) {
                            mErrorDialog = new DlgNotify();
                            mErrorDialog.init(sAct.get(), mesg, DialogHandler.this);
//                            mErrorDialog.show(sAct.get().getFragmentManager(), null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.e("DialogHandler", "displayMessage sAct is NULL");
                }
            }
        });
    }

    /**
     * Display error dialog with given message
     */
    public void displayWaitDialog() {
        sAct.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (sAct == null || mWaitDialog != null) {
                    LogUtils.d("DialogHandler", "Do not show dialog : act " + sAct + " mWaitdlg " + mWaitDialog);
                    return;
                }
                if (CommonUtils.isConnectedNetwork(sAct.get())) {
                    try {
                        mWaitDialog = DlgWaitFragment.newInstance(false, WAIT_TIMEOUT, null);
                        setCurrentAct(sAct.get());
                        mWaitDialog.show(sAct.get().getFragmentManager(), null);
                        LogUtils.d("DialogHandler", "show waiting dilaog");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private String createDisplayMsg(int errCode, String errMsg, int featureID) {
        String message = errMsg;
        if (getCurrentActivity() == null) {
            return message;
        }

        if (errCode >= 500) {//server error
            message = sAct.get().getString(R.string.msg_internal_server_error_500);
        } else if (errCode == PBError.ERR_HTTP_NO_INTERNET_CONNECTION.value()) {
            message = sAct.get().getString(R.string.no_internet_connection);
        } else {
            message = sAct.get().getString(R.string.msg_cannot_get_data_from_server);
        }

        return message;
    }

    @Override
    public void onCancelWaitDialog(String tag) {
        dismissDialog(ID_WAIT_DIALOG);
    }

    @Override
    public void onTimeoutWaitDialog(String tag) {
        dismissDialog(ID_WAIT_DIALOG);
    }

    @Override
    public void clickBtnOK() {
        dismissDialog(ID_ERR_DIALOG_);
    }


    public void dismissDialog(int which) {
        switch (which) {
            case ID_ERR_DIALOG_:
                if (mErrorDialog != null) {
                    mErrorDialog.dismiss();
                    mErrorDialog = null;
                }
                break;
            case ID_WAIT_DIALOG:
                if (mWaitDialog != null) {
                    mWaitDialog.dismiss();
                    mWaitDialog = null;
                }
                break;
            default:
                break;
        }
    }

    public void displayDialog(final String message) {
        sAct.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (sAct != null) {
                    if (mErrorDialog != null) {
                        return;
                    }
                    try {
                        if (message != null && !message.isEmpty()) {
                            mErrorDialog = new DlgNotify();
                            mErrorDialog.init(sAct.get(), message, DialogHandler.this);
//                            mErrorDialog.show(sAct.get().getFragmentManager(), null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.e("DialogHandler", "displayMessage sAct is NULL");
                }
            }
        });
    }
}
