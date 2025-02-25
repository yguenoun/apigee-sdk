package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.quota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsynchronousConfiguration {

  @JacksonXmlProperty(localName = "SyncIntervalInSeconds")
  @JsonProperty(value = "syncIntervalInSeconds")
  private Integer syncIntervalInSeconds;

  @JacksonXmlProperty(localName = "SyncMessageCount")
  @JsonProperty(value = "syncMessageCount")
  private Integer syncMessageCount;

}
