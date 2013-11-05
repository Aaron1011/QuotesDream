package com.aaronhill.quotesdream;

import java.util.Iterator;
import java.util.List;
import com.aaronhill.quotesdream.Quote;

import android.os.AsyncTask;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

public class UpdateQuoteTask extends AsyncTask<TextView, Quote, Void> {
	TextView textView;

	protected Void doInBackground(TextView... arg0) {

		this.textView = (TextView) arg0[0];
		while (true) {
			for (Quote quote: (List<Quote>) Quote.listAll(Quote.class)) {
				publishProgress(quote);
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					return null;
				}
			}
		}
	}

	protected void onProgressUpdate(Quote... values) {
		Quote quote = values[0];

		textView.setText(quote.body + "\n--" + quote.author, TextView.BufferType.SPANNABLE);
		Spannable str = (Spannable) textView.getText();
		str.setSpan(new AbsoluteSizeSpan(50, true), 0, str.toString().lastIndexOf("\n"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		str.setSpan(new AbsoluteSizeSpan(30, true), str.toString().lastIndexOf("\n") + 1, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

	}

}
