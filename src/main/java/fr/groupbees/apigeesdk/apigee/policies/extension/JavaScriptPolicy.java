package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.SSLInfo;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Javascript")
public class JavaScriptPolicy extends Policy {

  @JacksonXmlProperty(isAttribute = true)
  @JsonProperty(value = "timeLimit")
  private String timeLimit;

  @JacksonXmlProperty(localName = "IncludeURL")
  @JsonProperty(value = "includeURL")
  private String includeURL;

  @JacksonXmlProperty(localName = "ResourceURL")
  @JsonProperty(value = "resourceURL")
  private String resourceURL;

  @JacksonXmlProperty(localName = "SSLInfo")
  @JsonProperty(value = "sslInfo")
  private SSLInfo sslInfo;

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }

}
