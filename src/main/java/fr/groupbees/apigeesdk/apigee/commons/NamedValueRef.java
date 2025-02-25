package fr.groupbees.apigeesdk.apigee.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NamedValueRef<T> {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private T value;

    @JacksonXmlProperty(isAttribute = true, localName = "ref")
    @JsonProperty(value = "ref")
    private String ref;


}
