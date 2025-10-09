package edu.kirkwood;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.Movie;
import edu.kirkwood.model.xml.MovieSearchResult;

import java.util.ArrayList;
import java.util.List;

public class MyDataApp {
    public static void main(String[] args) {
        MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
        // Prompt the user for a movie title
        String title = "Short Circuit";
        List<Movie> results = new ArrayList<>();
        if(movieDAO instanceof XmlMovieDAO) {
            XmlMovieDAO xmlMovieDAO = (XmlMovieDAO) movieDAO;
            results = xmlMovieDAO.search(title);
        } else if(movieDAO instanceof MySQLMovieDAO) {
            MySQLMovieDAO mySQLMovieDAO = (MySQLMovieDAO) movieDAO;
            results = mySQLMovieDAO.search(title);
        }
        results.forEach(System.out::println);
    }
}
