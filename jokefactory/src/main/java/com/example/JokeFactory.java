package com.example;

import java.util.Random;

public class JokeFactory {
    public String getJoke(){
        return "Can a kangaroo jump higher than a house? Of course, a house doesn't jump at all.";
    }

    public String getRandomJoke() {
        Random r = new Random();

        int n = r.nextInt(5);

        switch(n) {
            case 0:
                return "- Anton, do you think I'm a bad mother?\n- My name is Paul.";
            case 1:
                return "Scientists have now discovered how women keep their secrets. They do so within groups of 40.";
            case 2:
                return "My wife's cooking is so bad we usually pray after our food.";
            case 3:
                return "I1d like to buy a new boomerang please. Also, can you tell me how to throw the old one away?";
            default:
                return "A hot naked women robbed a bank. Nobody could remember her face.";
        }
    }
}
