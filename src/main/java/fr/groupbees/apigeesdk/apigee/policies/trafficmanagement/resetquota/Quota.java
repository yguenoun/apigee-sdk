package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.resetquota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quota {

  @JacksonXmlProperty(isAttribute = true, localName = "name")
  @JsonProperty(value = "name")
  private String name;

  @JacksonXmlProperty(isAttribute = true, localName = "ref")
  @JsonProperty(value = "ref")
  private String ref;

  @JacksonXmlProperty(localName = "Identifier")
  @JsonProperty(value = "identifier")
  private Identifier identifier;

}
