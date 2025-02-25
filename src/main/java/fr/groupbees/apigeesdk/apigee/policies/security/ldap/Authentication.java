package fr.groupbees.apigeesdk.apigee.policies.security.ldap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authentication {

    @JacksonXmlProperty(localName = "BaseDN")
    @JsonProperty(value = "baseDN")
    private String baseDN;

    @JacksonXmlProperty(localName = "UserName")
    @JsonProperty(value = "userName")
    private ValueRef<String> userName;

    @JacksonXmlProperty(localName = "Password")
    @JsonProperty(value = "password")
    private ValueRef<String> password;

    @JacksonXmlProperty(localName = "SearchQuery")
    @JsonProperty(value = "searchQuery")
    private String searchQuery;

    @JacksonXmlProperty(localName = "Scope")
    @JsonProperty(value = "scope")
    private LDAPScope scope;

}
