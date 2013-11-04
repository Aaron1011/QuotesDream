package com.aaronhill.quotesdream;
import android.content.Context;

import com.orm.SugarRecord;


public class Quote extends SugarRecord<Quote> {
	String body;
	String author;

	public Quote(Context ctx) {
		super(ctx);
	}

	public Quote(Context ctx, String body, String author) {
		super(ctx);
		this.body = body;
		this.author = author;
	}

}
