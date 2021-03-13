package com.crejo.fun.repository;

import com.crejo.fun.model.Level;
import com.crejo.fun.model.Movie;
import com.crejo.fun.model.Review;
import com.crejo.fun.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewRepository {

    private static List<Review> reviewsDB = new ArrayList<>();

    public Review save(Review review){
        reviewsDB.add(review);
        return review;
    }

    public List<Review> getAllReviews(){
        return reviewsDB;
    }

    public List<Review> findAllReviewsByCriticOnGenre(String genre){
        return findAllReviewsByGenreAndLevel(genre, Level.CRITIC);
    }

    public List<Review> findAllReviewsByGenreAndLevel(String genre, Level level){
        List<Review> reviews = reviewsDB.stream().filter(review ->
                review.getLevel() == level &&
                review.getMovie().getGenres().contains(genre)
                ).collect(Collectors.toList());
        return reviews;
    }

    public List<Review> findAllReviewsByMovie(Movie movie){
        List<Review> reviews = reviewsDB.stream().filter(
                review -> review.getMovie().equals(movie))
                .collect(Collectors.toList());
        return reviews;
    }

    public List<Review> findAllReviewsByUser(User user){
        List<Review> reviews = reviewsDB.stream().filter(
                review -> review.getUser().equals(user)).
                collect(Collectors.toList());
        return reviews;
    }
}
