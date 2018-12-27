package com.corp.k.androidos.android.view.customviews;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.corp.k.androidos.android.utils.CommonUtils;


/**
 * Created by hoangtuan on 11/9/16.
 */
public class EditTextCurrency implements TextWatcher {
    EditText editText;


    public EditTextCurrency(EditText editText) {
        this.editText = editText;


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try
        {
            editText.removeTextChangedListener(this);
            String value = editText.getText().toString();


            if (value != null && !value.equals(""))
            {

                if(value.startsWith(".")){
                    editText.setText("0.");
                }
                if(value.startsWith("0") && !value.startsWith("0.")){
                    editText.setText("");

                }


                String str = editText.getText().toString().replaceAll(",", "");
                if (!value.equals(""))
                    editText.setText(CommonUtils.getDecimalFormattedString(str));
                editText.setSelection(editText.getText().toString().length());
            }
            editText.addTextChangedListener(this);
            return;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            editText.addTextChangedListener(this);
        }

    }



}
