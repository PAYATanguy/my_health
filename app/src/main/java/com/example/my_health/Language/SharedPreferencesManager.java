package com.example.my_health.Language;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.my_health.R;


public class SharedPreferencesManager {

    private static final String LANGUAGE = "language";
    private static final String PREFS_FILE_GENERAL = "settings.xml";

    private SharedPreferencesManager() {
        throw new IllegalStateException("Utility class");
    }

    public static void setSelectedLanguage(Activity activity, int language) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(PREFS_FILE_GENERAL, 0).edit();
        editor.putInt(LANGUAGE, language)
                .apply();
        editor.commit();
    }

    public static int getSelectedLanguage(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences(PREFS_FILE_GENERAL, 0);
        return (preferences.getInt(LANGUAGE, R.id.FR));
    }

    public static String getSelectedLanguageString(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences(PREFS_FILE_GENERAL, 0);
        return activity.getResources().getResourceEntryName(preferences.getInt(LANGUAGE, R.id.FR));
    }
}
