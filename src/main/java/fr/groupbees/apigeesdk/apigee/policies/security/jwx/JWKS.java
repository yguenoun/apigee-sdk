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
public class JWKS {

    @JacksonXmlProperty(isAttribute = true, localName = "ref")
    @JsonProperty(value = "ref")
    private String ref;

    @JacksonXmlProperty(isAttribute = true, localName = "uri")
    @JsonProperty(value = "uri")
    private String uri;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private String value;

}
