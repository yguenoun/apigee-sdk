package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.resetquota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.Data;

@Data
public class ClassAllow extends Ref {

  @JacksonXmlProperty(localName = "Allow")
  @JsonProperty(value = "allow")
  private ValueRef<Integer> allow;

}
