package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EndPointTest {

    @Test
    public void testDoInBackground() throws Exception {
        MainActivityFragment mainFragment = new MainActivityFragment();
        EndpointsAsyncTask.testInProgress = true;
        new EndpointsAsyncTask().execute(mainFragment);
        Thread.sleep(6000);
        assertTrue("Loaded Joke: " + EndpointsAsyncTask.testJoke,
                EndpointsAsyncTask.testJoke != null);
    }

}
