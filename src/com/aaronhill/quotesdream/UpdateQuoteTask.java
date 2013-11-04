package com.aaronhill.quotesdream;

import java.util.Iterator;
import java.util.List;
import com.aaronhill.quotesdream.Quote;

import android.os.AsyncTask;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.widget.TextView;

public class UpdateQuoteTask extends AsyncTask<Object, Quote, Void> {
	TextView textView;

	protected Void doInBackground(Object... arg0) {
		List quotes = (List) arg0[0];
		this.textView = (TextView) arg0[1];
		while (true) {
			for (Quote quote: (List<Quote>) quotes) {
				publishProgress(quote);
				Log.d("Quote", quote.body);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					return null;
				}
			}
		}
	}

	protected void onProgressUpdate(Quote... values) {
		Quote quote = values[0];
		Log.d("Progress", "Hi");

		textView.setText(quote.body + "\n--" + quote.author, TextView.BufferType.SPANNABLE);
		Spannable str = (Spannable) textView.getText();
		str.setSpan(new AbsoluteSizeSpan(50, true), 0, str.toString().indexOf("\n"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		str.setSpan(new AbsoluteSizeSpan(30, true), str.toString().indexOf("\n") + 1, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

}
