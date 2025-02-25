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
@JacksonXmlRootElement(localName = "VerifyJWS")
public class VerifyJWSPolicy extends Policy {

    @JacksonXmlProperty(localName = "Algorithm")
    @JsonProperty(value = "algorithm")
    private String algorithm; // HS256, HS384, HS512, RS256, RS384, RS512, ES256, ES384, ES512, PS256, PS384, PS512

    @JacksonXmlElementWrapper(localName = "AdditionalHeaders")
    @JacksonXmlProperty(localName = "Claim")
    @JsonProperty(value = "additionalHeaders")
    private List<Claim> additionalHeaders;

    @JacksonXmlProperty(localName = "DetachContent")
    @JsonProperty(value = "detachContent")
    private String detachContent;

    @JacksonXmlProperty(localName = "IgnoreCriticalHeaders")
    @JsonProperty(value = "ignoreCriticalHeaders")
    private Boolean ignoreCriticalHeaders;

    @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
    @JsonProperty(value = "ignoreUnresolvedVariables")
    private Boolean ignoreUnresolvedVariables;

    @JacksonXmlProperty(localName = "KnownHeaders")
    @JsonProperty(value = "knownHeaders")
    private ValueRef<String> knownHeaders;

    @JacksonXmlProperty(localName = "PublicKey")
    @JsonProperty(value = "publicKey")
    private PublicKey publicKey; // Use only when the algorithm is one of RS256/RS384/RS512, PS256/PS384/PS512, or ES256/ES384/ES512.

    @JacksonXmlProperty(localName = "SecretKey")
    @JsonProperty(value = "secretKey")
    private SecretKey secretKey; // Use only when the algorithm is one of HS256, HS384, HS512.

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty(value = "source")
    private String source;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.ignoreCriticalHeaders = false;
        this.ignoreUnresolvedVariables = false;
        this.source = "request.header.authorization";
    }

}
