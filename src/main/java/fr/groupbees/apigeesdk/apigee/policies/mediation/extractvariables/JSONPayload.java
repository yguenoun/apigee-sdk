package fr.groupbees.apigeesdk.apigee.policies.mediation.extractvariables;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONPayload {

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(isAttribute = true, localName = "Variable")
  @JsonProperty(value = "variable")
  private List<JSONVariable> variables;

}
