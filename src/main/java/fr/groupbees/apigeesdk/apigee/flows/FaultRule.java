package fr.groupbees.apigeesdk.apigee.flows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"name", "condition", "step"})
public class FaultRule {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(localName = "Condition")
    @JsonProperty(value = "condition")
    private String condition;

    @JacksonXmlProperty(localName = "Step")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty(value = "step")
    private LinkedHashSet<Step> steps = new LinkedHashSet<>();

    public FaultRule(final String name) {
        this.name = name;
    }

}
