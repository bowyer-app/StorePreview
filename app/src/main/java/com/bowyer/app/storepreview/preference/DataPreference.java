package com.bowyer.app.storepreview.preference;

import android.content.Context;

public class DataPreference extends PreferenceHelper {
  private static final String DATA_PREFERENCES = "data_preferences";
  private static final String KEY_SHORT_TEXT = "key_short_text";
  private static final String KEY_DESCRIPTION_TEXT = "key_description_text";

  public DataPreference(Context context) {
    super(context, DATA_PREFERENCES, Context.MODE_PRIVATE);
  }

  public String getShortText() {
    return getString(KEY_SHORT_TEXT, "");
  }

  public void saveShortText(String shortText) {
    putString(KEY_SHORT_TEXT, shortText);
  }

  public String getDescriptionText() {
    return getString(KEY_DESCRIPTION_TEXT, "");
  }

  public void saveSescriptionText(String descriptionText) {
    putString(KEY_DESCRIPTION_TEXT, descriptionText);
  }
}
