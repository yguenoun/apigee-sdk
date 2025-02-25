package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "GetOAuthV1Info")
public class GetOAuthV1InfoPolicy extends Policy {

    @JacksonXmlProperty(localName = "ConsumerKey")
    @JsonProperty(value = "consumerKey")
    private ValueRef<String> consumerKey;

    @JacksonXmlProperty(localName = "RequestToken")
    @JsonProperty(value = "requestToken")
    private ValueRef<String> requestToken;

    @JacksonXmlProperty(localName = "AccessToken")
    @JsonProperty(value = "accessToken")
    private ValueRef<String> accessToken;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

}
