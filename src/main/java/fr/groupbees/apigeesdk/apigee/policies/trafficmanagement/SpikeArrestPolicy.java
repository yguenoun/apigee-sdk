package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "SpikeArrest")
public class SpikeArrestPolicy extends Policy {

  @JacksonXmlProperty(localName = "Identifier")
  @JsonProperty(value = "identifier")
  private Ref identifier = new Ref();

  @JacksonXmlProperty(localName = "MessageWeight")
  @JsonProperty(value = "messageWeight")
  private Ref messageWeight = new Ref();

  @JacksonXmlProperty(localName = "Rate")
  @JsonProperty(value = "rate")
  private ValueRef<String> rate;

  @JacksonXmlProperty(localName = "UseEffectiveCount")
  @JsonProperty(value = "useEffectiveCount")
  private Boolean useEffectiveCount;

  public void defaultValues() {
    this.defaultHeader();
    this.useEffectiveCount = false;
  }

}
