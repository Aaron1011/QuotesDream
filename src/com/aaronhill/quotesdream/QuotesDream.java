package com.aaronhill.quotesdream;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

	List<Quote> QUOTES = new ArrayList<Quote>();


	@Override
    public void onAttachedToWindow() {
		super.onAttachedToWindow();
		QUOTES.add(new Quote(getBaseContext(), "To be or not to be, that is the question. ", "William Shakespeare"));
		QUOTES.add(new Quote(getBaseContext(), "As far as the laws of mathematics refer to reality, they are not certain, and as far as they are certain, they do not refer to reality. ", "Alert Einstein"));
		QUOTES.add(new Quote(getBaseContext(), "The economy depends about as much on economists as the weather does on weather forecasters. ", "Jean-Paul Kauffmann"));

		for (Quote quote: QUOTES) {
			quote.save();
		}


		// Exit dream upon user touch
		setInteractive(true);
	   // Hide system UI
		setFullscreen(true);
		// Set the dream layout
		setContentView(R.layout.quotes_dream);



		/*Quote quote = new Quote(getBaseContext(), "This is a blah", "Aaron");
		quote.save();

		Quote quote2 = new Quote(getBaseContext(), "This is a second quote", "Anonymous");
		quote2.save();*/
	}

	@Override
	public void onDreamingStarted() {

		List<Quote> newQuotes = Quote.listAll(Quote.class);
		final TextView textView = (TextView) findViewById(R.id.textView1);
		task = new UpdateQuoteTask();
		task.execute(newQuotes, textView);


	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		Quote.deleteAll(Quote.class);
		task.cancel(true);
	}
}
