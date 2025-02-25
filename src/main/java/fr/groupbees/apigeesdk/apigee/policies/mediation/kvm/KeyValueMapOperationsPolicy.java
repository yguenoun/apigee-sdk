package fr.groupbees.apigeesdk.apigee.policies.mediation.kvm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "KeyValueMapOperations")
public class KeyValueMapOperationsPolicy extends Policy {

  @JacksonXmlProperty(isAttribute = true, localName = "mapIdentifier")
  @JsonProperty(value = "mapIdentifier")
  private String mapIdentifier;

  @JacksonXmlProperty(localName = "Scope")
  @JsonProperty(value = "scope")
  private KVMScope scope;

  @JacksonXmlProperty(localName = "ExclusiveCache")
  @JsonProperty(value = "exclusiveCache")
  private String exclusiveCache;

  @JacksonXmlProperty(localName = "ExpiryTimeInSecs")
  @JsonProperty(value = "expiryTimeInSecs")
  private Integer expiryTimeInSecs;

  @JacksonXmlElementWrapper(localName = "InitialEntries")
  @JacksonXmlProperty(localName = "Entry")
  @JsonProperty(value = "initialEntries")
  private List<Entry> initialEntries;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Delete")
  @JsonProperty(value = "delete")
  private List<Delete> delete;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Put")
  @JsonProperty(value = "put")
  private List<Put> put;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Get")
  @JsonProperty(value = "get")
  private List<Get> get;

  @Override
  public void defaultValues() {
    this.defaultHeader();
    this.expiryTimeInSecs = 300;
    this.scope = KVMScope.environment;
  }

  // must have at least one of <Delete>, <Get> or <Put>

}
