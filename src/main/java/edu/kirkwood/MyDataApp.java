package edu.kirkwood;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.xml.MovieSearchResult;

import java.util.List;

public class MyDataApp {
    public static void main(String[] args) {
        MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
        // Prompt the user for a movie title
        String title = "Batman";
        XmlMovieDAO xmlMovieDAO = (XmlMovieDAO) movieDAO;
        List<MovieSearchResult> results = xmlMovieDAO.search(title);
        results.forEach(System.out::println);
    }
}
