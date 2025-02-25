package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.NamedValueRef;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "SetOAuthV2Info")
public class SetOAuthV2InfoPolicy extends Policy {

    @JacksonXmlProperty(localName = "AccessToken")
    @JsonProperty(value = "accessToken")
    private ValueRef<String> accessToken;

    @JacksonXmlElementWrapper(localName = "Attributes")
    @JacksonXmlProperty(localName = "Attribute")
    @JsonProperty(value = "attributes")
    private List<NamedValueRef<String>> attributes;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

}
