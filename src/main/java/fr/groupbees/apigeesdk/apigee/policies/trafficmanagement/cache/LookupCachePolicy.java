package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "LookupCache")
public class LookupCachePolicy extends Policy {

  @JacksonXmlProperty(localName = "CacheKey")
  @JsonProperty(value = "cacheKey")
  private CacheKey cacheKey;

  @JacksonXmlProperty(localName = "CacheResource")
  @JsonProperty(value = "cacheResource")
  private String cacheResource;

  @JacksonXmlProperty(localName = "CacheLookupTimeoutInSeconds")
  @JsonProperty(value = "cacheLookupTimeoutInSeconds")
  private Integer cacheLookupTimeoutInSeconds;

  @JacksonXmlProperty(localName = "AssignTo")
  @JsonProperty(value = "assignTo")
  private String assignTo;

  @JacksonXmlProperty(localName = "Scope")
  @JsonProperty(value = "scope")
  private CacheScope scope;

  public void defaultValues() {
    this.defaultHeader();
    this.cacheLookupTimeoutInSeconds = 30;
    this.scope = CacheScope.Exclusive;
  }

}
