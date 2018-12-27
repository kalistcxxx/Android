package com.corp.k.androidos.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.corp.k.androidos.android.TempApplication;

/**
 * Created by hoangtuan on 11/9/15.
 * Class for handling SharedPreference.
 */

public class SharedPreference {
    private final String SETTING_NAME = TempApplication.getAppContext().getApplicationContext().getPackageName();;
    private static SharedPreference _Instance;// = new SharedPreference();
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mAppEdit = false;
    private final String UTF8 = "utf-8";
    private final String KEY_ENCRYPT = "PBEWithMD5AndDES";
    private static final char[] SEKRIT = {};

    public synchronized static SharedPreference getInstance(Context context) {
        if(_Instance == null)
            _Instance = new SharedPreference(context);
        return _Instance;
    }

    public synchronized static SharedPreference getInstance() {
        if(_Instance == null){
            _Instance = new SharedPreference(TempApplication.getAppContext());
        }
        return _Instance;
    }

    private SharedPreference(Context context) {
        mPref = context.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
    }

    public void edit(){
        mAppEdit = true;
        mEditor = mPref.edit();
    }

    public void commit(){
        mAppEdit = false;
        mEditor.commit();
        mEditor = null;
    }
    public void clear(){
        doEdit();
        mEditor.clear();
        doCommit();
    }

    private void doEdit(){
        if(!mAppEdit && mEditor == null){
            mEditor = mPref.edit();
        }
    }
    private void doCommit(){
        if(!mAppEdit && mEditor!=null){
            mEditor.commit();
            mEditor = null;
        }
    }
    //Boolean
    public void putBoolean(String key, boolean value){
        doEdit();
        mEditor.putBoolean(key, value);
        doCommit();
    }

    public Boolean getBoolean(String key, boolean defaultValue){
        if(mPref!=null) {
            return mPref.getBoolean(key, defaultValue);
        }
        else
            return defaultValue;
    }
    //Int
    public void putInt(String key, int value){
        doEdit();
        mEditor.putInt(key, value);
        doCommit();
    }

    public int getInt(String key, int defaultValue){
        if(mPref!=null) {
            return mPref.getInt(key, defaultValue);
        }
        else
            return defaultValue;
    }

    //String
    public void putString(String key, String value){
        doEdit();
        mEditor.putString(key, encrypt(value));
        doCommit();
    }

    public String getString(String key, String defaultValue){
        if(mPref!=null) {
            String result = mPref.getString(key, defaultValue);
            if(!result.isEmpty()) {
                return decrypt(mPref.getString(key, defaultValue));
            }
            return result;
        }
        else
            return defaultValue;
    }

    //String no encrypt
    public void putStrings(String key, String value){
        doEdit();
        mEditor.putString(key, value);
        doCommit();
    }

    public String getStrings(String key, String defaultValue){
        if(mPref!=null) {
            String result = mPref.getString(key, defaultValue);
            if(!result.isEmpty()) {
                return mPref.getString(key, defaultValue);
            }
            return result;
        }
        else
            return defaultValue;
    }

    //Float
    public void putFloat(String key, float value){
        doEdit();
        mEditor.putFloat(key, value);
        doCommit();
    }

    public float getFloat(String key, float defaultValue){
        if(mPref!=null) {
            return mPref.getFloat(key, defaultValue);
        }
        else
            return defaultValue;
    }

    public void putFloat(String key, long value){
        doEdit();
        mEditor.putFloat(key, value);
        doCommit();
    }

    public long getFloat(String key, long defaultValue){
        if(mPref!=null) {
            return mPref.getLong(key, defaultValue);
        }
        else
            return defaultValue;
    }

    /**
     *
     * @param value
     * @return
     */
    protected String encrypt(String value ) {
        try {
            final byte[] bytes = value!=null ? value.getBytes(UTF8) : new byte[0];
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ENCRYPT);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(SEKRIT));
            Cipher pbeCipher = Cipher.getInstance(KEY_ENCRYPT);
            pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(Settings.Secure.getString(TempApplication.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID).getBytes(UTF8), 20));
            return new String(Base64.encode(pbeCipher.doFinal(bytes), Base64.NO_WRAP),UTF8);
        } catch( Exception e ) {
            throw new RuntimeException(e);

        }

    }

    /**
     *
     * @param value
     * @return
     */
    protected String decrypt(String value){
        try {
            final byte[] bytes = value!=null ? Base64.decode(value,Base64.DEFAULT) : new byte[0];
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ENCRYPT);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(SEKRIT));
            Cipher pbeCipher = Cipher.getInstance(KEY_ENCRYPT);
            pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(Settings.Secure.getString(TempApplication.getAppContext().getContentResolver(),Settings.Secure.ANDROID_ID).getBytes(UTF8), 20));
            return new String(pbeCipher.doFinal(bytes),UTF8);
        } catch( Exception e) {
            throw new RuntimeException(e);
        }
    }
}
