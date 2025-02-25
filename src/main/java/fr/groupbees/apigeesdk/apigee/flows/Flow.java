package fr.groupbees.apigeesdk.apigee.flows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Flow")
@JsonPropertyOrder({"name", "description", "condition", "request", "response"})
public class Flow {

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(localName = "Description")
    @JsonProperty(value = "description")
    private String description;

    @JacksonXmlProperty(localName = "Condition")
    @JsonProperty(value = "condition")
    private String condition;

    @JacksonXmlProperty(localName = "Request")
    @JsonProperty(value = "request")
    private Steps request = new Steps();

    @JacksonXmlProperty(localName = "Response")
    @JsonProperty(value = "response")
    private Steps response = new Steps();

    public Flow(final String name) {
        this.name = name;
    }

}
