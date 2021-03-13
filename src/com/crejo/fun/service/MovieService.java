package com.crejo.fun.service;

import com.crejo.fun.model.Movie;
import com.crejo.fun.repository.MovieRepository;
import com.crejo.fun.util.DateUtil;

import java.util.Date;
import java.util.List;

public class MovieService {

    private static MovieRepository movieRepository = new MovieRepository();

    /**
     * Method to add a movie with release date and genres
     *
     * @param movieName
     * @param releaseDate
     * @param genres
     * @return
     */
    public static Movie addMovie(String movieName, String releaseDate, List<String> genres){
        Date relDate = DateUtil.formatDate(releaseDate, DateUtil.MM_DD_YYYY);
        Movie movie = new Movie(movieName, relDate, genres);
        movieRepository.save(movie);
        System.out.println(movie.toString());
        return movie;
    }

}
