package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private static final Store INST = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "Same description",
                LocalDateTime.of(2021, 06, 20, 20, 01)));
        posts.put(2, new Post(2, "Middle Java Job", "Same description",
                LocalDateTime.of(2021, 06, 25, 14, 25)));
        posts.put(3, new Post(3, "Senior Java Job", "Very good job",
                LocalDateTime.of(2021, 06, 29, 19, 27)));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }
}
