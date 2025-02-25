package fr.groupbees.apigeesdk.apigee.policies.extension.servicecallout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Add;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Copy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Remove;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @JacksonXmlProperty(isAttribute = true, localName = "clearPayload")
    @JsonProperty(value = "clearPayload")
    private Boolean clearPayload;

    @JacksonXmlProperty(isAttribute = true, localName = "variable")
    @JsonProperty(value = "variable")
    private String variable;

    @JacksonXmlProperty(localName = "Add")
    @JsonProperty(value = "add")
    private Add add;

    @JacksonXmlProperty(localName = "Set")
    @JsonProperty(value = "set")
    private Set set;

    @JacksonXmlProperty(localName = "Copy")
    @JsonProperty(value = "copy")
    private Copy copy;

    @JacksonXmlProperty(localName = "Remove")
    @JsonProperty(value = "remove")
    private Remove remove;

    @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
    @JsonProperty(value = "ignoreUnresolvedVariables")
    private Boolean ignoreUnresolvedVariables;

}
