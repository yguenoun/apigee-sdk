package fr.groupbees.apigeesdk.apigee.policies.mediation.kvm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Get {

  @JacksonXmlProperty(isAttribute = true, localName = "assignTo")
  @JsonProperty(value = "assignTo")
  private String assignTo;

  @JacksonXmlProperty(isAttribute = true, localName = "index")
  @JsonProperty(value = "index")
  private Integer index;

  @JacksonXmlProperty(localName = "Key")
  @JsonProperty(value = "key")
  private Key key;

}
