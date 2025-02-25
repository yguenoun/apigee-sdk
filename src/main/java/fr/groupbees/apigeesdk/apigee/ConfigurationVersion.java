package fr.groupbees.apigeesdk.apigee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationVersion {

  @JacksonXmlProperty(isAttribute = true, localName = "majorVersion")
  @JsonProperty(value = "majorVersion")
  private Integer majorVersion = 4;

  @JacksonXmlProperty(isAttribute = true, localName = "minorVersion")
  @JsonProperty(value = "minorVersion")
  private Integer minorVersion = 0;

}
