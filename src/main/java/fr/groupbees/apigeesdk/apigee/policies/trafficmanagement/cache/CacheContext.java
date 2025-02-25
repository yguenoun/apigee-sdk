package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.Data;

@Data
public class CacheContext {

  @JacksonXmlProperty(localName = "APIProxyName")
  @JsonProperty(value = "apiProxyName")
  private ValueRef<String> apiProxyName;

  @JacksonXmlProperty(localName = "TargetName")
  @JsonProperty(value = "targetName")
  private ValueRef<String> targetName;

  @JacksonXmlProperty(localName = "ProxyName")
  @JsonProperty(value = "proxyName")
  private ValueRef<String> proxyName;

}
