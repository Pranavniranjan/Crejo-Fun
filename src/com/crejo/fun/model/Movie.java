package com.crejo.fun.model;

import com.crejo.fun.util.DateUtil;

import java.util.Date;
import java.util.List;

public class Movie {

    private static Long idIncrementor = 0L;

    private Long id;
    private String name;
    private Date releaseDate;
    private List<String> genres;

    public Movie(String name, Date releaseDate, List<String> genres) {
        this.id = ++idIncrementor;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // helper method
    public boolean isReleased(){
        return !DateUtil.isFutureDate(getReleaseDate());
    }

    @Override
    public String toString() {
        return "Added movie with name: " + name;
    }
}
