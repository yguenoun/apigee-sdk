package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.quota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Allow")
public class AllowClass {

  @JacksonXmlProperty(isAttribute = true, localName = "class")
  @JsonProperty(value = "class")
  private String quotaClass;

  @JacksonXmlProperty(isAttribute = true, localName = "count")
  @JsonProperty(value = "count")
  private Integer count;

}
