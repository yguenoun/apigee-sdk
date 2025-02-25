package fr.groupbees.apigeesdk.apigee.policies.mediation.extractvariables;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONVariable {

  @JacksonXmlProperty(isAttribute = true, localName = "name")
  @JsonProperty(value = "name")
  private String name;

  @JacksonXmlProperty(isAttribute = true, localName = "type")
  @JsonProperty(value = "type")
  private String type;

  @JacksonXmlProperty(localName = "JSONPath")
  @JsonProperty(value = "jsonPath")
  private String jsonPath;

}
