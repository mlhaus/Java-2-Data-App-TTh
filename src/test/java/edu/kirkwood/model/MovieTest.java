package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.kirkwood.model.Movie.*;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie m1;
    private Movie m2;
    private Movie m3;
    private List<Movie> movies;

    @BeforeEach
    void setUp() {
        m1 = new Movie("1010", "a", 2025, "No Plot");
        m2 = new Movie("11", "C", 2024, "No Plot");
        m3 = new Movie("11", "b", 2023, "No Plot");
        movies = new ArrayList<>();
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
    }

    @Test
    void sortMoviesByYear() {
        // Act
        movies.sort(compareYear);
        // Assert
        assertEquals(movies.get(0), m3);
        assertEquals(movies.get(1), m2);
        assertEquals(movies.get(2), m1);
    }

    @Test
    void sortMoviesByYearReverse() {
        // Act
        movies.sort(Collections.reverseOrder(compareYear));
        // Assert
        assertEquals(movies.get(0), m1);
        assertEquals(movies.get(1), m2);
        assertEquals(movies.get(2), m3);
    }

    @Test
    void sortMoviesByID() {
        // Arrange

        // Act
        Collections.sort(movies);
        // Assert
        assertEquals(movies.get(0), m2);
        assertEquals(movies.get(1), m3);
        assertEquals(movies.get(2), m1);
    }

    @Test
    void sortMoviesByIDReverse() {
        // Arrange

        // Act
//        Collections.reverse(movies);
        movies.sort(Collections.reverseOrder());
        // Assert
        assertEquals(movies.get(0), m1);
        assertEquals(movies.get(1), m2);
        assertEquals(movies.get(2), m3);
    }

    @Test
    void compareMoviesByTitleNotIgnoringCapitalization() {
        // Arrange

        // Act
        movies.sort(compareTitle);
        // Assert
        assertEquals(movies.get(0), m2);
        assertEquals(movies.get(1), m1);
        assertEquals(movies.get(2), m3);
    }

    @Test
    void compareMoviesByTitleIgnoringCapitalization() {
        // Arrange

        // Act
        movies.sort(compareTitleIgnoreCase);
        // Assert
        assertEquals(movies.get(0), m1);
        assertEquals(movies.get(1), m3);
        assertEquals(movies.get(2), m2);
    }

    @Test
    void compareToNegative() {
        // Arrange
        int expected = -1; // A negative number means obj1 comes before obj2
        // Act
        int actual = m2.compareTo(m1);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void compareToZero() {
        // Arrange
        int expected = 0; // Zero means obj1 and obj2 are the same
        // Act
        int actual = m2.compareTo(m3);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void compareToPositve() {
        // Arrange
        int expected = 1; // Positive means obj1 comes after obj2
        // Act
        int actual = m1.compareTo(m3);
        // Assert
        assertEquals(expected, actual);
    }
}