package com.bowyer.app.storepreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

public class LaunchActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    Bundle extras = intent.getExtras();
    CharSequence cs = extras.getCharSequence(Intent.EXTRA_TEXT);
    if (TextUtils.isEmpty(cs)) {
      return;
    }
    String text = cs.toString();
    InputActivity.startActivity(this, text);
    finish();
  }
}
