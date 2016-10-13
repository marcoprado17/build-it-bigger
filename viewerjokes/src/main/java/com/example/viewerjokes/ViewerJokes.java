package com.example.viewerjokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewerJokes extends AppCompatActivity {

    public static final String JOKE_KEY = "JOKE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer_jokes);

        String joke = getJoke();
        TextView textView = (TextView) findViewById(R.id.text_view);

        if(joke != null){
            textView.setText(joke);
        }
        else {
            String noJokeFound = getString(R.string.no_joke_found);
            textView.setText(noJokeFound);
        }
    }

    private String getJoke(){
        Intent intent = getIntent();
        if(intent != null){
            return intent.getStringExtra(JOKE_KEY);
        }
        return null;
    }
}
