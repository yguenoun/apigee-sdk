package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "JavaCallout")
public class JavaCalloutPolicy extends Policy {

  @JacksonXmlProperty(localName = "ClassName")
  @JsonProperty(value = "className")
  private String className;

  @JacksonXmlProperty(localName = "ResourceURL")
  @JsonProperty(value = "resourceURL")
  private String resourceURL;

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }

}
