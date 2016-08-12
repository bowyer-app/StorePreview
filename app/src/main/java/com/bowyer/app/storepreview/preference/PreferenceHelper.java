package com.bowyer.app.storepreview.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
  private final SharedPreferences mSharedPreferences;

  public PreferenceHelper(Context context, String name, int mode) {
    mSharedPreferences = context.getSharedPreferences(name, mode);
  }

  protected final boolean getBoolean(String key, boolean defVal) {
    return mSharedPreferences.getBoolean(key, defVal);
  }

  protected void putBoolean(String key, boolean val) {
    mSharedPreferences.edit().putBoolean(key, val).apply();
  }

  protected String getString(String key, String defVal) {
    return mSharedPreferences.getString(key, defVal);
  }

  protected void putString(String key, String val) {
    mSharedPreferences.edit().putString(key, val).apply();
  }

  protected int getInt(String key, int defVal) {
    return mSharedPreferences.getInt(key, defVal);
  }

  protected void putInt(String key, int val) {
    mSharedPreferences.edit().putInt(key, val).apply();
  }
}
