package com.aaronhill.quotesdream;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.service.dreams.DreamService;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuotesDream extends DreamService {
	UpdateQuoteTask task;

	List<Quote> QUOTES = new ArrayList<Quote>();
	SharedPreferences sharedPref;


	@Override
    public void onAttachedToWindow() {
		super.onAttachedToWindow();
 		sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		/*SharedPreferences.Editor editor = sharedPref.edit();


		editor.putInt(getString(R.string.quote_display_time), 4000);
		editor.commit();*/

		QUOTES.add(new Quote("To be or not to be, that is the question. ", "William Shakespeare"));
		QUOTES.add(new Quote("As far as the laws of mathematics refer to reality, they are not certain, and as far as they are certain, they do not refer to reality. ", "Alert Einstein"));
		QUOTES.add(new Quote("The economy depends about as much on economists as the weather does on weather forecasters. ", "Jean-Paul Kauffmann"));

		for (Quote quote: QUOTES) {
			quote.save();
		}
		ConnectivityManager connMgr = (ConnectivityManager)
		        getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			new GetQuotesTask().execute(20);
		}

	   // Hide system UI
		setFullscreen(true);
		// Set the dream layout
		setContentView(R.layout.quotes_dream);
	}

	@Override
	public void onDreamingStarted() {

		final TextView textView = (TextView) findViewById(R.id.textView1);

		task = new UpdateQuoteTask();
		task.execute(textView, sharedPref.getInt(getString(R.string.quote_display_time), 5) * 1000);

	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		Quote.deleteAll(Quote.class);
		task.cancel(true);
	}
}
