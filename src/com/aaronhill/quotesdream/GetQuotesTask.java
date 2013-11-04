package com.aaronhill.quotesdream;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import com.aaronhill.quotesdream.QuotesDbHelper;
import com.aaronhill.quotesdream.QuotesContract.*;
import java.lang.Void;

public class GetQuotesTask extends AsyncTask<Void, Void, Void> {
	private Context myCtx;

	@Override
	protected Void doInBackground(Void... a) {
/*		QuotesDbHelper helper = new QuotesDbHelper(myCtx);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Quote.COLUMN_NAME_QUOTE_ID, 1);
		values.put(Quote.COLUMN_NAME_BODY, "Hi");
		values.put(Quote.COLUMN_NAME_AUTHOR, "No one");

		db.insert(Quote.TABLE_NAME, "null", values);*/
		return null;

	}

	public GetQuotesTask (Context ctx) {
	    myCtx = ctx;
	}

}
