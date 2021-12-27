package ru.android.lesson2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class InstallTheme extends AppCompatActivity {
    public static final String THEME_TYPE = "THEME_TYPE";
    public String nameTheme;

    private MaterialButton lightTheme;
    private MaterialButton darkTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_theme);

        initView();

    }

    private void initView() {
        MaterialButton lightTheme = findViewById(R.id.button_light_theme);
        MaterialButton darkTheme = findViewById(R.id.button_dark_theme);

        lightTheme.setOnClickListener(buttonLightTheme);
        darkTheme.setOnClickListener(buttonDarkTheme);

    }

    public View.OnClickListener buttonLightTheme = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent lightThemeIntent = new Intent(InstallTheme.this, MainActivity.class);
            nameTheme = "LIGHT_THEME";
            lightThemeIntent.putExtra(THEME_TYPE, nameTheme);
            startActivity(lightThemeIntent);
        }
    };
    public View.OnClickListener buttonDarkTheme = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent darkThemeIntent = new Intent(InstallTheme.this, MainActivity.class);
            nameTheme = "DARK_THEME";
            darkThemeIntent.putExtra(THEME_TYPE, nameTheme);
            startActivity(darkThemeIntent);
        }
    };


}