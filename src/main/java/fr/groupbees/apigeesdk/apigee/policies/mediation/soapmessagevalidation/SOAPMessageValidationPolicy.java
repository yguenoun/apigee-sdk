package fr.groupbees.apigeesdk.apigee.policies.mediation.soapmessagevalidation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "SOAPMessageValidation")
public class SOAPMessageValidationPolicy extends Policy {

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Element")
  @JsonProperty(value = "element")
  private List<Element> elements;

  @JacksonXmlProperty(localName = "SOAPMessage")
  @JsonProperty(value = "soapMessage")
  private SOAPMessage soapMessage;

  @JacksonXmlProperty(localName = "Source")
  @JsonProperty(value = "source")
  private String source;

  @JacksonXmlProperty(localName = "ResourceURL")
  @JsonProperty(value = "resourceURL")
  private String resourceURL;

  @Override
  public void defaultValues() {
    this.defaultHeader();
    this.source = "request";
  }

}
