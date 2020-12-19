package com.utachiwana.athena;

import com.utachiwana.athena.data.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<Post> getPosts(int count) {
        List<Post> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Post post = new Post();
            post.setAuthor("author");
            post.setId(i);
            post.setText("message");
            post.setType("kek");
            post.setForm("очно");
            post.setDuration("1 hour");
            post.setPrice("2000/занятие");
            post.setSubject("Программирование");
            post.setTime("вт " + generateTime() + " - " +generateTime() +
                    "\nср " + generateTime() + " - " + generateTime());
            list.add(post);
        }
        return list;
    }

    private static String generateTime() {
        return new Random().nextInt(24) + ":00";
    }
}
