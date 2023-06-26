package com.example.appepicnovels;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class GeneralSettingsActivity extends AppCompatActivity {

    private RadioGroup languageRadioGroup;
    private RadioGroup themeRadioGroup;
    private Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);

        Toolbar toolbar = findViewById(R.id.materialToolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeneralSettingsActivity.this, ManagenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize views
        languageRadioGroup = findViewById(R.id.radioGroupLanguage);
        themeRadioGroup = findViewById(R.id.radioGroupTheme);
        saveButton = findViewById(R.id.buttonSave);

        // Set click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });

        // Load and set the current language selection
        String language = getCurrentLanguage();
        if (language.equals("English")) {
            RadioButton radioButtonEnglish = findViewById(R.id.radioButtonEnglish);
            radioButtonEnglish.setChecked(true);
        } else if (language.equals("Vietnamese")) {
            RadioButton radioButtonVietnamese = findViewById(R.id.radioButtonVietnamese);
            radioButtonVietnamese.setChecked(true);
        }

        // Load and set the current theme selection
        int currentTheme = getCurrentTheme();
        if (currentTheme == AppCompatDelegate.MODE_NIGHT_NO) {
            RadioButton radioButtonLight = findViewById(R.id.radioButtonLight);
            radioButtonLight.setChecked(true);
        } else if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            RadioButton radioButtonDark = findViewById(R.id.radioButtonDark);
            radioButtonDark.setChecked(true);
        }

        // Set initial text color based on the current theme
        setTextColorBasedOnTheme(currentTheme);
    }

    private String getCurrentLanguage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("language", "English");
    }

    private int getCurrentTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    private void saveSettings() {
        // Save the selected language
        int selectedLanguageId = languageRadioGroup.getCheckedRadioButtonId();
        String language;
        if (selectedLanguageId == R.id.radioButtonVietnamese) {
            language = "Vietnamese";
        } else {
            language = "English";
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", language);
        editor.apply();

        // Save the selected theme
        int selectedThemeId = themeRadioGroup.getCheckedRadioButtonId();
        int theme;
        if (selectedThemeId == R.id.radioButtonDark) {
            theme = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            theme = AppCompatDelegate.MODE_NIGHT_NO;
        }
        editor.putInt("theme", theme);
        editor.apply();

        // Apply the new theme
        AppCompatDelegate.setDefaultNightMode(theme);

        // Restart the activity to apply the theme changes
        recreate();
    }

    private void setTextColorBasedOnTheme(int currentTheme) {
        int textColor;
        if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            // Dark theme, set text color to white
            textColor = Color.WHITE;
        } else {
            // Light theme, set text color to black
            textColor = Color.BLACK;
        }

        // Set text color for each radio button in the languageRadioGroup
        for (int i = 0; i < languageRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) languageRadioGroup.getChildAt(i);
            radioButton.setTextColor(textColor);
        }

        // Set text color for each radio button in the themeRadioGroup
        for (int i = 0; i < themeRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) themeRadioGroup.getChildAt(i);
            radioButton.setTextColor(textColor);
        }

        saveButton.setTextColor(textColor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update the language of the activity
        updateLanguage();

        // Set text color based on the current theme
        int currentTheme = getCurrentTheme();
        setTextColorBasedOnTheme(currentTheme);
    }

    private void updateLanguage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String language = preferences.getString("language", "English");
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        }
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
