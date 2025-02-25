package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "JSONThreatProtection")
public class JSONThreatProtectionPolicy extends Policy {

    @JacksonXmlProperty(localName = "ArrayElementCount")
    @JsonProperty(value = "arrayElementCount")
    private Integer arrayElementCount;

    @JacksonXmlProperty(localName = "ContainerDepth")
    @JsonProperty(value = "containerDepth")
    private Integer containerDepth;

    @JacksonXmlProperty(localName = "ObjectEntryCount")
    @JsonProperty(value = "objectEntryCount")
    private Integer objectEntryCount;

    @JacksonXmlProperty(localName = "ObjectEntryNameLength")
    @JsonProperty(value = "objectEntryNameLength")
    private Integer objectEntryNameLength;

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty(value = "source")
    private String source;

    @JacksonXmlProperty(localName = "StringValueLength")
    @JsonProperty(value = "stringValueLength")
    private Integer stringValueLength;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.source = "request";
    }
}
