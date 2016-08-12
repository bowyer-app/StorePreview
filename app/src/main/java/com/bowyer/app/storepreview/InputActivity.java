package com.bowyer.app.storepreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bowyer.app.fabtransitionlayout.FooterLayout;
import com.bowyer.app.storepreview.preference.DataPreference;
import com.crashlytics.android.Crashlytics;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import io.fabric.sdk.android.Fabric;
import petrov.kristiyan.colorpicker.ColorPicker;

public class InputActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

  private static final String KEY_DESCRIPTION = "key_description";

  @Bind(R.id.scrollView) ObservableScrollView scrollView;
  @Bind(R.id.short_description) EditText editShortDescription;
  @Bind(R.id.description) EditText editDescription;
  @Bind(R.id.fab) FloatingActionButton fab;
  @Bind(R.id.fabtoolbar) FooterLayout footerLayout;

  private DataPreference mPrefs;

  public static void startActivity(Context context, String description) {
    Intent intent = new Intent(context, InputActivity.class);
    intent.putExtra(KEY_DESCRIPTION, description);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fabric.with(this, new Crashlytics());
    setContentView(R.layout.activity_input);
    ButterKnife.bind(this);
    mPrefs = new DataPreference(this);
    initData();
    scrollView.setScrollViewCallbacks(this);
    footerLayout.setFab(fab);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_input, menu);
    return super.onCreateOptionsMenu(menu);
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_preview:
        showPreview();
        return true;
    }
    return false;
  }

  private void initData() {
    if (getIntent().hasExtra(KEY_DESCRIPTION)) {
      setDataFromIntent();
      return;
    }
    String shortText = mPrefs.getShortText();
    if (!TextUtils.isEmpty(shortText)) {
      editShortDescription.setText(shortText);
    }
    String descriptionText = mPrefs.getDescriptionText();
    if (!TextUtils.isEmpty(descriptionText)) {
      editDescription.setText(descriptionText);
    }
  }

  private void setDataFromIntent() {
    Intent intent = getIntent();
    Bundle extras = intent.getExtras();
    String description = extras.getString(KEY_DESCRIPTION);
    editDescription.setText(description);
  }

  private void showPreview() {
    String shortText = editShortDescription.getText().toString();
    String description = editDescription.getText().toString();
    if (TextUtils.isEmpty(shortText) || TextUtils.isEmpty(description)) {
      Toast.makeText(this, R.string.text_need, Toast.LENGTH_SHORT).show();
      return;
    }
    PreviewActivity.startActivity(this, shortText, description);
  }

  @OnClick(R.id.fab) void onClickFab() {
    footerLayout.expandFab();
  }

  @Override public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

  }

  @Override public void onDownMotionEvent() {

  }

  @Override public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    if (scrollState == ScrollState.UP) {
      if (!footerLayout.isFabExpanded()) {
        fab.hide();
      }
    } else if (scrollState == ScrollState.DOWN) {
      if (!footerLayout.isFabExpanded()) {
        fab.show();
      }
    }
  }

  @Override public void onBackPressed() {
    if (footerLayout.isFabExpanded()) {
      footerLayout.contractFab();
      return;
    }
    super.onBackPressed();
  }

  private void setTag(String tag) {
    int start = editDescription.getSelectionStart();
    int end = editDescription.getSelectionEnd();
    Editable editable = editDescription.getText();
    editable.replace(Math.min(start, end), Math.max(start, end), tag);
  }

  @OnClick(R.id.font) void addFontTag() {
    ColorPicker colorPicker = new ColorPicker(this);
    colorPicker.show();
    colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
      @Override public void onChooseColor(int position, int color) {
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        setTag(getString(R.string.font_hex, hexColor));
      }
    });
  }

  @OnClick(R.id.font_end) void addFontEndTag() {
    setTag(getString(R.string.font_end));
  }

  @OnClick(R.id.br) void addBrTag() {
    setTag(getString(R.string.br));
  }

  @OnClick(R.id.b) void addBTag() {
    setTag(getString(R.string.b));
  }

  @OnClick(R.id.b_end) void addBEndTag() {
    setTag(getString(R.string.b_end));
  }

  @OnClick(R.id.i) void addITag() {
    setTag(getString(R.string.i));
  }

  @OnClick(R.id.i_end) void addIEndTag() {
    setTag(getString(R.string.i_end));
  }

  @OnClick(R.id.u) void addUTag() {
    setTag(getString(R.string.u));
  }

  @OnClick(R.id.u_end) void addUEndTag() {
    setTag(getString(R.string.u_end));
  }

  @OnClick(R.id.a) void addATag() {
    setTag(getString(R.string.a));
  }

  @OnClick(R.id.a_end) void addAEndTag() {
    setTag(getString(R.string.a_end));
  }

  @OnClick(R.id.h1) void addH1Tag() {
    setTag(getString(R.string.h1));
  }

  @OnClick(R.id.h1_end) void addH1EndTag() {
    setTag(getString(R.string.h1_end));
  }

  @OnClick(R.id.h2) void addH2Tag() {
    setTag(getString(R.string.h2));
  }

  @OnClick(R.id.h2_end) void addH2EndTag() {
    setTag(getString(R.string.h2_end));
  }

  @OnClick(R.id.h3) void addH3Tag() {
    setTag(getString(R.string.h3));
  }

  @OnClick(R.id.h3_end) void addH3EndTag() {
    setTag(getString(R.string.h3_end));
  }

  @OnClick(R.id.h4) void addH4Tag() {
    setTag(getString(R.string.h4));
  }

  @OnClick(R.id.h4_end) void addH4EndTag() {
    setTag(getString(R.string.h4_end));
  }
}
