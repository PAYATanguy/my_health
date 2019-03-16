package com.example.my_health.Language;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageChanger {

    private LanguageChanger() {
    }

    public static void setAppLocale(Activity activity, String language) {

        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(language));
        activity.getApplicationContext().createConfigurationContext(configuration);

        activity.getResources().updateConfiguration(configuration,
                activity.getResources().getDisplayMetrics());
    }
}
