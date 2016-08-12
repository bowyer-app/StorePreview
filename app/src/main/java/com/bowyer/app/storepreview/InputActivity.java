package com.bowyer.app.storepreview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class InputActivity extends AppCompatActivity {

  @Bind(R.id.short_text) EditText editShort;
  @Bind(R.id.description) EditText editDescription;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_input);
    ButterKnife.bind(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate menu resource file.
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

  private void showPreview() {
    String shortText = editShort.getText().toString();
    String description = editDescription.getText().toString();
    if (TextUtils.isEmpty(shortText) || TextUtils.isEmpty(description)) {
      Toast.makeText(this, R.string.text_need, Toast.LENGTH_SHORT).show();
      return;
    }
    PreviewActivity.startActivity(this, shortText, description);
  }
}
