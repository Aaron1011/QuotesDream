package com.aaronhill.quotesdream;

import com.orm.SugarRecord;


public class Quote extends SugarRecord {
	String body;
	String author;

	public Quote() {
	}

	public Quote(String body, String author) {
		this.body = body;
		this.author = author;
	}
}
