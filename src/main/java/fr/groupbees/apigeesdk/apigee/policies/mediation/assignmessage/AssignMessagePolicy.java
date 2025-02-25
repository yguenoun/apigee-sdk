package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.flows.FaultRule;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "AssignMessage")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignMessagePolicy extends Policy {

  @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
  @JsonProperty(value = "ignoreUnresolvedVariables")
  private Boolean ignoreUnresolvedVariables;

  @JacksonXmlElementWrapper(localName = "FaultRules")
  @JacksonXmlProperty(localName = "FaultRule")
  @JsonProperty(value = "faultRules")
  private LinkedHashSet<FaultRule> faultRules;

  @JacksonXmlProperty(localName = "AssignTo")
  @JsonProperty(value = "assignTo")
  private AssignTo assignTo;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "AssignVariable")
  @JsonProperty(value = "assignVariable")
  private List<AssignVariable> assignVariables;

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

  @Override
  public void defaultValues() {
    this.defaultHeader();
    this.ignoreUnresolvedVariables = true;
  }

}
