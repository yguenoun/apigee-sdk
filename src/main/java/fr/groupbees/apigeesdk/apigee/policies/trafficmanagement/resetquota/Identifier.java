package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.resetquota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.Data;

@Data
public class Identifier {

  @JacksonXmlProperty(isAttribute = true, localName = "name")
  @JsonProperty(value = "name")
  private String name;

  @JacksonXmlProperty(isAttribute = true, localName = "ref")
  @JsonProperty(value = "ref")
  private String ref;

  @JacksonXmlProperty(localName = "Allow")
  @JsonProperty(value = "allow")
  private ValueRef<Integer> allow;

  @JacksonXmlProperty(localName = "Class")
  @JsonProperty(value = "class")
  private ClassAllow classAllow;

}
