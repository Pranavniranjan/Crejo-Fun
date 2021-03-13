package com.crejo.fun;

import com.crejo.fun.model.Movie;
import com.crejo.fun.model.User;
import com.crejo.fun.service.MovieService;
import com.crejo.fun.service.ReviewService;
import com.crejo.fun.service.UserService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Adding Users");
        User userSrk = UserService.addUser("SRK");
        User userSalman = UserService.addUser("Salman");
        User userDeepika = UserService.addUser("Deepika");
        User userSalmanTwo = UserService.addUser("Salman");

        System.out.println();
        System.out.println("Adding Movies");
        Movie movieDon = MovieService.addMovie("Don", "01/01/2006", Arrays.asList("Action", "Comedy"));
        Movie movieTiger = MovieService.addMovie("Tiger", "01/01/2008", Arrays.asList("Drama"));
        Movie moviePadmaavat =  MovieService.addMovie("Padmaavat", "01/01/2006", Arrays.asList("Comedy"));
        Movie movieLunchbox = MovieService.addMovie("Lunchbox", "03/22/2021", Arrays.asList("Drama"));
        Movie movieGuru = MovieService.addMovie("Guru", "01/01/2006", Arrays.asList("Drama"));
        Movie movieMetro = MovieService.addMovie("Metro", "01/01/2006", Arrays.asList("Romance"));
        Movie movieDhoom = MovieService.addMovie("Dhoom", "02/27/2007", Arrays.asList("Romance", "Action"));

        System.out.println();
        System.out.println("Adding Reviews");
        ReviewService.addReview(userSrk, movieDon, 2);
        ReviewService.addReview(userSrk, moviePadmaavat, 8);
        ReviewService.addReview(userSalman, movieDon, 5);
        ReviewService.addReview(userDeepika, movieDon, 9);
        ReviewService.addReview(userDeepika, movieGuru, 6);
        ReviewService.addReview(userSrk, movieDon, 10);
        ReviewService.addReview(userDeepika, movieLunchbox, 5);
        ReviewService.addReview(userSrk, movieTiger, 5);
        ReviewService.addReview(userSrk, movieMetro, 7);
        ReviewService.addReview(userSrk, movieDhoom, 8);

        System.out.println();
        double averageRating = ReviewService.getAverageReviewScoreForYear("01/01/2006");
        System.out.println("Average Review Rating for year 2006 is: " + averageRating);

        System.out.println();
        double averageReviewRatingForMovie = ReviewService.getAverageReviewScoreForMovie(movieDon);
        System.out.println("Average Review Rating for movie: " + movieDon.getName() + " is: " + averageReviewRatingForMovie);

        System.out.println();
        String genre = "Romance";
        int topNMoviews = 2;
        System.out.println("Printing top "+ topNMoviews + " of genre: " + genre + " by critic reviewers");
        ReviewService.getTopMoviesByCritics(genre, topNMoviews);

    }
}
