package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignTo {

  @JacksonXmlProperty(isAttribute = true, localName = "createNew")
  @JsonProperty(value = "createNew")
  private Boolean createNew;

  @JacksonXmlProperty(isAttribute = true, localName = "transport")
  @JsonProperty(value = "transport")
  private String transport;

  @JacksonXmlProperty(isAttribute = true, localName = "type")
  @JsonProperty(value = "type")
  private String type;

  @JacksonXmlText
  @JsonProperty(value = "value")
  private String value;

}
