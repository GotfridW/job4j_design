package ru.job4j.gc.leak;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PostStore {

    private Map<Integer, Post> posts = new HashMap<>();
    private int ids = 1;

    public Post add(Post post) {
        int id = ids++;
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}
