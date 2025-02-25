package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.NamedValueRef;
import fr.groupbees.apigeesdk.apigee.flows.FaultRule;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "FlowCallout")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowCalloutPolicy extends Policy {

  @JacksonXmlProperty(localName = "SharedFlowBundle")
  @JsonProperty(value = "sharedFlowBundle")
  private String sharedFlowBundle;

  @JacksonXmlElementWrapper(localName = "Parameters")
  @JacksonXmlProperty(localName = "Parameter")
  @JsonProperty(value = "parameters")
  private List<NamedValueRef<String>> parameters;

  @JacksonXmlElementWrapper(localName = "FaultRules")
  @JacksonXmlProperty(localName = "FaultRules")
  @JsonProperty(value = "faultRules")
  private LinkedHashSet<FaultRule> faultRules = new LinkedHashSet<>();

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }

}
