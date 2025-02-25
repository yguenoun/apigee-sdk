package fr.groupbees.apigeesdk.apigee.policies.security.saml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {

    @JacksonXmlProperty(isAttribute = true, localName = "ignoreUnresolvedVariables")
    private Boolean ignoreUnresolvedVariables;

    @JacksonXmlText
    private String value;

}
