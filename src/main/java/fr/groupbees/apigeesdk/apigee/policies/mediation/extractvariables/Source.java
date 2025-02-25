package fr.groupbees.apigeesdk.apigee.policies.mediation.extractvariables;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Source {

  @JacksonXmlProperty(isAttribute = true, localName = "clearPayload")
  @JsonProperty(value = "clearPayload")
  private Boolean clearPayload;

  @JacksonXmlText
  @JsonProperty(value = "value")
  private String value;

}
