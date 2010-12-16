/*
 * Copyright (C) 2010 Carlo Perassi <carlo@perassi.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpa;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	Button btnSave;
	TextView tUrl;
	TextView tPassword;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		final DbModel db = new DbModel(getApplicationContext());
		db.open();

		Cursor c = db.fetch(); // query
		startManagingCursor(c);

		int urlCol = c.getColumnIndex(DbModel.MetaData.JPA_URL_KEY);
		int passwordCol = c.getColumnIndex(DbModel.MetaData.JPA_PASSWORD_KEY);

		if (c.moveToFirst() == true) {
			// // se va alla prima entry, il cursor non è vuoto
			// do {
			// // estrazione dei dati dalla entry del cursor
			// a = "Product Name: " + c.getString(nameCol) + ", Price: "
			// + c.getInt(priceCol) + "\n";
			// } while (c.moveToNext());
		}

		tUrl = (TextView) findViewById(R.id.settings_url);
		tUrl.setText(this.getString(R.string.settings_url));
		final EditText etUrl = (EditText) findViewById(R.id.edit_url);
		etUrl.setText(c.getString(urlCol));

		tPassword = (TextView) findViewById(R.id.settings_password);
		tPassword.setText(this.getString(R.string.settings_password));
		final EditText etPassword = (EditText) findViewById(R.id.edit_password);
		etPassword.setText(c.getString(passwordCol));

		Button btnSave = (Button) findViewById(R.id.btnSave);

		btnSave.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// EditText editText = (EditText)findViewById(R.id.yourId);
				// String editTextStr = editText.getText().toString()
				// etPassword.setText("bbb");
				db.open(); // one more open?
				//db.insert(etUrl.getText().toString(), etPassword.getText().toString());
				db.update(1, etUrl.getText().toString(), etPassword.getText().toString());
				Toast.makeText(SettingsActivity.this, getString(R.string.tstSave),
						Toast.LENGTH_SHORT).show();
			}
		});
		
		db.close();
	}
}