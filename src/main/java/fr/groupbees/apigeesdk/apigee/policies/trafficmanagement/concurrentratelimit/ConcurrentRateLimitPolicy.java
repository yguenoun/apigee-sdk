package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.concurrentratelimit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ConcurrentRateLimit")
public class ConcurrentRateLimitPolicy extends Policy {

  @JacksonXmlProperty(localName = "AllowConnections")
  @JsonProperty(value = "allowConnections")
  private AllowConnections allowConnections;

  @JacksonXmlProperty(localName = "Distributed")
  @JsonProperty(value = "distributed")
  private Boolean distributed;

  @JacksonXmlProperty(localName = "StrictOnTtl")
  @JsonProperty(value = "strictOnTtl")
  private Boolean strictOnTtl;

  @JacksonXmlProperty(localName = "TargetIdentifier")
  @JsonProperty(value = "targetIdentifier")
  private TargetIdentifier targetIdentifier;

  public ConcurrentRateLimitPolicy() {
    this.allowConnections = new AllowConnections();
  }

  public void defaultValues() {
    this.defaultHeader();
    this.distributed = false;
    this.strictOnTtl = false;
  }

}
