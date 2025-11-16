package edu.kirkwood.dao.impl;

import edu.kirkwood.dao.MusicDAO;
import edu.kirkwood.model.Music;
import edu.kirkwood.model.xml.musicbrainz.Metadata;
import edu.kirkwood.model.xml.musicbrainz.Work;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javax.xml.stream.XMLInputFactory;
import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class XmlMusicDAO implements MusicDAO {
    private String apiURL;

    public XmlMusicDAO(String apiURL) {
        this.apiURL = apiURL;
    }

    /**
     * Retrieves all movies from the data source that matches the title
     * @param title The movie title a user is searching for
     * @return A List<Movie> movies that matches the search title
     */
    @Override
    public List<Music> search(String title) {
        List<Work> results = fetch(title);
        List<Music> songs = new ArrayList<>();
        results.forEach(result -> {
            Music music = new Music();
            music.setTitle(result.getTitle());
            List<String> artists = result.getComposers();
            if(artists == null || artists.isEmpty()) {
                artists = result.getWriters();
            }
            music.setArtist(String.join(", ", artists));
            music.setLyricist(String.join(", ", result.getLyricists()));
            songs.add(music);
        });
        return songs;
    }

    /**
     * Retrieves all movies from the data source that matches the title
     * @param title The movie title a user is searching for
     * @return A List<MovieSearchResult> movies that matches the search title
     */
    public List<Work> fetch(String title) {
        if(apiURL == null || apiURL.isEmpty()) {
            throw new IllegalArgumentException("apiURL cannot be null or empty");
        }

        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String fullURL = String.format("%s&query=%s", apiURL, encodedTitle);
        List<Work> results = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullURL))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Metadata musicResponse = parseXml(body);
            results.addAll(musicResponse.getWorkList().getWorks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    // 1. Keep a reusable, thread-safe mapper instance
    //    We configure it once and reuse it.
    private static final XmlMapper XML_MAPPER;

    static {
        // Create an XMLInputFactory that is NOT namespace-aware
        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);

        // Build the XmlMapper using the non-namespace-aware factory
        XML_MAPPER = new XmlMapper(xif);

        // Optional: Tell Jackson to not fail on unknown properties (like 'type', 'type-id', etc.)
        XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Parses the MusicBrainz XML string into Metadata objects using Jackson.
     *
     * @param xml The XML data as a string.
     * @return A populated Metadata object.
     * @throws IOException If parsing fails.
     */
    public Metadata parseXml(String xml) throws IOException {
        // Use the pre-configured mapper to read the value
        // No need for StringReader, readValue can take a String directly.
        Metadata musicResponse = XML_MAPPER.readValue(xml, Metadata.class);
        return musicResponse;
    }
}
