package fr.groupbees.apigeesdk.apigee.policies.security.accesscontrol;

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
public class MatchRule {

    @JacksonXmlProperty(isAttribute = true, localName = "action")
    @JsonProperty(value = "action")
    private IPRuleAction action;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "SourceAddress")
    @JsonProperty(value = "sourceAddress")
    private List<SourceAddress> sourceAddresses;

}
