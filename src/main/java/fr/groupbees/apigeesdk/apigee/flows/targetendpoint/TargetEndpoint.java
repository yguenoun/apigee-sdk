package fr.groupbees.apigeesdk.apigee.flows.targetendpoint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.LocalTargetConnection;
import fr.groupbees.apigeesdk.apigee.commons.httptargetconnection.HTTPTargetConnection;
import fr.groupbees.apigeesdk.apigee.flows.DefaultFaultRule;
import fr.groupbees.apigeesdk.apigee.flows.FaultRule;
import fr.groupbees.apigeesdk.apigee.flows.Flow;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedHashSet;

@Data
@JacksonXmlRootElement(localName = "TargetEndpoint")
@JsonPropertyOrder({
        "description",
        "preFlow",
        "flows",
        "postFlow",
        "faultRules",
        "defaultFaultRule",
        "localTargetConnection",
        "httpTargetConnection",
        "scriptTarget"
})
public class TargetEndpoint {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(localName = "Description")
    @JsonProperty(value = "description")
    private String description;

    @JacksonXmlProperty(localName = "PreFlow")
    @JsonProperty(value = "preFlow")
    private Flow preFlow = new Flow("PreFlow");

    @JacksonXmlElementWrapper(localName = "Flows")
    @JacksonXmlProperty(localName = "Flow")
    @JsonProperty(value = "flow")
    private LinkedHashSet<Flow> flows = new LinkedHashSet<>();

    @JacksonXmlProperty(localName = "PostFlow")
    @JsonProperty(value = "postFlow")
    private Flow postFlow = new Flow("PostFlow");

    @JacksonXmlProperty(localName = "DefaultFaultRule")
    @JsonProperty(value = "defaultFaultRule")
    private DefaultFaultRule defaultFaultRule;

    @JacksonXmlElementWrapper(localName = "FaultRules")
    @JacksonXmlProperty(localName = "FaultRule")
    @JsonProperty(value = "faultRules")
    private LinkedHashSet<FaultRule> faultRules = new LinkedHashSet<>();

    @JacksonXmlProperty(localName = "HTTPTargetConnection")
    @JsonProperty(value = "httpTargetConnection")
    private HTTPTargetConnection httpTargetConnection;

    @JacksonXmlProperty(localName = "LocalTargetConnection")
    @JsonProperty(value = "localTargetConnection")
    private LocalTargetConnection localTargetConnection;

    @JacksonXmlProperty(localName = "ScriptTarget")
    @JsonProperty(value = "scriptTarget")
    private ScriptTarget scriptTarget;

    public void prependFlows(final Flow... flows) {
        LinkedHashSet<Flow> newFlows = new LinkedHashSet<>();
        newFlows.addAll(Arrays.asList(flows));
        newFlows.addAll(this.flows);
        this.flows = newFlows;
    }

    public void appendFlows(final Flow... flows) {
        this.flows.addAll(Arrays.asList(flows));
    }

    public void prependFaultRules(final FaultRule... faultRules) {
        LinkedHashSet<FaultRule> newFaultRules = new LinkedHashSet<>();
        newFaultRules.addAll(Arrays.asList(faultRules));
        newFaultRules.addAll(this.faultRules);
        this.faultRules = newFaultRules;
    }

    public void appendFaultRules(final FaultRule... faultRules) {
        this.faultRules.addAll(Arrays.asList(faultRules));
    }

    @JsonIgnore
    public boolean isUsingTargetServers() {
        return httpTargetConnection != null &&
                httpTargetConnection.getLoadBalancer() != null &&
                httpTargetConnection.getLoadBalancer().getServers() != null &&
                !httpTargetConnection.getLoadBalancer().getServers().isEmpty();
    }

}
