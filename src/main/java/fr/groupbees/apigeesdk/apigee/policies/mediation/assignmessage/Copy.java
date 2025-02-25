package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Copy")
public class Copy {

  @JacksonXmlProperty(isAttribute = true, localName = "source")
  @JsonProperty(value = "source")
  private String source;

  @JacksonXmlProperty(localName = "Path")
  @JsonProperty(value = "path")
  private Boolean path;

  @JacksonXmlProperty(localName = "Payload")
  @JsonProperty(value = "payload")
  private Boolean payload;

  @JacksonXmlProperty(localName = "ReasonPhrase")
  @JsonProperty(value = "reasonPhrase")
  private Boolean reasonPhrase;

  @JacksonXmlProperty(localName = "StatusCode")
  @JsonProperty(value = "statusCode")
  private Boolean statusCode;

  @JacksonXmlProperty(localName = "Verb")
  @JsonProperty(value = "verb")
  private Boolean verb;

  @JacksonXmlProperty(localName = "Version")
  @JsonProperty(value = "version")
  private Boolean version;

  @JacksonXmlProperty(localName = "Headers")
  @JsonProperty(value = "headers")
  private HeadersWrapper headers;

  @JacksonXmlProperty(localName = "FormParams")
  @JsonProperty(value = "formParams")
  private FormParamsWrapper formParams;

  @JacksonXmlProperty(localName = "QueryParams")
  @JsonProperty(value = "queryParams")
  private QueryParamsWrapper queryParams;

  public void setHeaders(Object object) {
    this.headers = HeadersWrapper.setter(object);
  }

  public void setFormParams(Object object) {
    this.formParams = FormParamsWrapper.setter(object);
  }

  public void setQueryParams(Object object) {
    this.queryParams = QueryParamsWrapper.setter(object);
  }

}
