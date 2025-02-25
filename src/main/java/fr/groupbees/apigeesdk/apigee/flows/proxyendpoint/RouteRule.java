package fr.groupbees.apigeesdk.apigee.flows.proxyendpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteRule {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(localName = "Condition")
    @JsonProperty(value = "condition")
    private String condition;

    @JacksonXmlProperty(localName = "TargetEndpoint")
    @JsonProperty(value = "targetEndpoint")
    private String targetEndpoint;

    @JacksonXmlProperty(localName = "URL")
    @JsonProperty(value = "url")
    private String url;

}
