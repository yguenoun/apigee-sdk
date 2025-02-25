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
public class IPRules {

    @JacksonXmlProperty(isAttribute = true, localName = "noRuleMatchAction")
    @JsonProperty(value = "noRuleMatchAction")
    private IPRuleAction noRuleMatchAction;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "MatchRule")
    @JsonProperty(value = "matchRule")
    private List<MatchRule> matchRules;

}
