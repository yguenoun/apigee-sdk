package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.quota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allow {

  @JacksonXmlProperty(isAttribute = true, localName = "count")
  @JsonProperty(value = "count")
  private Integer count;

  @JacksonXmlProperty(isAttribute = true, localName = "countRef")
  @JsonProperty(value = "countRef")
  private String countRef;

  @JacksonXmlProperty(localName = "Class")
  @JsonProperty(value = "class")
  private ClassAllow classAllow;

}
