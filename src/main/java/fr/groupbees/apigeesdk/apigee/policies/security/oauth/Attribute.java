package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "ref")
    @JsonProperty(value = "ref")
    private String ref;

    @JacksonXmlProperty(isAttribute = true, localName = "display")
    @JsonProperty(value = "display")
    private Boolean display;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private String value;

}
