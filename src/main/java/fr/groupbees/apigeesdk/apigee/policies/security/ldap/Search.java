package fr.groupbees.apigeesdk.apigee.policies.security.ldap;

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
public class Search {

    @JacksonXmlProperty(localName = "BaseDN")
    @JsonProperty(value = "baseDN")
    private String baseDN;

    @JacksonXmlProperty(localName = "SearchQuery")
    @JsonProperty(value = "searchQuery")
    private String searchQuery;

    @JacksonXmlProperty(localName = "Scope")
    @JsonProperty(value = "scope")
    private LDAPScope scope;

    @JacksonXmlElementWrapper(localName = "Attributes")
    @JacksonXmlProperty(localName = "Attribute")
    @JsonProperty(value = "attributes")
    private List<String> attributes;

}
