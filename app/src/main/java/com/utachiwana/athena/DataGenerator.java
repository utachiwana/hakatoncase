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
            post.setPrice("2000/hour");
            post.setSubject("Программирование");
            post.setTime("вт " + new Random().nextInt());
            list.add(post);
        }
        return list;
    }
}
