package com.utachiwana.athena;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.utachiwana.athena.data.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<Post> getPosts(int count) {
        List<Post> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Post post = new Post();
            post.setAuthor("Мага Владимирович");
            post.setId(i);
            post.setText("Веду только одиночные занятия. Указывайте номер телефона при связи.");
            post.setType("Репетиторство");
            post.setForm("Без разницы");
            post.setDuration("1 час");
            post.setPrice("2000/занятие");
            post.setTime("Вторник " + generateTime() + " - " + generateTime() +
                    "\nСреда " + generateTime() + " - " + generateTime());
            list.add(post);
        }
        return list;
    }

    private static String generateTime() {
        return new Random().nextInt(24) + ":00";
    }

    public static String getProfileData() {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", "Василий");
        obj.addProperty("lastname", "Шмоткин");
        obj.addProperty("email", "vasyatopshmot@mail.ru");
        obj.addProperty("role", "Ученик");
        return obj.toString();
    }

    public static String getTimeArray() {
        JsonArray el = new JsonArray();
        for (int i = 0; i < new Random().nextInt(5) + 1; i++) {
            el.add("Вторник " + generateTime() + " - " + generateTime());
        }
        return el.toString();
    }
}
