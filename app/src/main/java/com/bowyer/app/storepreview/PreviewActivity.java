package com.bowyer.app.storepreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PreviewActivity extends AppCompatActivity {

  private static String KEY_SHORT = "key_short";
  private static String KEY_DESCRIPTION = "key_description";

  @Bind(R.id.short_text) TextView shortText;
  @Bind(R.id.description) TextView description;
  @Bind(R.id.toolbar) Toolbar toolbar;

  public static void startActivity(Context context, String textShort, String textDescription) {
    Intent intent = new Intent(context, PreviewActivity.class);
    intent.putExtra(KEY_SHORT, textShort);
    intent.putExtra(KEY_DESCRIPTION, textDescription);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preview);
    ButterKnife.bind(this);
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

    String textShort = intent.getStringExtra(KEY_SHORT);
    String textDescription = intent.getStringExtra(KEY_DESCRIPTION);

    shortText.setText(textShort);
    String parceDescription = textDescription.replaceAll("\\n", "<br>");
    description.setText(Html.fromHtml(parceDescription));
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate menu resource file.
    return super.onCreateOptionsMenu(menu);
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
    }
    return false;
  }
}
