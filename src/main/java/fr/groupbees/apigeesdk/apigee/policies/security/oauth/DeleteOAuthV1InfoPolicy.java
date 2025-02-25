package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "DeleteOAuthV1Info")
public class DeleteOAuthV1InfoPolicy extends Policy {

    @JacksonXmlProperty(localName = "AccessToken")
    @JsonProperty(value = "accessToken")
    private ValueRef<String> accessToken;

    @JacksonXmlProperty(localName = "RequestToken")
    @JsonProperty(value = "requestToken")
    private ValueRef<String> requestToken;

    @JacksonXmlProperty(localName = "Verifier")
    @JsonProperty(value = "verifier")
    private ValueRef<String> verifier;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

}
