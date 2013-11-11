package com.aaronhill.quotesdream;



import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.service.dreams.DreamService;
import android.util.Log;
import android.widget.TextView;

public class QuotesDream extends DreamService {
	Quote newQuote;
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

		QUOTES.add(new Quote(getBaseContext(), "To be or not to be, that is the question. ", "William Shakespeare"));
		QUOTES.add(new Quote(getBaseContext(), "As far as the laws of mathematics refer to reality, they are not certain, and as far as they are certain, they do not refer to reality. ", "Alert Einstein"));
		QUOTES.add(new Quote(getBaseContext(), "The economy depends about as much on economists as the weather does on weather forecasters. ", "Jean-Paul Kauffmann"));

		for (Quote quote: QUOTES) {
			quote.save();
		}
		new GetQuotesTask(getBaseContext()).execute(20);


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

		final TextView textView = (TextView) findViewById(R.id.textView1);
		task = new UpdateQuoteTask();
		task.execute(textView, Integer.parseInt(sharedPref.getString(getString(R.string.quote_display_time), "1000")) * 1000);


	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		Quote.deleteAll(Quote.class);
		task.cancel(true);
	}
}
