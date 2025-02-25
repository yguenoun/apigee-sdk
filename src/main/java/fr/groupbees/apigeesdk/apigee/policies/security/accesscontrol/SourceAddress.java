package fr.groupbees.apigeesdk.apigee.policies.security.accesscontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceAddress {

    @JacksonXmlProperty(isAttribute = true, localName = "mask")
    @JsonProperty(value = "mask")
    private String mask;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private String value;

}
