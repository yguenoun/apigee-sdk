package fr.groupbees.apigeesdk.apigee.policies.security.accesscontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "AccessControl")
public class AccessControlPolicy extends Policy {

    @JacksonXmlProperty(localName = "IgnoreTrueClientIPHeader")
    @JsonProperty(value = "ignoreTrueClientIPHeader")
    private Boolean ignoreTrueClientIPHeader;

    @JacksonXmlProperty(localName = "IPRules")
    @JsonProperty(value = "ipRules")
    private IPRules ipRules;

    @JacksonXmlProperty(localName = "ValidateBasedOn")
    @JsonProperty(value = "validateBasedOn")
    private String validateBasedOn; // X_FORWARDED_FOR_ALL_IP, X_FORWARDED_FOR_FIRST_IP, X_FORWARDED_FOR_LAST_IP

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.ignoreTrueClientIPHeader = true;
        this.validateBasedOn = "X_FORWARDED_FOR_ALL_IP";
    }
}
