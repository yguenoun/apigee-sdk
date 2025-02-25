package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "InvalidateCache")
public class InvalidateCachePolicy extends Policy {

  @JacksonXmlProperty(localName = "CacheKey")
  @JsonProperty(value = "cacheKey")
  private CacheKey cacheKey = new CacheKey();

  @JacksonXmlProperty(localName = "CacheContext")
  @JsonProperty(value = "cacheContext")
  private CacheContext cacheContext;

  @JacksonXmlProperty(localName = "CacheResource")
  @JsonProperty(value = "cacheResource")
  private String cacheResource;

  @JacksonXmlProperty(localName = "PurgeChildEntries")
  @JsonProperty(value = "purgeChildEntries")
  private Boolean purgeChildEntries;

  @JacksonXmlProperty(localName = "Scope")
  @JsonProperty(value = "scope")
  private CacheScope scope;

  public void defaultValues() {
    this.defaultHeader();
    this.purgeChildEntries = false;
    this.scope = CacheScope.Exclusive;
  }

}
