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

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * TODO: AsyncTask o Thread per la parte di calcolo (examineJSONFile)
 * TODO: l'annoso problema del filtro su di un ArrayAdapter opportuno
 * TODO: icona settings corretta
 * TODO: in examineJSONFile, rimuovere get e chiamata diretta all'array
 * TODO: tre campi "attivi" email, phone, url
 */
public class FindActivity extends ListActivity {
	ListView lv;

	private ArrayList<String> partners = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find);

		examineJSONFile();
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, partners));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				JPAApplication appState = ((JPAApplication) getApplicationContext());
				String[] selected = parent.getItemAtPosition(position)
						.toString().split(" - ");
				appState.pid = Integer.parseInt(selected[0]);
				// Toast.makeText(getApplicationContext(), selected[0],
				// Toast.LENGTH_SHORT).show();
				Intent myIntent = new Intent(view.getContext(),
						PartnerActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	void examineJSONFile() {
		JPAApplication appState = ((JPAApplication) getApplicationContext());

		appState.setPartners();

		ArrayList<PartnerModel> p = appState.getPartners();
		int length = p.size();
		for (int i = 0; i < length; i++) {
			String s = "" + i + " - ";
			if (p.get(i).company.length() == 0) {
				s += p.get(i).name + " " + p.get(i).surname;
			} else {
				s += p.get(i).company;
			}
			partners.add(s);
		}
	}
}