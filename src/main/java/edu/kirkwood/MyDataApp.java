package edu.kirkwood;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.JsonMovieDAO;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.Movie;
import edu.kirkwood.model.xml.MovieSearchResult;
import edu.kirkwood.view.Animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.kirkwood.model.Movie.compareTitle;
import static edu.kirkwood.model.Movie.compareYear;
import static edu.kirkwood.view.Helpers.printList;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class MyDataApp {
    public static void main(String[] args) {
        String title = getString("Enter a movie title", true);
        List<Movie> results = getResults(title);
        sortByMenu(results, title);
    }

    public static void sortByMenu(List<Movie> results, String title) {
        String[] menuItems = {
                "No sort",
                "Sort by Id (Low to high)",
                "Sort by Id (High to low)",
                "Sort by Title (A to Z)",
                "Sort by Title (Z to A)",
                "Sort by Year (Old to new)",
                "Sort by Year (New to old)",
                "Quit"
        };
        String menuTitle = "Sort By Menu";
        while(true) {
            printMenu(menuTitle, menuItems);
            int choice = getInt("Choose an option", true, 1, menuItems.length);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    Collections.sort(results);
                    break;
                case 3:
                    results.sort(Collections.reverseOrder());
                    break;
                case 4:
                    results.sort(compareTitle);
                    break;
                case 5:
                    results.sort(Collections.reverseOrder(compareTitle));
                    break;
                case 6:
                    results.sort(compareYear);
                    break;
                case 7:
                    results.sort(Collections.reverseOrder(compareYear));
                    break;
                default:
                    return;
            }
            printList("Search for '" + title + "'", results, 10);
            pressEnterToContinue();
        }
    }

    public static List<Movie> getResults(String title) {
        Animation animation = new Animation("Searching for data. Please wait");
        Thread animationThread = new Thread(animation);
        animationThread.start(); // triggers the Animation run()

        MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
        List<Movie> results = new ArrayList<>();
        if(movieDAO instanceof XmlMovieDAO) {
            XmlMovieDAO xmlMovieDAO = (XmlMovieDAO) movieDAO;
            results = xmlMovieDAO.search(title);
        } else if(movieDAO instanceof MySQLMovieDAO) {
            MySQLMovieDAO mySQLMovieDAO = (MySQLMovieDAO) movieDAO;
            results = mySQLMovieDAO.search(title);
        } else if(movieDAO instanceof JsonMovieDAO) {
            JsonMovieDAO mySQLMovieDAO = (JsonMovieDAO) movieDAO;
            results = mySQLMovieDAO.search(title);
        }

        animation.stopAnimation();
        try {
            animationThread.join(); // Wait for the animation to finish before continuing
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}
