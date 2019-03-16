package com.example.my_health;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.example.my_health.Language.LanguageChanger.setAppLocale;
import static com.example.my_health.Language.SharedPreferencesManager.getSelectedLanguage;
import static com.example.my_health.Language.SharedPreferencesManager.getSelectedLanguageString;
import static com.example.my_health.Language.SharedPreferencesManager.setSelectedLanguage;


public class SettingsMenu extends MenuActivity {


    private RadioGroup languageOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_layout);

        setElements();
        setListeners();
    }

    private void setElements() {

        languageOption = findViewById(R.id.toggleLanguage);


        ((RadioButton) findViewById(getSelectedLanguage(this))).setChecked(true);

    }

    private void setListeners() {


        languageOption.setOnCheckedChangeListener((group, checkedId) -> {
            setSelectedLanguage(this, checkedId);
            setAppLocale(this, getSelectedLanguageString(this));

            recreate();
        });


    }
}
