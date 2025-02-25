package fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityIdentifier {

  @JacksonXmlProperty(isAttribute = true, localName = "ref")
  @JsonProperty(value = "ref")
  private String ref;

  @JacksonXmlProperty(isAttribute = true, localName = "type")
  @JsonProperty(value = "type")
  private String type;

}
