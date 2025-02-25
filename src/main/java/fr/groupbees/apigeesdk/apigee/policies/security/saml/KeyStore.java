package fr.groupbees.apigeesdk.apigee.policies.security.saml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyStore {

    @JacksonXmlProperty(localName = "Name")
    @JsonProperty(value = "name")
    private ValueRef<String> name;

    @JacksonXmlProperty(localName = "Alias")
    @JsonProperty(value = "alias")
    private ValueRef<String> alias;

}
