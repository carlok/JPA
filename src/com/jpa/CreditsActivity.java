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
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class CreditsActivity extends Activity {
	TextView tCredits;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credits);

		TextView tv_jpa_description = (TextView) findViewById(R.id.credits_jpa_description);
		tv_jpa_description.setText(this
				.getString(R.string.credits_jpa_description));

		TextView tv_jpa_url = (TextView) findViewById(R.id.credits_jpa_url);
		tv_jpa_url.setAutoLinkMask(Linkify.WEB_URLS);
		tv_jpa_url.setText(this.getString(R.string.credits_jpa_url));

		TextView tv_jpa_copyright = (TextView) findViewById(R.id.credits_jpa_copyright);
		tv_jpa_copyright
				.setText(this.getString(R.string.credits_jpa_copyright));

		TextView tv_carlo_url = (TextView) findViewById(R.id.credits_carlo_url);
		tv_carlo_url.setAutoLinkMask(Linkify.WEB_URLS);
		tv_carlo_url.setText(this.getString(R.string.credits_carlo_url));
	}
}