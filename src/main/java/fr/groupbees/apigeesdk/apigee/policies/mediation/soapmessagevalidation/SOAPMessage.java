package fr.groupbees.apigeesdk.apigee.policies.mediation.soapmessagevalidation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOAPMessage {

  @JacksonXmlProperty(isAttribute = true, localName = "version")
  @JsonProperty(value = "version")
  private String version;
  // [ 1.1 | 1.2 | 1.1/1.2 ]
}
