package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateResponse {

    @JacksonXmlProperty(isAttribute = true, localName = "enabled")
    @JsonProperty(value = "enabled")
    private Boolean enabled;

}
