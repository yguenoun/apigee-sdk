package fr.groupbees.apigeesdk.apigee.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "SSLInfo")
public class SSLInfo {

    @JacksonXmlProperty(localName = "Enabled")
    @JsonProperty(value = "enabled")
    private Boolean enabled;

    @JacksonXmlProperty(localName = "ClientAuthEnabled")
    @JsonProperty(value = "clientAuthEnabled")
    private Boolean clientAuthEnabled;

    @JacksonXmlProperty(localName = "KeyStore")
    @JsonProperty(value = "keyStore")
    private String keyStore;

    @JacksonXmlProperty(localName = "KeyAlias")
    @JsonProperty(value = "keyAlias")
    private String keyAlias;

    @JacksonXmlProperty(localName = "TrustStore")
    @JsonProperty(value = "trustStore")
    private String trustStore;

    @JacksonXmlElementWrapper(localName = "Cyphers")
    @JacksonXmlProperty(localName = "Cypher")
    @JsonProperty(value = "cyphers")
    private List<String> cyphers;

    @JacksonXmlElementWrapper(localName = "Protocols")
    @JacksonXmlProperty(localName = "Protocol")
    @JsonProperty(value = "protocols")
    private List<String> protocols;

}
