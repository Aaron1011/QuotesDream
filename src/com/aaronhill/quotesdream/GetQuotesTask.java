package com.aaronhill.quotesdream;

import android.os.AsyncTask;
import android.os.Debug;
import android.text.Html;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetQuotesTask extends AsyncTask<Integer, Void, Void> {
	private Quote quote;
	private String quoteUrl = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=%d";

	@Override
	protected Void doInBackground(Integer... a) {
		try {
			parseJSON(downloadUrl(String.format(quoteUrl, a[0])));
		} catch (JSONException e) {
			Log.e("Quote", "Error parsing quote", e);
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

	private void parseJSON(String json) throws JSONException {
		Log.d("JSON", json.toString());
		JSONArray array = new JSONArray(json);
		for (int i = 0; i < array.length(); i++) {
			JSONObject quoteJson = array.getJSONObject(i);

			String author = quoteJson.getString("title");
            String body = Html.fromHtml(quoteJson.getString("content")).toString().replaceAll("([\n])*\\z", "");
			new Quote(body, author).save();
		}
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
}
