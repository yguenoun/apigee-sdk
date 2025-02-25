package fr.groupbees.apigeesdk.apigee.policies.security.xmlthreatprotection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "XMLThreatProtection")
public class XMLThreatProtectionPolicy extends Policy {

    @JacksonXmlProperty(localName = "NameLimits")
    @JsonProperty(value = "nameLimits")
    private NameLimits nameLimits;

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty(value = "source")
    private String source;

    @JacksonXmlProperty(localName = "StructuralLimits")
    @JsonProperty(value = "structuralLimits")
    private StructuralLimits structuralLimits;

    @JacksonXmlProperty(localName = "ValueLimits")
    @JsonProperty(value = "valueLimits")
    private ValueLimits valueLimits;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.source = "request";
    }

}
