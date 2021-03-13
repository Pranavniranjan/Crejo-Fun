package com.crejo.fun.model;

public class Review {


    private static Long idIncrementor = 0L;

    private Long id;
    private User user;
    private Movie movie;
    private int rating;
    private Level level;


    public Review(User user, Movie movie, int rating) {
        this.id = ++idIncrementor;
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.level = user.getLevel();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getReviewRating(){
        return this.rating * this.level.getReviewRatingX();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Added review for movie: " + movie.getName() + " by user: " + user.getFirstName() +
                " with level: " + this.level;
    }
}
