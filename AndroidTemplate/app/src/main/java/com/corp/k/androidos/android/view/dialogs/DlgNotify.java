package com.corp.k.androidos.android.view.dialogs;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.corp.k.androidos.R;

public class DlgNotify extends DlgAbsBase implements OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private Callbacks mCallbacks;
    private String mMessage;
    private String mTitle;
    private TextView mTextviewTitle;

    public DlgNotify() {
    }

    public void init(String title, String message, Callbacks callbacks) {
        this.mTitle = title;
        this.mMessage = message;
        this.mCallbacks = callbacks;
    }

    public void init(Context context, String message, Callbacks callbacks) {
        init(context.getString(R.string.str_error), message, callbacks);
    }

    @Override
    void onSetting(View vRoot) {
        setCancelable(false);
    }

    @Override
    void init(View vRoot) {
        mTextviewTitle = vRoot.findViewById(R.id.tvTitle);
        mTextviewTitle.setText(mTitle);

        TextView tvMessage = vRoot.findViewById(R.id.tvMesage);
        tvMessage.setText(mMessage);

        Button btnOk = vRoot.findViewById(R.id.btnOK);
        btnOk.setOnClickListener(this);
    }

    @Override
    int getLayoutID() {
        return R.layout.dlg_notify;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                dismiss();
                if (mCallbacks != null) {
                    mCallbacks.clickBtnOK();
                }
                break;
            default:
                break;
        }
    }

    public void setTitle(String str) {
        if (mTextviewTitle != null) {
            mTextviewTitle.setText(str);
        }
    }

    public interface Callbacks {
        void clickBtnOK();
    }
}
