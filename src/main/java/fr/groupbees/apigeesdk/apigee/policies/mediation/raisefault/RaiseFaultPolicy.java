package fr.groupbees.apigeesdk.apigee.policies.mediation.raisefault;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "RaiseFault")
public class RaiseFaultPolicy extends Policy {

  @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
  @JsonProperty(value = "ignoreUnresolvedVariables")
  private Boolean ignoreUnresolvedVariables;

  @JacksonXmlProperty(localName = "FaultResponse")
  @JsonProperty(value = "faultResponse")
  private FaultResponse faultResponse;

  @JacksonXmlProperty(localName = "ShortFaultReason")
  @JsonProperty(value = "shortFaultReason")
  private Boolean shortFaultReason;

  @Override
  public void defaultValues() {
    this.defaultHeader();
    this.ignoreUnresolvedVariables = true;
    this.shortFaultReason = false;
  }

}
