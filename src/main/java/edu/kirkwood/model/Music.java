package edu.kirkwood.model;

import java.util.Objects;

public class Music {
    private String title;
    private String artist;
    private String lyricist;

    public Music() {}

    public Music(String title, String artist, String lyricist) {
        this.title = title;
        this.artist = artist;
        this.lyricist = lyricist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", lyricist='" + lyricist + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return Objects.equals(title, music.title) && Objects.equals(artist, music.artist) && Objects.equals(lyricist, music.lyricist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, lyricist);
    }
}
