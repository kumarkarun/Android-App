package com.android.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "issue",
        "image"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue implements Serializable {
    @JsonProperty("issue")
    private String issue;
    @JsonProperty("image")
    private String image;

    public Issue(){}
    @JsonProperty("issue")
    public String getIssue() {
        return issue;
    }
    @JsonProperty("issue")
    public void setIssue(String issue) {
        this.issue = issue;
    }
	@JsonProperty("image")
    public String getImage() {
        return image;
    }
	@JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }


}
