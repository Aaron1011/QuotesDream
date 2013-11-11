package com.aaronhill.quotesdream;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Void;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class GetQuotesTask extends AsyncTask<Integer, Void, Void> {
	private Context myCtx;
	private Quote quote;
	private String quoteUrl = "http://www.iheartquotes.com/api/v1/random?format=json&source=science";

	@Override
	protected Void doInBackground(Integer... a) {
		int numQuotes = a[0];
		for (int i = 0; i < numQuotes; i++) {
			Object result = parseJSON(downloadUrl(quoteUrl));
			if (result != null) {
				quote = (Quote) result;
				Log.d("Quote", quote.toString());
				quote.save();
			}
		}
/*		QuotesDbHelper helper = new QuotesDbHelper(myCtx);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Quote.COLUMN_NAME_QUOTE_ID, 1);
		values.put(Quote.COLUMN_NAME_BODY, "Hi");
		values.put(Quote.COLUMN_NAME_AUTHOR, "No one");

		db.insert(Quote.TABLE_NAME, "null", values);*/
		return null;

	}

	private Object parseJSON(String json) {
		try {
			Log.d("JSON", json.toString());
			JSONObject jObject = new JSONObject(json);
			String body = jObject.getString("quote");
			Quote quote = new Quote(myCtx);
			String[] parsed = body.split("--");
			quote.body = parsed[0];
			if (parsed.length == 2) {
				quote.author = parsed[1];
			}
			else {
				quote.author = "Anonymous";
			}
			return quote;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String downloadUrl(String url) {
		InputStream is = null;
		String line = null;
		try {
			Log.d("Fetching quote", url);
			URL downloadUrl = new URL(url);
		    HttpURLConnection conn = (HttpURLConnection) downloadUrl.openConnection();
		    conn.setReadTimeout(10000 /* milliseconds */);
		    conn.setConnectTimeout(15000 /* milliseconds */);
		    conn.setRequestMethod("GET");
		    conn.setDoInput(true);
		    // Starts the query
		    conn.connect();
		    int response = conn.getResponseCode();
		    Log.d("Code", "Hi");
		    if (response == 200) {
		    	Log.d("Code", "200");
		    	is = conn.getInputStream();
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		    	StringBuilder sb = new StringBuilder();
		    	while ((line = reader.readLine()) != null) {
		            sb.append(line);
		        }
		    	return sb.toString();
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if (is != null) {
					is.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public GetQuotesTask (Context ctx) {
	    myCtx = ctx;
	}

}
