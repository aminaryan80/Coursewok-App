package edu.sharif.courseworkapp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.sharif.courseworkapp.R;

public class CourseImageUtils {

    private static final int MAX_IMAGES = 5;

    private static List<Integer> getImages() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.classroom);
        images.add(R.drawable.classroom1);
        images.add(R.drawable.classroom2);
        images.add(R.drawable.classroom3);
        images.add(R.drawable.classroom4);
        return images;
    }

    public static int getRandomImage() {
        Random random = new Random();
        int position = random.nextInt(MAX_IMAGES);
        return getImages().get(position);
    }
}
