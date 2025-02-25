package fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityType {

  @JacksonXmlProperty(isAttribute = true, localName = "value")
  @JsonProperty(value = "value")
  private String value;

}
