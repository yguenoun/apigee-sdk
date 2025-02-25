package fr.groupbees.apigeesdk.apigee.policies.security.jwx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicKey {

    @JacksonXmlProperty(localName = "JWKS")
    @JsonProperty(value = "jwks")
    private JWKS jwks;

    @JacksonXmlProperty(localName = "Value")
    @JsonProperty(value = "value")
    private ValueRef<String> value;

    @JacksonXmlProperty(localName = "Certificate")
    @JsonProperty(value = "certificate")
    private ValueRef<String> certificate;

}
