/**
 * Copyright (c) 2016 Marco Aurélio Prado dos Santos Vidoca.
 */

package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button randomJokeButton = (Button) root.findViewById(R.id.random_joke_button);
        final MainActivity mainActivity = (MainActivity) getActivity();
        randomJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetRandomJokeEndpointAsyncTask().execute(new Pair<OnAsyncTaskEndListener, CountDownLatch>(mainActivity, null));
            }
        });
        return root;
    }
}
