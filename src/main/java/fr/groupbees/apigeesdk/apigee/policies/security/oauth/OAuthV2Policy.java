package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "OAuthV2")
public class OAuthV2Policy extends Policy {

    @JacksonXmlProperty(localName = "AccessToken")
    @JsonProperty(value = "accessToken")
    private String accessToken;

    @JacksonXmlProperty(localName = "AccessTokenPrefix")
    @JsonProperty(value = "accessTokenPrefix")
    private String accessTokenPrefix;

    @JacksonXmlProperty(localName = "AppEndUser")
    @JsonProperty(value = "appEndUser")
    private String appEndUser;

    @JacksonXmlElementWrapper(localName = "Attributes")
    @JacksonXmlProperty(localName = "Attribute")
    @JsonProperty(value = "attributes")
    private List<Attribute> attributes;

    @JacksonXmlProperty(localName = "ClientId")
    @JsonProperty(value = "clientId")
    private String clientId;

    @JacksonXmlProperty(localName = "Code")
    @JsonProperty(value = "code")
    private String code;

    @JacksonXmlProperty(localName = "ExpiresIn")
    @JsonProperty(value = "expiresIn")
    private ValueRef<String> expiresIn;

    @JacksonXmlProperty(localName = "ExternalAccessToken")
    @JsonProperty(value = "externalAccessToken")
    private String externalAccessToken;

    @JacksonXmlProperty(localName = "ExternalAuthorization")
    @JsonProperty(value = "externalAuthorization")
    private Boolean externalAuthorization;

    @JacksonXmlProperty(localName = "ExternalAuthorizationCode")
    @JsonProperty(value = "externalAuthorizationCode")
    private String externalAuthorizationCode;

    @JacksonXmlProperty(localName = "ExternalRefreshToken")
    @JsonProperty(value = "externalRefreshToken")
    private String externalRefreshToken;

    @JacksonXmlProperty(localName = "GenerateResponse")
    @JsonProperty(value = "generateResponse")
    private GenerateResponse generateResponse;

    @JacksonXmlProperty(localName = "GenerateErrorResponse")
    @JsonProperty(value = "generateErrorResponse")
    private GenerateResponse generateErrorResponse;

    @JacksonXmlProperty(localName = "GrantType")
    @JsonProperty(value = "grantType")
    private String grantType;

    @JacksonXmlProperty(localName = "Operation")
    @JsonProperty(value = "operation")
    private OAuthOperation operation;

    @JacksonXmlProperty(localName = "PassWord")
    @JsonProperty(value = "passWord")
    private String passWord;

    @JacksonXmlProperty(localName = "RedirectUri")
    @JsonProperty(value = "redirectUri")
    private String redirectUri;

    @JacksonXmlProperty(localName = "RefreshToken")
    @JsonProperty(value = "refreshToken")
    private String refreshToken;

    @JacksonXmlProperty(localName = "RefreshTokenExpiresIn")
    @JsonProperty(value = "refreshTokenExpiresIn")
    private ValueRef<String> refreshTokenExpiresIn;

    @JacksonXmlProperty(localName = "ResponseType")
    @JsonProperty(value = "responseType")
    private String responseType;

    @JacksonXmlProperty(localName = "ReuseRefreshToken")
    @JsonProperty(value = "reuseRefreshToken")
    private Boolean reuseRefreshToken;

    @JacksonXmlProperty(localName = "Scope")
    @JsonProperty(value = "scope")
    private String scope;

    @JacksonXmlProperty(localName = "State")
    @JsonProperty(value = "state")
    private String state;

    @JacksonXmlProperty(localName = "StoreToken")
    @JsonProperty(value = "storeToken")
    private Boolean storeToken;

    @JacksonXmlProperty(localName = "SupportedGrantTypes")
    @JsonProperty(value = "supportedGrantTypes")
    private SupportedGrantTypes supportedGrantTypes;

    @JacksonXmlElementWrapper(localName = "Tokens")
    @JacksonXmlProperty(localName = "Token")
    @JsonProperty(value = "tokens")
    private List<Token> tokens;

    @JacksonXmlProperty(localName = "UserName")
    @JsonProperty(value = "userName")
    private String userName;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.accessTokenPrefix = "Bearer";
        this.clientId = "request.formparam.client_id";
        this.code = "request.formparam.code";
        this.generateResponse = new GenerateResponse(false);
        this.generateErrorResponse = new GenerateResponse(false);
        this.grantType = "request.formparam.grant_type";
        this.passWord = "request.formparam.password";
        this.redirectUri = "request.formparam.redirect_uri";
        this.refreshToken = "request.formparam.refresh_token";
        this.responseType = "request.formparam.response_type";
        this.reuseRefreshToken = false;
        this.storeToken = false;
        this.supportedGrantTypes =
                new SupportedGrantTypes(Arrays.asList(OAuthGrantType.authorization_code,
                        OAuthGrantType.implicit
                ));
        this.userName = "request.formparam.username";
    }

}
