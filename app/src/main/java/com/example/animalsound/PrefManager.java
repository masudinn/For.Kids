package com.example.animalsound;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "welcome";
    private static final String IS_FIRST_LAUNCH = "isFirstLaunch";


    public PrefManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstLaunchTime(Boolean isFirstTime) {
    editor.putBoolean(IS_FIRST_LAUNCH,isFirstTime);
    editor.commit();
    }

    public boolean isFirstLaunch() {
        return pref.getBoolean(IS_FIRST_LAUNCH,true);
    }
}
