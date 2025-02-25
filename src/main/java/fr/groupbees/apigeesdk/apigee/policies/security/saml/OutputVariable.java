package fr.groupbees.apigeesdk.apigee.policies.security.saml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputVariable {

    @JacksonXmlProperty(localName = "FlowVariable")
    @JsonProperty(value = "flowVariable")
    private String flowVariable;

    @JacksonXmlProperty(localName = "Message")
    @JsonProperty(value = "message")
    private Message message;

}
