package edu.kirkwood.model;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public class Movie implements Comparable<Movie> {
    private String id;
    private String title;
    private int year;
    private String plot;

    public Movie() {

    }

    public Movie(String id, String title, int year, String plot) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.plot = plot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", plot='" + plot + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year && Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(plot, movie.plot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, plot);
    }

    @Override
    public int compareTo(@NotNull Movie o) {
        if(this.id.length() != o.id.length()) {
            // First sort by the length of the id
            return Integer.compare(this.id.length(), o.id.length());
        }
        // If lengths are the same sort them alphabetically
        return this.id.compareTo(o.id);
    }

    public static Comparator<Movie> compareTitleIgnoreCase = (m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle());
    public static Comparator<Movie> compareTitleLamba2 = Comparator.comparing(m -> m.getTitle());
    public static Comparator<Movie> compareTitle = Comparator.comparing(Movie::getTitle);
    public static Comparator<Movie> compareYear = Comparator.comparingInt(Movie::getYear);
}
