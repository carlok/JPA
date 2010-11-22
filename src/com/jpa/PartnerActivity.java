/*
 * Copyright (C) 2007 Carlo Perassi <carlo@perassi.org>
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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PartnerActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partner);

		// latitude longitude;

		JPAApplication appState = ((JPAApplication) getApplicationContext());
		int pid = appState.pid;

		Button btnBack = (Button) findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), FindActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});

		TextView tv_name = (TextView) findViewById(R.id.partner_name);
		String name;
		if (appState.partners.get(pid).company.length() == 0) {
			name = appState.partners.get(pid).name + " "
					+ appState.partners.get(pid).surname;
		} else {
			name = appState.partners.get(pid).company;
		}
		tv_name.setText(String.format(this.getString(R.string.partner_name),
				name, appState.partners.get(pid).from));

		TextView tv_percentage = (TextView) findViewById(R.id.partner_percentage);
		tv_percentage.setText(String.format(this
				.getString(R.string.partner_percentage), appState.partners
				.get(pid).paid_in, appState.partners.get(pid).shares,
				appState.partners.get(pid).percentage));

		TextView tv_phone = (TextView) findViewById(R.id.partner_phone);
		tv_phone.setText(String.format(this.getString(R.string.partner_phone),
				appState.partners.get(pid).phone));

		TextView tv_email = (TextView) findViewById(R.id.partner_email);
		tv_email.setText(String.format(this.getString(R.string.partner_email),
				appState.partners.get(pid).email));

		TextView tv_url = (TextView) findViewById(R.id.partner_url);
		tv_url.setText(String.format(this.getString(R.string.partner_url),
				appState.partners.get(pid).url));
	}
}