package fr.groupbees.apigeesdk.apigee.policies.mediation.xmltojson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Path {

  @JacksonXmlProperty(isAttribute = true, localName = "unwrap")
  @JsonProperty(value = "unwrap")
  private Boolean unwrap;

  @JacksonXmlText
  @JsonProperty(value = "value")
  private String value;

}
