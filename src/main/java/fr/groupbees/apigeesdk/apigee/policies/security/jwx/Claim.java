package fr.groupbees.apigeesdk.apigee.policies.security.jwx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "ref")
    @JsonProperty(value = "ref")
    private String ref;

    @JacksonXmlProperty(isAttribute = true, localName = "type")
    @JsonProperty(value = "type")
    private String type; // string, number, boolean, or map

    @JacksonXmlProperty(isAttribute = true, localName = "array")
    @JsonProperty(value = "array")
    private Boolean array;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private String value;

}
