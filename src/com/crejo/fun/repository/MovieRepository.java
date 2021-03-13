package com.crejo.fun.repository;

import com.crejo.fun.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private static List<Movie> movies = new ArrayList<>();

    public Movie save(Movie movie){
        movies.add(movie);
        return movie;
    }
}
