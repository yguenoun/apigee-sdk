package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "AssignVariable")
public class AssignVariable {

  @JacksonXmlProperty(localName = "Name")
  @JsonProperty(value = "name")
  private String name;

  @JacksonXmlProperty(localName = "PropertySetRef")
  @JsonProperty(value = "propertySetRef")
  private String propertySetRef;

  @JacksonXmlProperty(localName = "Ref")
  @JsonProperty(value = "ref")
  private String ref;

  @JacksonXmlProperty(localName = "Value")
  @JsonProperty(value = "value")
  private String value;

  @JacksonXmlProperty(localName = "Template")
  @JsonProperty(value = "template")
  private ValueRef<String> template;

}
