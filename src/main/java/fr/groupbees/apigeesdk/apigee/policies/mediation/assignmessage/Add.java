package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Add")
public class Add {

  @JacksonXmlProperty(localName = "Header")
  @JacksonXmlElementWrapper(localName = "Headers")
  @JsonProperty(value = "headers")
  private List<NameValue> headers;

  @JacksonXmlProperty(localName = "FormParam")
  @JacksonXmlElementWrapper(localName = "FormParams")
  @JsonProperty(value = "formParams")
  private List<NameValue> formParams;

  @JacksonXmlProperty(localName = "QueryParam")
  @JacksonXmlElementWrapper(localName = "QueryParams")
  @JsonProperty(value = "queryParams")
  private List<NameValue> queryParams;

}
