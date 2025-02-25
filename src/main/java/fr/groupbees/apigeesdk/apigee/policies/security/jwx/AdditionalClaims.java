package fr.groupbees.apigeesdk.apigee.policies.security.jwx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalClaims {

    @JacksonXmlProperty(isAttribute = true, localName = "ref")
    @JsonProperty(value = "ref")
    private String ref;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Claim")
    @JsonProperty(value = "claim")
    private List<Claim> claims;

}
