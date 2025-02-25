package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

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
public class SupportedGrantTypes {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "GrantType")
    @JsonProperty(value = "grantType")
    private List<OAuthGrantType> grantTypes;

}
