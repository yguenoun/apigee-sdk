package fr.groupbees.apigeesdk.apigee.policies.security.xmlthreatprotection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameLimits {

    @JacksonXmlProperty(localName = "Element")
    @JsonProperty(value = "element")
    private Integer element;

    @JacksonXmlProperty(localName = "Attribute")
    @JsonProperty(value = "attribute")
    private Integer attribute;

    @JacksonXmlProperty(localName = "NamespacePrefix")
    @JsonProperty(value = "namespacePrefix")
    private Integer namespacePrefix;

    @JacksonXmlProperty(localName = "ProcessingInstructionTarget")
    @JsonProperty(value = "processingInstructionTarget")
    private Integer processingInstructionTarget;

}
