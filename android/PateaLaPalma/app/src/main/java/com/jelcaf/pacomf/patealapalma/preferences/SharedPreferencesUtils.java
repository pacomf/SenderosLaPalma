package com.jelcaf.pacomf.patealapalma.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.jelcaf.pacomf.patealapalma.R;

/**
 * Created by Paco on 26/03/2015.
 */
public class SharedPreferencesUtils {

    public static void addString (Activity activity, String id, String value){
        SharedPreferences sharedPref = activity.getSharedPreferences(activity.getString(R.string.sp_sharedpreferences), 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(id, value);
        editor.commit();
    }

    public static String getString (Activity activity, String id){
        SharedPreferences sharedPref = activity.getSharedPreferences(activity.getString(R.string.sp_sharedpreferences), 0);
        return sharedPref.getString(id, null);
    }
}
