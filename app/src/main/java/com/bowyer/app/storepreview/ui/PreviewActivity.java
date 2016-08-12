package com.bowyer.app.storepreview.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bowyer.app.storepreview.R;
import com.bowyer.app.storepreview.preference.DataPreference;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

public class PreviewActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

  private static String KEY_SHORT_DESCRIPTION = "key_short_description";
  private static String KEY_DESCRIPTION = "key_description";

  @Bind(R.id.scrollView) ObservableScrollView scrollView;
  @Bind(R.id.short_description) TextView shortDescription;
  @Bind(R.id.description) TextView description;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.fab) FloatingActionButton fab;

  private String textShort;
  private String textDescription;

  private DataPreference mPrefs;

  public static void startActivity(Context context, String textShort, String textDescription) {
    Intent intent = new Intent(context, PreviewActivity.class);
    intent.putExtra(KEY_SHORT_DESCRIPTION, textShort);
    intent.putExtra(KEY_DESCRIPTION, textDescription);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preview);
    ButterKnife.bind(this);
    mPrefs = new DataPreference(this);
    scrollView.setScrollViewCallbacks(this);
    initToolbar();
    initData();
  }

  private void initToolbar() {
    toolbar.setTitle(R.string.app_name);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationIcon(R.drawable.ic_close);
  }

  private void initData() {
    Intent intent = getIntent();

    textShort = intent.getStringExtra(KEY_SHORT_DESCRIPTION);
    textDescription = intent.getStringExtra(KEY_DESCRIPTION);

    shortDescription.setText(textShort);
    String parceDescription = textDescription.replaceAll("\\n", "<br>");
    description.setText(Html.fromHtml(parceDescription));
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_preview, menu);
    return super.onCreateOptionsMenu(menu);
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
      case R.id.action_share:
        share();
        return true;
    }
    return false;
  }

  @OnClick(R.id.fab) void save() {
    mPrefs.saveShortText(textShort);
    mPrefs.saveSescriptionText(textDescription);
    Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    finish();
  }

  private void share() {
    String message = getString(R.string.share_text, textShort, textDescription);
    ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(this);
    builder.setChooserTitle(getString(R.string.title_data_share));
    builder.setText(message);
    builder.setType("text/plain");
    builder.startChooser();
  }

  @Override public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

  }

  @Override public void onDownMotionEvent() {

  }

  @Override public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    if (scrollState == ScrollState.UP) {
      fab.hide();
    } else {
      fab.show();
    }
  }
}
