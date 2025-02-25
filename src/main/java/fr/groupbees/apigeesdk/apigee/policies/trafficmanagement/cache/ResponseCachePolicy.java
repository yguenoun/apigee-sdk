package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ResponseCache")
public class ResponseCachePolicy extends Policy {

  @JacksonXmlProperty(localName = "CacheKey")
  @JsonProperty(value = "cacheKey")
  private CacheKey cacheKey;

  @JacksonXmlProperty(localName = "Scope")
  @JsonProperty(value = "scope")
  private CacheScope scope;

  @JacksonXmlProperty(localName = "ExpirySettings")
  @JsonProperty(value = "expirySettings")
  private ExpirySettings expirySettings;

  @JacksonXmlProperty(localName = "CacheResource")
  @JsonProperty(value = "cacheResource")
  private String cacheResource;

  @JacksonXmlProperty(localName = "CacheLookupTimeoutInSeconds")
  @JsonProperty(value = "cacheLookupTimeoutInSeconds")
  private Integer cacheLookupTimeoutInSeconds;

  @JacksonXmlProperty(localName = "SkipCacheLookup")
  @JsonProperty(value = "skipCacheLookup")
  private String skipCacheLookup;

  @JacksonXmlProperty(localName = "SkipCachePopulation")
  @JsonProperty(value = "skipCachePopulation")
  private String skipCachePopulation;

  @JacksonXmlProperty(localName = "UseAcceptHeader")
  @JsonProperty(value = "useAcceptHeader")
  private Boolean useAcceptHeader;

  @JacksonXmlProperty(localName = "UseResponseCacheHeaders")
  @JsonProperty(value = "useResponseCacheHeaders")
  private Boolean useResponseCacheHeaders;

  @JacksonXmlProperty(localName = "ExcludeErrorResponse")
  @JsonProperty(value = "excludeErrorResponse")
  private Boolean excludeErrorResponse;

  public ResponseCachePolicy() {
    this.cacheKey = new CacheKey();
    this.expirySettings = new ExpirySettings();
  }

  public void defaultValues() {
    this.defaultHeader();
    this.cacheLookupTimeoutInSeconds = 30;
    this.excludeErrorResponse = false;
    this.scope = CacheScope.Exclusive;
    this.useAcceptHeader = false;
    this.useResponseCacheHeaders = false;
  }

}
