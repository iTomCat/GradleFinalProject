package com.example.tomcat.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Activity displaying a joke
 */

public class JokeDispActivity extends AppCompatActivity {
    public static String JOKE_KEY = "Joke key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_displ_layout);
        displayJoke();
    }

    private void displayJoke(){
        final TextView tvJoke = findViewById(R.id.joke_txt);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_KEY);
        if (joke != null && joke.length() != 0) {
            tvJoke.setText(joke);
        }

    }
}
