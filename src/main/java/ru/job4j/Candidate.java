package ru.job4j;

public class Candidate extends Person {
    private String city;
    private String position;

    public Candidate(String name, String email, String city, String position) {
        super(name, email);
        this.city = city;
        this.position = position;
    }

    public String getCity() {
        return city;
    }

    public String getPosition() {
        return position;
    }
}
