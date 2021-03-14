package com.crejo.fun.service;

import com.crejo.fun.exception.MovieNotReleasedException;
import com.crejo.fun.exception.MultipleReviewsNotAllowedException;
import com.crejo.fun.model.Level;
import com.crejo.fun.model.Movie;
import com.crejo.fun.model.Review;
import com.crejo.fun.model.User;
import com.crejo.fun.repository.ReviewRepository;
import com.crejo.fun.util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class ReviewService {

    private static ReviewRepository reviewRepository = new ReviewRepository();

    /**
     * Adds review for the movie and upgrades the user level
     * if applicable.
     *
     * @param user
     * @param movie
     * @param rating
     * @return
     */
    public static Review addReview(User user, Movie movie, int rating){
        try {
            boolean canAddReview = validateIfUserCanAddReview(user, movie);
            if(canAddReview){
                Review review = new Review(user, movie, rating);
                reviewRepository.save(review);
                System.out.println(review.toString());
                UserService.changeUserLevelIfApplicable(user);
                return review;
            }
        } catch (Exception e){
            System.out.println("Exception Occurred - " + e.getClass().getName() + " - " + e.getMessage());
        }
       return null;
    }

    /**
     * Returns the reviews added by the user
     *
     * @param user
     * @return
     */
    public static List<Review> getReviewsAddedByUser(User user){
        List<Review> reviewsByUser = reviewRepository.findAllReviewsByUser(user);
        return reviewsByUser;
    }

    /**
     * Method to validate if the user can add the review for that movie
     *
     * @param user
     * @param movie
     * @return
     */
    private static boolean validateIfUserCanAddReview(User user, Movie movie){
        if(movie.isReleased()){
            if(canAddReviewForMovie(user, movie)){
                return true;
            } else {
                String exceptionMessage = "User: " + user.getFirstName() + " has already reviewed Movie: " + movie.getName();
                throw new MultipleReviewsNotAllowedException(exceptionMessage);
            }
        } else {
            String exceptionMessage = "Movie :" + movie.getName() + " is not released yet";
            throw new MovieNotReleasedException(exceptionMessage);
        }
    }

    /**
     * Checks if the user hasn't already added a review for the movie
     *
     * @param user
     * @param movie
     * @return
     */
    private static boolean canAddReviewForMovie(User user, Movie movie){
        List<Review> reviews = getReviewsAddedByUser(user);
        boolean hasReviewed = reviews.stream().anyMatch(review -> review.getMovie().equals(movie));
        return !hasReviewed;
    }

    /**
     * Calculates the avg review score of all the reviews
     * for a given year
     *
     * @param reviewYear
     * @return
     */
    public static double getAverageReviewScoreForYear(String reviewYear){
        Date reviewYearDate = DateUtil.formatDate(reviewYear, DateUtil.MM_DD_YYYY);
        List<Review> reviews = reviewRepository.getAllReviews();
        List<Review> reviewsForTheYear = reviews.stream().filter(review ->
                DateUtil.isSameYear(reviewYearDate, review.getMovie().getReleaseDate()))
                .collect(Collectors.toList());
        Double averageReviewScoreForYear =  calculateAverageReviewScore(reviewsForTheYear);
        return averageReviewScoreForYear;
    }

    /**
     * Calculates the avg review score of all the reviews
     * for a movie
     *
     * @param movie
     * @return
     */
    public static Double getAverageReviewScoreForMovie(Movie movie){
        List<Review> reviewsOfMovie = reviewRepository.findAllReviewsByMovie(movie);
        Double averageReviewScoreOfMovie = calculateAverageReviewScore(reviewsOfMovie);
        return averageReviewScoreOfMovie;
    }

    /**
     * Helper method to calculate the average of review rating score
     *
     * @param reviews
     * @return
     */
    private static double calculateAverageReviewScore(List<Review> reviews){
        int reviewsForTheYearCount = reviews.size();
        if (reviewsForTheYearCount > 0 ){
            int reviewRatingSum = 0;
            for (Review review : reviews) {
                reviewRatingSum += review.getReviewRating();
            }
            Double avgReviewScore = (double) reviewRatingSum / reviewsForTheYearCount;
            BigDecimal bigDecimal = new BigDecimal(avgReviewScore);
            return bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        return 0.00;
    }

    /**
     * Method to get the top N movies of highest total review rating score
     * by a specific genre and posted by critic
     *
     * @param genre
     * @param maxCount
     */
    public static void getTopMoviesByCritics(String genre, int maxCount){
        List<Review> reviews = reviewRepository.findAllReviewsByGenreAndLevel(genre, Level.CRITIC);
        Map<Movie, Integer> reviewScoreMap = new HashMap<>();
        for(Review review : reviews){
            Integer reviewScore = reviewScoreMap.get(review.getMovie());
            if(reviewScore == null){
                reviewScore = review.getReviewRating();
            } else {
                reviewScore += review.getReviewRating();
            }
            reviewScoreMap.put(review.getMovie(), reviewScore);
        }
        if(reviewScoreMap.size() > 0 ){
            sortMoviesByReviewScoreAndPrint(reviewScoreMap, maxCount);
        } else {
            System.out.println("No top movies found for genre: " + genre);
        }
    }

    /**
     * Helper method to sort the values in descending order (sort by total review score)
     * and print the first N movies
     *
     * @param reviewScoreMap
     * @param maxCount
     */
    private static void sortMoviesByReviewScoreAndPrint(Map<Movie, Integer> reviewScoreMap, int maxCount){
        List<Entry<Movie, Integer>> reviewList = new ArrayList<>(reviewScoreMap.entrySet());
        Collections.sort(reviewList, new Comparator<Entry<Movie, Integer>>() {
            @Override
            public int compare(Entry<Movie, Integer> o1, Entry<Movie, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int count = 0;
        for(Entry<Movie, Integer> movieEntry: reviewList){
            System.out.println("Movie: " + movieEntry.getKey().getName() + " has total review score: " + movieEntry.getValue());
            count++;
            if(count == maxCount ){
                break;
            }
        }
    }

}
