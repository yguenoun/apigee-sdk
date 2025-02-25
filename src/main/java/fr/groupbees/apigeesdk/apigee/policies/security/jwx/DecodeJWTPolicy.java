package fr.groupbees.apigeesdk.apigee.policies.security.jwx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "DecodeJWT")
public class DecodeJWTPolicy extends Policy {

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty(value = "source")
    private String source;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.source = "request.header.authorization";
    }
}
