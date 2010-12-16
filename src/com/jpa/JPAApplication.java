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

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

public class JPAApplication extends Application {
	MyHttpClient mhc = new MyHttpClient();
	public ArrayList<PartnerModel> partners = new ArrayList<PartnerModel>();
	public ArrayList<String> partnersS = new ArrayList<String>();
	public int pid = 0;

	public ArrayList<PartnerModel> getPartners() {
		return partners;
	}

	public ArrayList<String> getDbAuth() {
		DbModel db = new DbModel(getApplicationContext());
		ArrayList<String> s = new ArrayList<String>();
		db.open();

		Cursor c = db.getDbAuth();

		int urlCol = c.getColumnIndex(DbModel.MetaData.JPA_URL_KEY);
		int passwordCol = c.getColumnIndex(DbModel.MetaData.JPA_PASSWORD_KEY);
		c.moveToFirst();
		s.add(c.getString(urlCol));
		s.add(c.getString(passwordCol));

		db.close();

		return s;
	}

	public void setDbAuth() {
		DbModel db = new DbModel(getApplicationContext());
		db.open();

		Cursor c = db.fetch();

		if (c.moveToFirst() == false) {
			db.insert(this.getString(R.string.jpa_default_url), this
					.getString(R.string.jpa_default_password));
		}

		db.close();
	}

	public void setPartners() {
		try {
			partners.clear();

			ArrayList<String> s = this.getDbAuth();
			JSONArray entries = new JSONArray(mhc.getContent(s.get(0) + "?p="
					+ s.get(1)));
			for (int i = 0; i < entries.length(); i++) {
				JSONObject post = entries.getJSONObject(i);
				partners.add(new PartnerModel(post.getString("company"), post
						.getString("name"), post.getString("surname"), post
						.getString("percentage"), post.getString("paid_in"),
						post.getString("shares"), post.getString("from"), post
								.getString("email"), post.getString("url"),
						post.getString("phone"), post.getString("latitude"),
						post.getString("longitude")));
			}

		} catch (Exception je) {
			Log.d("Error w/file: ", je.getMessage());
		}
	}
}