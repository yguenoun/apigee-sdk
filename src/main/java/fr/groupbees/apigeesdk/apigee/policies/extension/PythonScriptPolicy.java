package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Script")
public class PythonScriptPolicy extends Policy {

  @JacksonXmlProperty(localName = "IncludeURL")
  @JsonProperty(value = "includeURL")
  private String includeURL;

  @JacksonXmlProperty(localName = "ResourceURL")
  @JsonProperty(value = "resourceURL")
  private String resourceURL;

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }

}
