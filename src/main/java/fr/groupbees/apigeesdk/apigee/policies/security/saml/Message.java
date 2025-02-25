package fr.groupbees.apigeesdk.apigee.policies.security.saml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.xml.Namespace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlElementWrapper(localName = "Namespaces")
    @JacksonXmlProperty(localName = "Namespace")
    @JsonProperty(value = "namespaces")
    private List<Namespace> namespaces;

    @JacksonXmlProperty(localName = "XPath")
    @JsonProperty(value = "xPath")
    private String xPath;

}
