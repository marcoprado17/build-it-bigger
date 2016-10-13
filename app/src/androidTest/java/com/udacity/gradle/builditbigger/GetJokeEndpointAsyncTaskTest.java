/**
 * Copyright (c) 2016 Marco Aurélio Prado dos Santos Vidoca.
 */

package com.udacity.gradle.builditbigger;

import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class GetJokeEndpointAsyncTaskTest implements OnAsyncTaskEndListener {

    private String mJoke = null;

    @Test
    @UiThreadTest
    public void testReturnedString() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final GetJokeEndpointAsyncTask task = new GetJokeEndpointAsyncTask();
        task.execute(new Pair<OnAsyncTaskEndListener, CountDownLatch>(this, signal));

        signal.await(30, TimeUnit.SECONDS);

        assertNotNull(mJoke);
        assertNotEquals("", mJoke);
    }

    @Override
    public void onAsyncTaskEnd(String joke) {
        mJoke = joke;
    }
}