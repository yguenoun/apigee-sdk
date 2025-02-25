package fr.groupbees.apigeesdk.apigee.policies.security.ldap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "LDAP")
public class LDAPPolicy extends Policy {

    @JacksonXmlProperty(localName = "LdapConnectorClass")
    @JsonProperty(value = "ldapConnectorClass")
    private String ldapConnectorClass;

    @JacksonXmlProperty(localName = "LdapResource")
    @JsonProperty(value = "ldapResource")
    private String ldapResource;

    @JacksonXmlProperty(localName = "Authentication")
    @JsonProperty(value = "authentication")
    private Authentication authentication;

    @JacksonXmlProperty(localName = "Search")
    @JsonProperty(value = "search")
    private Search search;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

}
