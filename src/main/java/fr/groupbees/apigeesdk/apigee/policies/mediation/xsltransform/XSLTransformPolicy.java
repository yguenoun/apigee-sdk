package fr.groupbees.apigeesdk.apigee.policies.mediation.xsltransform;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "XSL")
public class XSLTransformPolicy extends Policy {

  @JacksonXmlProperty(localName = "Source")
  @JsonProperty(value = "source")
  private String source;

  @JacksonXmlProperty(localName = "ResourceURL")
  @JsonProperty(value = "resourceURL")
  private String resourceURL;

  @JacksonXmlProperty(localName = "OutputVariable")
  @JsonProperty(value = "outputVariable")
  private String outputVariable;

  @JacksonXmlProperty(localName = "Parameters")
  @JsonProperty(value = "parameters")
  private Parameters parameters;

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }
}
