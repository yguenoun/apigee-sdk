package fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "AccessEntity")
public class AccessEntityPolicy extends Policy {

  @JacksonXmlProperty(localName = "EntityIdentifier")
  @JsonProperty(value = "entityIdentifier")
  private EntityIdentifier entityIdentifier;

  @JacksonXmlProperty(localName = "EntityType")
  @JsonProperty(value = "entityType")
  private EntityType entityType;

  @JacksonXmlProperty(localName = "SecondaryIdentifier")
  @JsonProperty(value = "secondaryIdentifier")
  private EntityIdentifier secondaryIdentifier;

  @JacksonXmlElementWrapper(localName = "Identifiers")
  @JacksonXmlProperty(localName = "Identifier")
  @JsonProperty(value = "identifiers")
  private List<Identifier> identifiers;

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }

}
