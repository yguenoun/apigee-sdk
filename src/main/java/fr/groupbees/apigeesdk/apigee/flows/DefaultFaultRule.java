package fr.groupbees.apigeesdk.apigee.flows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultFaultRule {

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    @JsonProperty(value = "name")
    private String name = "default-faultrule";

    @JacksonXmlProperty(localName = "Step")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty(value = "step")
    private LinkedHashSet<Step> steps = new LinkedHashSet<>();

    @JacksonXmlProperty(localName = "AlwaysEnforce")
    @JsonProperty(value = "alwaysEnforce")
    private Boolean alwaysEnforce;

}
