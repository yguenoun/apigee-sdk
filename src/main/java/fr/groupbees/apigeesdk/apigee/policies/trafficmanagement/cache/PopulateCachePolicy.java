package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "PopulateCache")
public class PopulateCachePolicy extends Policy {

  @JacksonXmlProperty(localName = "CacheKey")
  @JsonProperty(value = "cacheKey")
  private CacheKey cacheKey;

  @JacksonXmlProperty(localName = "CacheResource")
  @JsonProperty(value = "cacheResource")
  private String cacheResource;

  @JacksonXmlProperty(localName = "Scope")
  @JsonProperty(value = "scope")
  private CacheScope scope;

  @JacksonXmlProperty(localName = "ExpirySettings")
  @JsonProperty(value = "expirySettings")
  private ExpirySettings expirySettings;

  @JacksonXmlProperty(localName = "Source")
  @JsonProperty(value = "source")
  private String source;

  public PopulateCachePolicy() {
    this.cacheKey = new CacheKey();
    this.expirySettings = new ExpirySettings();
  }

  public void defaultValues() {
    this.defaultHeader();
    this.scope = CacheScope.Exclusive;
  }

}
