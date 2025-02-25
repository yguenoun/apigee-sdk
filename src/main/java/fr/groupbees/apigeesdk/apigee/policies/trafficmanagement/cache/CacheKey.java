package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class CacheKey {

  @JacksonXmlProperty(localName = "Prefix")
  @JsonProperty(value = "prefix")
  private String prefix;

  @JacksonXmlProperty(localName = "KeyFragment")
  @JacksonXmlElementWrapper(useWrapping = false)
  @JsonProperty(value = "keyFragment")
  private List<CacheKeyValueRef<String>> keyFragments;
}
