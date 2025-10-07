package edu.kirkwood.dao.impl;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.model.mysql.MovieMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static edu.kirkwood.dao.MySQLConnection.getConnection;

public class MySQLMovieDAO implements MovieDAO<MovieMySQL> {
    private String connectionString;

    public MySQLMovieDAO(String connectionString) {
        this.connectionString = connectionString;
    }
    
    @Override
    public List<MovieMySQL> search(String title) {
        // try-with-resources
        try(Connection connection = getConnection(connectionString)) {
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }
}
