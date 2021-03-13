package com.crejo.fun.model;

public class User {

    private static Long idIncrementor = 0L;

    private Long id;
    private String firstName;
    private Level level;

    public User(String firstName) {
        this.id = ++idIncrementor;
        this.firstName = firstName;
        this.level = Level.VIEWER;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
