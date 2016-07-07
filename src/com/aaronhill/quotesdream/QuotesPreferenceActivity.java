package com.aaronhill.quotesdream;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class QuotesPreferenceActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setTitle("Settings");

		getFragmentManager().beginTransaction()
        		.replace(android.R.id.content, new SettingsFragment())
        		.commit();
	}

}
