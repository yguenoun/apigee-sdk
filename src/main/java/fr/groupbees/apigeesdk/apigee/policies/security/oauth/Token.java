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
public class Token {

    @JacksonXmlProperty(isAttribute = true, localName = "type")
    @JsonProperty(value = "type")
    private TokenType type;

    @JacksonXmlProperty(isAttribute = true, localName = "cascade")
    @JsonProperty(value = "cascade")
    private Boolean cascade;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private String value;

}
