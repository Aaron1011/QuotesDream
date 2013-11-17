package com.aaronhill.quotesdream;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupManager;
import android.app.backup.SharedPreferencesBackupHelper;

public class MyBackupAgent extends BackupAgentHelper {

	@Override
    public void onCreate() {
        SharedPreferencesBackupHelper helper =
                new SharedPreferencesBackupHelper(this, getString(R.string.quote_display_time));
        addHelper(getString(R.string.preference_file_key), helper);
    }

}
