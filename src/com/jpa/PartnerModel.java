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

public class PartnerModel {
	public String company;
	public String name;
	public String surname;
	public String percentage;
	public String paid_in;
	public String shares;
	public String from;
	public String email;
	public String url;
	public String phone;
	public String latitude;
	public String longitude;

	public PartnerModel(String company, String name, String surname,
			String percentage, String paid_in, String shares, String from,
			String email, String url, String phone, String latitude,
			String longitude) {
		this.company = company;
		this.name = name;
		this.surname = surname;
		this.percentage = percentage;
		this.paid_in = paid_in;
		this.shares = shares;
		this.from = from;
		this.email = email;
		this.url = url;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
