package fr.groupbees.apigeesdk.apigee.policies.security.saml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ValidateSAMLAssertion")
public class ValidateSAMLAssertionPolicy extends Policy {

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty(value = "source")
    private Message source;

    @JacksonXmlProperty(localName = "TrustStore")
    @JsonProperty(value = "trustStore")
    private String trustStore;

    @JacksonXmlProperty(localName = "RemoveAssertion")
    @JsonProperty(value = "removeAssertion")
    private Boolean removeAssertion;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

}
