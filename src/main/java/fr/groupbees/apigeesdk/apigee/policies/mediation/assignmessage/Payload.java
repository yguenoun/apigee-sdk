package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

  @JacksonXmlProperty(isAttribute = true, localName = "contentType")
  private String contentType;

  @JacksonXmlProperty(isAttribute = true, localName = "variablePrefix")
  private String variablePrefix;

  @JacksonXmlProperty(isAttribute = true, localName = "variableSuffix")
  private String variableSuffix;

  @JacksonXmlText
  private String value;

}
