package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job", "Description for Java Job"));
        store.save(new Post(0, "Junior Java Job", "Description for Junior Java Job"));
        store.save(new Candidate(0, "Ivan"));
        store.save(new Candidate(0, "Oleg"));
        store.save(new Candidate(0, "Petr"));
        store.save(new User(0, "Oleg", "oleg@oleg", "oleg"));
        store.save(new User(0, "Ivan", "ivan@ivan", "ivan"));
        store.save(new User(0, "Petr", "petr@petr", "petr"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
        store.save(new Candidate(1, "Gregory"));
        store.save(new Post(2, "Hard Job", "Very Hard Job"));
        store.deleteCandidateById(2);
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
        System.out.println(store.findPostById(2).getName());
        System.out.println(store.findCandidateById(1).getName());
        System.out.println(store.findByEmail("oleg@oleg").getName());
    }
}