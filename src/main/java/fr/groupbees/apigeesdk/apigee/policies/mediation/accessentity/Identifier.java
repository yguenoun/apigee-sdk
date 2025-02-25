package fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Identifier {

  @JacksonXmlProperty(localName = "EntityIdentifier")
  @JsonProperty(value = "entityIdentifier")
  private EntityIdentifier entityIdentifier;

  @JacksonXmlProperty(localName = "SecondaryIdentifier")
  @JsonProperty(value = "secondaryIdentifier")
  private EntityIdentifier secondaryIdentifier;

}
