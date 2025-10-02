package edu.kirkwood.dao;

import edu.kirkwood.dao.impl.XmlMovieDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MovieDAOFactory {
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

    /**
     * To retrieve a MovieDAO based on application.properties settings
     * @return A MovieDAO (XMLMovieDAO, MySQLMovieDAO, JSONMovieDAO, etc.)
     */
    public static MovieDAO getMovieDAO() {
        String dataSourceType = properties.getProperty("datasource.type");
        if(dataSourceType == null || dataSourceType.isEmpty()) {
            throw new IllegalArgumentException("datasource.type is required");
        }
        switch (dataSourceType.toUpperCase()) {
            case "XML":
                String apiURL = properties.getProperty("xml.apiURL");
                if(apiURL == null || apiURL.isEmpty()) {
                    throw new IllegalArgumentException("xml.apiURL is required");
                }
                return new XmlMovieDAO(apiURL);
//            case "JSON":
//                break;
//            case "MYSQL":
//                break;
//            case "MONGODB":
//                break;
            default:
                throw new RuntimeException("Unsupported data source type: " + dataSourceType);
        }
    }
}
