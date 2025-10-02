package edu.kirkwood.dao;

import edu.kirkwood.model.xml.MovieSearchResult;

import java.util.List;

/**
 * The data access interface for Movie objects
 * Defines the methods needed to get data
 */
public interface MovieDAO {
    /**
     * Retrieves all movies from the data source that matches the title
     * @param title The movie title a user is searching for
     * @return A list of movies that matches the search title
     */
    List<MovieSearchResult> search(String title);
}
