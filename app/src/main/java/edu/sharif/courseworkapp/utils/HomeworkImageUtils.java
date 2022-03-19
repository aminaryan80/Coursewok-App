package edu.sharif.courseworkapp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.sharif.courseworkapp.R;

public class HomeworkImageUtils {

    private static final int MAX_IMAGES = 3;

    private static List<Integer> getImages() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.homework1);
        images.add(R.drawable.homework2);
        images.add(R.drawable.homework3);
        return images;
    }

    public static int getRandomImage() {
        Random random = new Random();
        int position = random.nextInt(MAX_IMAGES);
        return getImages().get(position);
    }
}