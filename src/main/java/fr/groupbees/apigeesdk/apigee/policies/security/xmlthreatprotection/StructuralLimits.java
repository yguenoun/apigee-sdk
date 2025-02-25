package fr.groupbees.apigeesdk.apigee.policies.security.xmlthreatprotection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructuralLimits {

    @JacksonXmlProperty(localName = "NodeDepth")
    @JsonProperty(value = "nodeDepth")
    private Integer nodeDepth;

    @JacksonXmlProperty(localName = "AttributeCountPerElement")
    @JsonProperty(value = "attributeCountPerElement")
    private Integer attributeCountPerElement;

    @JacksonXmlProperty(localName = "NamespaceCountPerElement")
    @JsonProperty(value = "namespaceCountPerElement")
    private Integer namespaceCountPerElement;

    @JacksonXmlProperty(localName = "ChildCount")
    @JsonProperty(value = "childCount")
    private ChildCount childCount;

}
