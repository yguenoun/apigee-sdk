package fr.groupbees.apigeesdk.apigee.flows.proxyendpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.flows.Steps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostClientFlow {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(localName = "Description")
    @JsonProperty(value = "description")
    private String description;

    @JacksonXmlProperty(localName = "Response")
    @JsonProperty(value = "response")
    private Steps response;

}
