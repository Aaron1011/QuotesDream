package com.aaronhill.quotesdream;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.service.dreams.DreamService;
import android.util.Log;
import android.widget.TextView;

import com.aaronhill.quotesdream.GetQuotesTask;
import com.aaronhill.quotesdream.Quote;

public class QuotesDream extends DreamService {
	Quote newQuote;
	UpdateQuoteTask task;
	@Override
    public void onAttachedToWindow() {
		super.onAttachedToWindow();

		// Exit dream upon user touch
		setInteractive(true);
	   // Hide system UI
		setFullscreen(true);
		// Set the dream layout
		setContentView(R.layout.quotes_dream);



		Quote quote = new Quote(getBaseContext(), "This is a blah", "Aaron");
		quote.save();

		Quote quote2 = new Quote(getBaseContext(), "This is a second quote", "Anonymous");
		quote2.save();
	}

	@Override
	public void onDreamingStarted() {

		List<Quote> newQuotes = Quote.listAll(Quote.class);
		final TextView textView = (TextView) findViewById(R.id.textView1);
		final Iterator iterator = newQuotes.iterator();
		int delay = 2000;
		task = new UpdateQuoteTask();
		task.execute(newQuotes, textView);


	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		Quote.deleteAll(Quote.class);
	}
	public void onDreamingStopped() {
		task.cancel(true);
	}
}
