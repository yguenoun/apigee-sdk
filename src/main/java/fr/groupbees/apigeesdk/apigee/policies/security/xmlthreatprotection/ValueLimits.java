package fr.groupbees.apigeesdk.apigee.policies.security.xmlthreatprotection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueLimits {

    @JacksonXmlProperty(localName = "Text")
    @JsonProperty(value = "text")
    private Integer text;

    @JacksonXmlProperty(localName = "Attribute")
    @JsonProperty(value = "attribute")
    private Integer attribute;

    @JacksonXmlProperty(localName = "NamespaceURI")
    @JsonProperty(value = "namespaceURI")
    private Integer namespaceURI;

    @JacksonXmlProperty(localName = "Comment")
    @JsonProperty(value = "comment")
    private Integer comment;

    @JacksonXmlProperty(localName = "ProcessingInstructionData")
    @JsonProperty(value = "processingInstructionData")
    private Integer processingInstructionData;

}
