package fr.groupbees.apigeesdk.apigee.policies.mediation.oasvalidation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "OASValidation")
public class OASValidationPolicy extends Policy {

  @JacksonXmlProperty(localName = "OASResource")
  @JsonProperty(value = "oasResource")
  private String oasResource; // must have suffix .json, .yml, .yaml

  @JacksonXmlProperty(localName = "Options")
  @JsonProperty(value = "options")
  private Options options;

  @JacksonXmlProperty(localName = "Source")
  @JsonProperty(value = "source")
  private String source; // message, request, response

  @Override
  public void defaultValues() {
    this.defaultHeader();
    this.source = "request";
  }

}
