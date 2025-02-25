package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Set {

  @JacksonXmlProperty(localName = "Path")
  @JsonProperty(value = "path")
  private String path;

  @JacksonXmlProperty(localName = "Payload")
  @JsonProperty(value = "payload")
  private Payload payload;

  @JacksonXmlProperty(localName = "ReasonPhrase")
  @JsonProperty(value = "reasonPhrase")
  private String reasonPhrase;

  @JacksonXmlProperty(localName = "StatusCode")
  @JsonProperty(value = "statusCode")
  private String statusCode;

  @JacksonXmlProperty(localName = "Verb")
  @JsonProperty(value = "verb")
  private String verb;

  @JacksonXmlProperty(localName = "Version")
  @JsonProperty(value = "version")
  private String version;

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
