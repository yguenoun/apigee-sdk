package fr.groupbees.apigeesdk.apigee.policies.security.saml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "GenerateSAMLAssertion")
public class GenerateSAMLAssertionPolicy extends Policy {

    @JacksonXmlProperty(isAttribute = true, localName = "ignoreContentType")
    @JsonProperty(value = "ignoreContentType")
    private Boolean ignoreContentType;

    @JacksonXmlProperty(localName = "Issuer")
    @JsonProperty(value = "issuer")
    private ValueRef<String> issuer;

    @JacksonXmlProperty(localName = "KeyStore")
    @JsonProperty(value = "keyStore")
    private KeyStore keyStore;

    @JacksonXmlProperty(localName = "OutputVariable")
    @JsonProperty(value = "outputVariable")
    private OutputVariable outputVariable;

    @JacksonXmlProperty(localName = "CanonicalizationAlgorithm")
    @JsonProperty(value = "canonicalizationAlgorithm")
    private String canonicalizationAlgorithm;

    @JacksonXmlProperty(localName = "SignatureAlgorithm")
    @JsonProperty(value = "signatureAlgorithm")
    private String signatureAlgorithm;

    @JacksonXmlProperty(localName = "Subject")
    @JsonProperty(value = "subject")
    private ValueRef<String> subject;

    @JacksonXmlProperty(localName = "Template")
    @JsonProperty(value = "template")
    private Template template;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

}
