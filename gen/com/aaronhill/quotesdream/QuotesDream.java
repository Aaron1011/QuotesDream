package com.aaronhill.quotesdream;

import android.service.dreams.DreamService;

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
	}
}
