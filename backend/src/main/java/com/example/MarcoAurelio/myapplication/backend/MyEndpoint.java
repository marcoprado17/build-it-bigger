/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.MarcoAurelio.myapplication.backend;

import com.example.JokeFactory;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.MarcoAurelio.example.com",
    ownerName = "backend.myapplication.MarcoAurelio.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();

        JokeFactory jokeFactory = new JokeFactory();
        String joke = jokeFactory.getJoke();

        response.setData(joke);

        return response;
    }

    @ApiMethod(name = "getRandomJoke", path="get_random_joke")
    public MyBean getRandomJoke() {
        MyBean response = new MyBean();

        JokeFactory jokeFactory = new JokeFactory();
        String joke = jokeFactory.getRandomJoke();

        response.setData(joke);

        return response;
    }

}
