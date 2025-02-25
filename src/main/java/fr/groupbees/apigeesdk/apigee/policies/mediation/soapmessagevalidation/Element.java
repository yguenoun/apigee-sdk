package fr.groupbees.apigeesdk.apigee.policies.mediation.soapmessagevalidation;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Element {

  @JacksonXmlProperty(isAttribute = true, localName = "namespace")
  private String namespace;

  @JacksonXmlText
  private String value;

}
