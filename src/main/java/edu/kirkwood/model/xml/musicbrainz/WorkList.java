package edu.kirkwood.model.xml.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkList {

    // @JacksonXmlElementWrapper(useWrapping = false) tells Jackson that
    // <work-list> is the wrapper, and its direct children <work> are the list items.
    @JacksonXmlProperty(localName = "work")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Work> works;

    // --- Getters and Setters ---
    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
