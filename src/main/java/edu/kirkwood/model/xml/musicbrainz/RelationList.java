package edu.kirkwood.model.xml.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RelationList {

    @JacksonXmlProperty(localName = "target-type", isAttribute = true)
    private String targetType;

    @JacksonXmlProperty(localName = "relation")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Relation> relations;

    // --- Getters and Setters ---
    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }
}
