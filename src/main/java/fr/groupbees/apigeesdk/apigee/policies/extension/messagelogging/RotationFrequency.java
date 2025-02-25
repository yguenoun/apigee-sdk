package fr.groupbees.apigeesdk.apigee.policies.extension.messagelogging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RotationFrequency {

    @JacksonXmlProperty(isAttribute = true, localName = "unit")
    @JsonProperty(value = "unit")
    private String unit;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private Integer value;

}
