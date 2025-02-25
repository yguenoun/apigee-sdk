package fr.groupbees.apigeesdk.apigee.policies.mediation.xsltransform;

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
public class Parameters {

  @JacksonXmlProperty(isAttribute = true, localName = "ignoreUnresolvedVariables")
  @JsonProperty(value = "ignoreUnresolvedVariables")
  private Boolean ignoreUnresolvedVariables;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Parameter")
  @JsonProperty(value = "parameter")
  private List<Parameter> parameters;

}
