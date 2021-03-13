package com.crejo.fun.model;

public enum Level {
    VIEWER(0, 1),
    CRITIC(3, 2),
    EXPERT(6, 3),
    ADMIN(9, 4);

    // This represents the mininum number of reviews that needs to be present
    // for the Level
    private int numberOfReviews;
    // This represents the amount of times the rating needs to be counted against
    // for the level
    private int reviewRatingX;

    Level(int reviews, int reviewRatingX) {
        this.numberOfReviews = reviews;
        this.reviewRatingX = reviewRatingX;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public int getReviewRatingX() {
        return reviewRatingX;
    }
}
