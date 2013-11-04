package com.aaronhill.quotesdream;

import java.util.Iterator;
import java.util.List;

import android.service.dreams.DreamService;
import android.widget.TextView;

import com.aaronhill.quotesdream.GetQuotesTask;
import com.aaronhill.quotesdream.Quote;

public class QuotesDream extends DreamService {
	@Override
    public void onAttachedToWindow() {
		super.onAttachedToWindow();

		// Exit dream upon user touch
		setInteractive(true);
	   // Hide system UI
		setFullscreen(true);
		// Set the dream layout
		setContentView(R.layout.quotes_dream);



		Quote quote = new Quote(getBaseContext(), "This is a quote", "Aaron");
		quote.save();
		List<Quote> newQuotes = Quote.find(Quote.class, "author = ?", "Aaron");
		Quote newQuote = newQuotes.get(0);


		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(quote.body + "\n--" + quote.author);

		Quote.deleteAll(Quote.class);


	}
}
