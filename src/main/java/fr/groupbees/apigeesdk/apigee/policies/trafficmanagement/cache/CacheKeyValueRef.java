package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheKeyValueRef<T> {

  @JacksonXmlText
  @JsonProperty(value = "value")
  private T value;

  @JacksonXmlProperty(isAttribute = true)
  @JsonProperty(value = "ref")
  private String ref;

  @JacksonXmlProperty(isAttribute = true)
  @JsonProperty(value = "type")
  private String type;

}
