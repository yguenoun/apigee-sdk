package fr.groupbees.apigeesdk.apigee.policies.security.jwx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "GenerateJWT")
public class GenerateJWTPolicy extends Policy {

    @JacksonXmlProperty(localName = "Algorithm")
    @JsonProperty(value = "algorithm")
    private String algorithm; // HS256, HS384, HS512, RS256, RS384, RS512, ES256, ES384, ES512, PS256, PS384, PS512

    @JacksonXmlProperty(localName = "Audience")
    @JsonProperty(value = "audience")
    private ValueRef<String> audience;

    @JacksonXmlProperty(localName = "AdditionalClaims")
    @JsonProperty(value = "additionalClaims")
    private AdditionalClaims additionalClaims;

    @JacksonXmlElementWrapper(localName = "AdditionalHeaders")
    @JacksonXmlProperty(localName = "Claim")
    @JsonProperty(value = "additionalHeaders")
    private List<Claim> additionalHeaders;

    @JacksonXmlProperty(localName = "CriticalHeaders")
    @JsonProperty(value = "criticalHeaders")
    private ValueRef<String> criticalHeaders;

    @JacksonXmlProperty(localName = "ExpiresIn")
    @JsonProperty(value = "expiresIn")
    private String expiresIn; // must have one of this suffixes: ms, s, m, h, d

    @JacksonXmlProperty(localName = "Id")
    @JsonProperty(value = "id")
    private ValueRef<String> id;

    @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
    @JsonProperty(value = "ignoreUnresolvedVariables")
    private Boolean ignoreUnresolvedVariables;

    @JacksonXmlProperty(localName = "Issuer")
    @JsonProperty(value = "issuer")
    private ValueRef<String> issuer;

    /*
    Valid formats:
    yyyy-MM-dd'T'HH:mm:ss.SSSZ
    EEE, dd MMM yyyy HH:mm:ss zzz
    EEEE, dd-MMM-yy HH:mm:ss zzz
    EEE MMM d HH:mm:ss yyyy
    For relative time must have a valid suffix: s, m, h
    */
    @JacksonXmlProperty(localName = "NotBefore")
    @JsonProperty(value = "notBefore")
    private String notBefore;

    @JacksonXmlProperty(localName = "OutputVariable")
    @JsonProperty(value = "outputVariable")
    private String outputVariable;

    @JacksonXmlProperty(localName = "PrivateKey")
    @JsonProperty(value = "privateKey")
    private PrivateKey privateKey; // Use only when the algorithm is one of RS256/RS384/RS512, PS256/PS384/PS512, or ES256/ES384/ES512.

    @JacksonXmlProperty(localName = "SecretKey")
    @JsonProperty(value = "secretKey")
    private SecretKey secretKey; // Use only when the algorithm is one of HS256/HS384/HS512.

    @JacksonXmlProperty(localName = "Subject")
    @JsonProperty(value = "subject")
    private ValueRef<String> subject;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.ignoreUnresolvedVariables = false;
    }

}
