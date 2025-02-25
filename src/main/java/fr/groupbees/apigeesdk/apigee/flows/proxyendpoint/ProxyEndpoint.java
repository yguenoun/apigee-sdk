package fr.groupbees.apigeesdk.apigee.flows.proxyendpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.flows.DefaultFaultRule;
import fr.groupbees.apigeesdk.apigee.flows.FaultRule;
import fr.groupbees.apigeesdk.apigee.flows.Flow;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedHashSet;

@Data
@JacksonXmlRootElement(localName = "ProxyEndpoint")
@JsonPropertyOrder({
        "description",
        "preFlow",
        "flows",
        "postFlow",
        "faultRules",
        "defaultFaultRule",
        "postClientFlow",
        "httpProxyConnection",
        "routeRules"
})
public class ProxyEndpoint {

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

    @JacksonXmlProperty(localName = "PostClientFlow")
    @JsonProperty(value = "postClientFlow")
    private PostClientFlow postClientFlow;

    @JacksonXmlProperty(localName = "DefaultFaultRule")
    @JsonProperty(value = "defaultFaultRule")
    private DefaultFaultRule defaultFaultRule;

    @JacksonXmlElementWrapper(localName = "FaultRules")
    @JacksonXmlProperty(localName = "FaultRule")
    @JsonProperty(value = "faultRules")
    private LinkedHashSet<FaultRule> faultRules = new LinkedHashSet<>();

    @JacksonXmlProperty(localName = "HTTPProxyConnection")
    @JsonProperty(value = "httpProxyConnection")
    private HTTPProxyConnection httpProxyConnection = new HTTPProxyConnection();

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "RouteRule")
    @JsonProperty(value = "routeRule")
    private LinkedHashSet<RouteRule> routeRules = new LinkedHashSet<>();

    public void prependFlows(final Flow... flows) {
        LinkedHashSet<Flow> newFlows = new LinkedHashSet<>();
        newFlows.addAll(Arrays.asList(flows));
        newFlows.addAll(this.flows);
        this.flows = newFlows;
    }

    public void appendFlows(final Flow... flows) {
        this.flows.addAll(Arrays.asList(flows));
    }

    public void prependRouteRules(final RouteRule... routeRules) {
        LinkedHashSet<RouteRule> newRouteRules = new LinkedHashSet<>();
        newRouteRules.addAll(Arrays.asList(routeRules));
        newRouteRules.addAll(this.routeRules);
        this.routeRules = newRouteRules;
    }

    public void appendRouteRules(final RouteRule... routeRules) {
        this.routeRules.addAll(Arrays.asList(routeRules));
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

}
