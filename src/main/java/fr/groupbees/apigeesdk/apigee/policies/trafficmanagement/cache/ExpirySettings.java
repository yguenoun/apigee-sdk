package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.Data;

@Data
public class ExpirySettings {

  @JacksonXmlProperty(localName = "ExpiryDate")
  private ValueRef<Integer> expiryDate;

  @JacksonXmlProperty(localName = "TimeOfDay")
  private ValueRef<Integer> timeOfDay;

  @JsonProperty("timeoutInSeconds")
  @JacksonXmlProperty(localName = "TimeoutInSeconds")
  private ValueRef<Integer> timeoutInSec;

}
