package fr.groupbees.apigeesdk.apigee.flows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"name", "condition"})
public class Step {

    @JacksonXmlProperty(localName = "Name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(localName = "Condition")
    @JsonProperty(value = "condition")
    private String condition;

    public Step(final String name) {
        this.name = name;
    }

    public Step(final Policy policy) {
        this.name = policy.getName();
    }
}
