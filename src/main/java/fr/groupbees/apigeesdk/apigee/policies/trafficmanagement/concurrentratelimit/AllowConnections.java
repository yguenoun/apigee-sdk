package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.concurrentratelimit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllowConnections {

  @JacksonXmlProperty(isAttribute = true, localName = "count")
  @JsonProperty(value = "count")
  private Integer count;

  @JacksonXmlProperty(isAttribute = true, localName = "ttl")
  @JsonProperty(value = "ttl")
  private Integer ttl;

}
