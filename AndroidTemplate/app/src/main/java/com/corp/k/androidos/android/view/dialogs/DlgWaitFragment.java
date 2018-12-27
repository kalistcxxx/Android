package com.corp.k.androidos.android.view.dialogs;

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.corp.k.androidos.R;


import java.lang.ref.WeakReference;
import java.util.EventListener;

public class DlgWaitFragment extends DialogFragment {
    public static final long WAIT_TIMEOUT_DEFAULT = 90000;
    private static final String KEY_TIMEOUT = "timeout";
    private static final String KEY_STRING = "string";
    private static final String KEY_START_TIME = "startTime";
    private static final long MIN_DIALOG_SHOW_TIME = 500;
    private static String TAG = DlgWaitFragment.class.getSimpleName();
    private WeakReference<Activity> sAct = null;
    private OnWaitDialogListener mListener;
    private Handler mHandler = new Handler();
    private long mVisibleTimeMillis;
    private int mShowDelayMillis = -1;
    private boolean isDismissing;

    private Runnable mTimeoutTask = new Runnable() {
        @Override
        public void run() {
            if (mListener != null) {
                String tag = getTag();
                if (tag != null) {
                    mListener.onTimeoutWaitDialog(tag);
                }
            }
            dismiss();
        }
    };

    public synchronized Activity getCurrentActivity() {
        return sAct.get();
    }

    public synchronized void setCurrentAct(Activity act) {
        sAct = new WeakReference<>(act);
    }


    public static DlgWaitFragment newInstance(boolean cancelable, long timeout) {

        DlgWaitFragment dialogFragment = new DlgWaitFragment();
        dialogFragment.setCancelable(cancelable);
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_TIMEOUT, timeout);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    public static DlgWaitFragment newInstance(boolean cancelable, long timeout, Fragment fragment) {
        DlgWaitFragment dialogFragment = new DlgWaitFragment();
        dialogFragment.setCancelable(cancelable);
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_TIMEOUT, timeout);
        dialogFragment.setArguments(bundle);
        dialogFragment.setTargetFragment(fragment, 0);
        return dialogFragment;
    }

    public static DlgWaitFragment newInstance(boolean cancelable, long timeout, Fragment fragment, String string) {
        DlgWaitFragment dialogFragment = newInstance(cancelable, timeout, fragment);
        Bundle bundle = dialogFragment.getArguments();
        bundle.putString(KEY_STRING, string);
        return dialogFragment;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (sAct == null || sAct.get().isDestroyed()) {
            return;
        }

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setTargetFragment(null, -1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {

        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mTimeoutTask);
    }

    @Override
    public void dismiss() {
        if (sAct == null || sAct.get().isDestroyed()) {
            return;
        }

        if (isDismissing) {
            return;
        }

        if (isVisibleDialog()) {
            long didVisibleTimeMillis = System.currentTimeMillis() - mVisibleTimeMillis;
            if (didVisibleTimeMillis < MIN_DIALOG_SHOW_TIME) {
                isDismissing = true;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissAllowingStateLoss();
                    }
                }, MIN_DIALOG_SHOW_TIME - didVisibleTimeMillis);
                return;
            }
        }

        dismissAllowingStateLoss();
    }

    private boolean isVisibleDialog() {
        Dialog dialog = getDialog();
        if (dialog == null) {
            return false;
        }

        View view = dialog.findViewById(R.id.dialog);
        return view != null && view.getVisibility() == View.VISIBLE;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        { // 呼び出し元からリスナーを取得
            mListener = null;
            Fragment targetFragment = getTargetFragment();
            if (targetFragment != null) {
                if (targetFragment instanceof OnWaitDialogListener) {
                    mListener = (OnWaitDialogListener) targetFragment;
                }
            } else {
                Activity activity = getActivity();
                if (activity instanceof OnWaitDialogListener) {
                    mListener = (OnWaitDialogListener) activity;
                }
            }
        }

        { // タイムアウトの設定
            Bundle args = getArguments();
            long timeout = args.getLong(KEY_TIMEOUT);
            long startTime = args.getLong(KEY_START_TIME, 0);
            long currentTime = System.currentTimeMillis();
            long passedTime = currentTime - startTime;
            if (startTime != 0) {
                timeout = timeout - passedTime;
            }
            args.putLong(KEY_START_TIME, currentTime);
            setTimeout(timeout);
        }

        Dialog dialog = new Dialog(getActivity(), R.style.custom_progress_dialog);
        dialog.setContentView(R.layout.dlg_progress);

        setUpTextView((TextView) dialog.findViewById(R.id.text));

//        visibleAfterOneSecond(dialog.findViewById(R.id.dialog));
        visibleAfterHalfSecond(dialog.findViewById(R.id.dialog)); /* quick response --> imporove user feeling */

//        BackgroundColorUtility.SetColor(dialog.findViewById(R.id.dialog), R.color.white_wait_background);

        return dialog;
    }

    private void setUpTextView(TextView textView) {
        Bundle args = getArguments();
        String string = args.getString(KEY_STRING);
        if (string == null) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(string);
        }
    }

    private void visibleAfterOneSecond(final View view) {
        if (mShowDelayMillis == -1) {
            mShowDelayMillis = 1000;
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                mVisibleTimeMillis = System.currentTimeMillis();
            }
        }, mShowDelayMillis);
    }

    private void visibleAfterHalfSecond(final View view) {
        if (mShowDelayMillis == -1) {
            mShowDelayMillis = 100;
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                mVisibleTimeMillis = System.currentTimeMillis();
            }
        }, mShowDelayMillis);
    }


    private void setTimeout(long timeout) {
        mHandler.postDelayed(mTimeoutTask, timeout);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mListener != null) {
            String tag = getTag();
            if (tag != null) {
                mListener.onCancelWaitDialog(tag);
            }
        }
    }

    public int getShowDelayMillis() {
        return mShowDelayMillis;
    }

    public void setShowDelayMillis(int mShowDelayMillis) {
        this.mShowDelayMillis = mShowDelayMillis;
    }

    public interface OnWaitDialogListener extends EventListener {
        void onCancelWaitDialog(String tag);
        void onTimeoutWaitDialog(String tag);
    }
}
