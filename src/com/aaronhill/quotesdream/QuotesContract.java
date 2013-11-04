package com.aaronhill.quotesdream;

import android.provider.BaseColumns;

public class QuotesContract {
	public static abstract class Quote implements BaseColumns {
        public static final String TABLE_NAME = "quote";
        public static final String COLUMN_NAME_QUOTE_ID = "quoteid";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_NAME_AUTHOR = "author";
    }
}
