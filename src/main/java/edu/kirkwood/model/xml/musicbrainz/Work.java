package edu.kirkwood.model.xml.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Work {

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "relation-list")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<RelationList> relationLists;

    // --- Helper Methods (Your Specific Goal) ---

    public List<String> getComposers() {
        return getArtistsByType("artist", "composer");
    }

    public List<String> getWriters() {
        return getArtistsByType("artist", "writer");
    }

    public List<String> getLyricists() {
        return getArtistsByType("artist", "lyricist");
    }

    /**
     * A helper to navigate the complex relation structure.
     * @param targetType The 'target-type' of the relation-list (e.g., "artist")
     * @param relationType The 'type' of the relation (e.g., "composer")
     * @return A list of artist names.
     */
    private List<String> getArtistsByType(String targetType, String relationType) {
        if (relationLists == null) {
            return Collections.emptyList();
        }

        return relationLists.stream()
                // 1. Find the <relation-list> with target-type="artist"
                .filter(rl -> targetType.equals(rl.getTargetType()))
                // 2. Get the list of <relation> tags from it
                .flatMap(rl -> rl.getRelations() != null ? rl.getRelations().stream() : Stream.empty())
                // 3. Filter for <relation> with type="composer" or "lyricist"
                .filter(r -> relationType.equals(r.getType()))
                // 4. Get the artist object
                .map(Relation::getArtist)
                // 5. Make sure artist isn't null
                .filter(Objects::nonNull)
                // 6. Get the artist's name
                .map(Artist::getName)
                // 7. Collect into a list
                .collect(Collectors.toList());
    }

    // --- Getters and Setters ---

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

    public List<RelationList> getRelationLists() {
        return relationLists;
    }

    public void setRelationLists(List<RelationList> relationLists) {
        this.relationLists = relationLists;
    }
}
