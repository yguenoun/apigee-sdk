package fr.groupbees.apigeesdk.apigee.policies.mediation.xmltojson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "XMLtoJSON")
public class XMLtoJSONPolicy extends Policy {

  @JacksonXmlProperty(localName = "Source")
  @JsonProperty(value = "source")
  private String source;

  @JacksonXmlProperty(localName = "OutputVariable")
  @JsonProperty(value = "outputVariable")
  private String outputVariable;

  @JacksonXmlProperty(localName = "Format")
  @JsonProperty(value = "format")
  private String format; // xml.com, yahoo, google, badgerFish
  // one /\ or another \/ never both
  @JacksonXmlProperty(localName = "Options")
  @JsonProperty(value = "options")
  private Options options;

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }

}
