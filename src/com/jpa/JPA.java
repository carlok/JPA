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

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class JPA extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Reusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		intent = new Intent().setClass(this, FindActivity.class);
		spec = tabHost.newTabSpec("find").setIndicator(
				this.getString(R.string.tab_name_find),
				res.getDrawable(R.drawable.ic_tab_find)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SettingsActivity.class);
		spec = tabHost.newTabSpec("settings").setIndicator(
				this.getString(R.string.tab_name_settings),
				res.getDrawable(R.drawable.ic_tab_settings)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, CreditsActivity.class);
		spec = tabHost.newTabSpec("credits").setIndicator(
				this.getString(R.string.tab_name_credits),
				res.getDrawable(R.drawable.ic_tab_credits)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTabByTag("find");
	}
}