package fr.groupbees.apigeesdk.apigee.policies.security.basicauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "BasicAuthentication")
public class BasicAuthenticationPolicy extends Policy {

    @JacksonXmlProperty(localName = "Operation")
    @JsonProperty(value = "operation")
    private BasicAuthOperation operation;

    @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
    @JsonProperty(value = "ignoreUnresolvedVariables")
    private Boolean ignoreUnresolvedVariables;

    @JacksonXmlProperty(localName = "User")
    @JsonProperty(value = "user")
    private Ref user;

    @JacksonXmlProperty(localName = "Password")
    @JsonProperty(value = "password")
    private Ref password;

    @JacksonXmlProperty(localName = "AssignTo")
    @JsonProperty(value = "assignTo")
    private AssignTo assignTo;

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty(value = "source")
    private String source;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.ignoreUnresolvedVariables = true;
    }
}
