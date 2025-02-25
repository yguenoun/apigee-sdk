package fr.groupbees.apigeesdk.apigee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.flows.SharedFlow;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import fr.groupbees.apigeesdk.apigee.policies.extension.FlowCalloutPolicy;
import lombok.Data;

import java.util.LinkedHashSet;

@Data
@JacksonXmlRootElement(localName = "SharedFlowBundle")
public class SharedFlowBundle {

  @JacksonXmlProperty(isAttribute = true, localName = "name")
  @JsonProperty(value = "name")
  private String name;

  @JacksonXmlProperty(isAttribute = true, localName = "revision")
  @JsonProperty(value = "revision")
  private Integer revision;

  @JacksonXmlProperty(localName = "ConfigurationVersion")
  @JsonProperty(value = "configurationVersion")
  private ConfigurationVersion configurationVersion = new ConfigurationVersion();

  @JacksonXmlProperty(localName = "CreatedAt")
  @JsonProperty(value = "createdAt")
  private Long createdAt;

  @JacksonXmlProperty(localName = "CreatedBy")
  @JsonProperty(value = "createdBy")
  private String createdBy;

  @JacksonXmlProperty(localName = "LastModifiedAt")
  @JsonProperty(value = "lastModifiedAt")
  private Long lastModifiedAt;

  @JacksonXmlProperty(localName = "LastModifiedBy")
  @JsonProperty(value = "lastModifiedBy")
  private String lastModifiedBy;

  @JacksonXmlProperty(localName = "DisplayName")
  @JsonProperty(value = "displayName")
  private String displayName;

  @JacksonXmlProperty(localName = "Description")
  @JsonProperty(value = "description")
  private String description;

  @JacksonXmlElementWrapper(localName = "Policies")
  @JacksonXmlProperty(localName = "Policy")
  @JsonProperty(value = "policies")
  private LinkedHashSet<String> policies = new LinkedHashSet<>();

  @JacksonXmlElementWrapper(localName = "Resources")
  @JacksonXmlProperty(localName = "Resource")
  @JsonProperty(value = "resources")
  private LinkedHashSet<String> resources = new LinkedHashSet<>();

  @JacksonXmlProperty(localName = "Spec")
  @JsonProperty(value = "spec")
  private String spec;

  @JacksonXmlElementWrapper(localName = "SharedFlows")
  @JacksonXmlProperty(localName = "SharedFlow")
  @JsonProperty(value = "sharedFlows")
  private LinkedHashSet<String> sharedFlows = new LinkedHashSet<>();

  // TODO study about manifest version and manifest files

  public void defaultValues() {
    revision = 1;
    configurationVersion = new ConfigurationVersion(4, 0);
  }

  public void addPolicy(final Policy policy) {
    policies.add(policy.getName());
    if (policy instanceof FlowCalloutPolicy) {
      FlowCalloutPolicy flowCallout = (FlowCalloutPolicy) policy;
      sharedFlows.add(flowCallout.getSharedFlowBundle());
    }
  }

  public void addResource(final Resource resource) {
    resources.add(resource.getType().name() + "://" + resource.getPath().getFileName().toString());
  }

  public void addSharedFlow(final SharedFlow sharedFlow) {
    sharedFlows.add(sharedFlow.getName());
  }

}
