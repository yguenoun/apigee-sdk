package fr.groupbees.apigeesdk.apigee.commons.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Namespace {

    @JacksonXmlProperty(isAttribute = true, localName = "prefix")
    @JsonProperty(value = "prefix")
    private String prefix;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private String value;

}
