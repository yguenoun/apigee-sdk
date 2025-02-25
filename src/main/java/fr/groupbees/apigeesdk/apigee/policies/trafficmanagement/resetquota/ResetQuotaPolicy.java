package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.resetquota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ResetQuota")
public class ResetQuotaPolicy extends Policy {

  @JacksonXmlProperty(localName = "Quota")
  @JsonProperty(value = "quota")
  private Quota quota = new Quota();

  public void defaultValues() {
    this.defaultHeader();
  }

}
