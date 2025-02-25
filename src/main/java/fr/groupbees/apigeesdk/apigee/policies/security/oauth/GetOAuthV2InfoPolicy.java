package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "GetOAuthV2Info")
public class GetOAuthV2InfoPolicy extends Policy {

    @JacksonXmlProperty(localName = "AccessToken")
    @JsonProperty(value = "accessToken")
    private ValueRef<String> accessToken;

    @JacksonXmlProperty(localName = "AuthorizationCode")
    @JsonProperty(value = "authorizationCode")
    private ValueRef<String> authorizationCode;

    @JacksonXmlProperty(localName = "ClientId")
    @JsonProperty(value = "clientId")
    private ValueRef<String> clientId;

    @JacksonXmlProperty(localName = "IgnoreAccessTokenStatus")
    @JsonProperty(value = "ignoreAccessTokenStatus")
    private Boolean ignoreAccessTokenStatus;

    @JacksonXmlProperty(localName = "RefreshToken")
    @JsonProperty(value = "refreshToken")
    private ValueRef<String> refreshToken;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.ignoreAccessTokenStatus = false;
        this.accessToken = new ValueRef<>(null, "request.formparam.access_token");
        this.authorizationCode = new ValueRef<>(null, "request.formparam.access_token");
        this.clientId = new ValueRef<>(null, "request.formparam.access_token");
        this.refreshToken = new ValueRef<>(null, "request.formparam.access_token");
    }

}
