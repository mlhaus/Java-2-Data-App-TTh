package edu.kirkwood.model.xml.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

// Ignore any XML elements we don't map
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "metadata")
public class Metadata {

    @JacksonXmlProperty(localName = "work-list")
    private WorkList workList;

    // --- Getters and Setters ---
    public WorkList getWorkList() {
        return workList;
    }

    public void setWorkList(WorkList workList) {
        this.workList = workList;
    }
}