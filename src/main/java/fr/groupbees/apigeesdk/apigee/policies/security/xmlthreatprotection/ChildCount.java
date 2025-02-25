package fr.groupbees.apigeesdk.apigee.policies.security.xmlthreatprotection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildCount {

    @JacksonXmlProperty(isAttribute = true, localName = "includeComment")
    @JsonProperty(value = "includeComment")
    private Boolean includeComment;

    @JacksonXmlProperty(isAttribute = true, localName = "includeElement")
    @JsonProperty(value = "includeElement")
    private Boolean includeElement;

    @JacksonXmlProperty(isAttribute = true, localName = "includeProcessingInstruction")
    @JsonProperty(value = "includeProcessingInstruction")
    private Boolean includeProcessingInstruction;

    @JacksonXmlProperty(isAttribute = true, localName = "includeText")
    @JsonProperty(value = "includeText")
    private Boolean includeText;

    @JacksonXmlText
    @JsonProperty(value = "value")
    private Integer value;

}
