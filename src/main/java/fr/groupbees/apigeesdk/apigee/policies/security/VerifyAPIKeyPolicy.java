package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "VerifyAPIKey")
public class VerifyAPIKeyPolicy extends Policy {

    @JacksonXmlProperty(localName = "APIKey")
    @JsonProperty(value = "apiKey")
    private Ref apiKey;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }
}
