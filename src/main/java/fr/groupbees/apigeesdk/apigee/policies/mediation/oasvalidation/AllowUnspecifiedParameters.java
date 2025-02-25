package fr.groupbees.apigeesdk.apigee.policies.mediation.oasvalidation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllowUnspecifiedParameters {

  @JacksonXmlProperty(localName = "Header")
  @JsonProperty(value = "header")
  private Boolean header;

  @JacksonXmlProperty(localName = "Query")
  @JsonProperty(value = "query")
  private Boolean query;

  @JacksonXmlProperty(localName = "Cookie")
  @JsonProperty(value = "cookie")
  private Boolean cookie;

}
