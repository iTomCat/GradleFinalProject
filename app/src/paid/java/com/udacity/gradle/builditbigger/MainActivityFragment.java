package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.tomcat.jokedisplay.JokeDispActivity;


/**
 * Paid version
 */

public class MainActivityFragment extends Fragment {
    ProgressBar myProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        myProgressBar = root.findViewById(R.id.progressbar);

        // ----------------------------------------------------------------------------------------- Buton Listener
        Button button = root.findViewById(R.id.button_joke);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke(){
        myProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

    public void showJoke(String joke){
        Intent intent = new Intent(getContext(), JokeDispActivity.class);
        intent.putExtra(JokeDispActivity.JOKE_KEY, joke);
        getContext().startActivity(intent);

        myProgressBar.setVisibility(View.GONE);
    }


}
