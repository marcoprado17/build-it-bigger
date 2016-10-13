/**
 * Copyright (c) 2016 Marco Aurélio Prado dos Santos Vidoca.
 */

package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Pair;

import com.example.marcoaurelio.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class GetJokeEndpointAsyncTask extends AsyncTask<Pair<OnAsyncTaskEndListener,CountDownLatch>, Void, String> {
    private MyApi myApiService = null;
    private OnAsyncTaskEndListener mOnAsyncTaskEndListener;
    private CountDownLatch mSignal;

    @Override
    protected String doInBackground(Pair<OnAsyncTaskEndListener, CountDownLatch>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        mOnAsyncTaskEndListener = params[0].first;
        mSignal = params[0].second;

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if (mOnAsyncTaskEndListener != null) {
            mOnAsyncTaskEndListener.onAsyncTaskEnd(joke);
        }
        if(mSignal != null){
            mSignal.countDown();
        }
    }
}
