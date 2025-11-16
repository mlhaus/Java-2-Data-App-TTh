package edu.kirkwood.dao;

import edu.kirkwood.dao.impl.JsonMovieDAO;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.dao.impl.XmlMusicDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MusicDAOFactory {
    private static Properties properties = new Properties();
    static {
        try(InputStream is = MovieDAOFactory.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if(is == null) {
                throw new FileNotFoundException("application.properties file not found");
            }
            properties.load(is);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static MusicDAO getMusicDAO() {
        String dataSourceType = properties.getProperty("datasource.type");
        if(dataSourceType == null || dataSourceType.isEmpty()) {
            throw new IllegalArgumentException("datasource.type is required");
        }
        switch (dataSourceType.toUpperCase()) {
            case "XML":
                String apiURL = properties.getProperty("xml.musicApiURL");
                if(apiURL == null || apiURL.isEmpty()) {
                    throw new IllegalArgumentException("xml.musicApiURL is required");
                }
                return new XmlMusicDAO(apiURL);
            case "JSON":
                break;
            case "MYSQL":
                break;
            default:
                throw new RuntimeException("Unsupported data source type: " + dataSourceType);
        }
        return null;
    }
}
