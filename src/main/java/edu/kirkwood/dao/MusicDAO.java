package edu.kirkwood.dao;

import edu.kirkwood.model.Music;

import java.util.List;

public interface MusicDAO {
    List<Music> search(String title);
}
