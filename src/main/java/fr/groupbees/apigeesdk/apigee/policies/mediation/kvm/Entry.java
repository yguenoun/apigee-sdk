package fr.groupbees.apigeesdk.apigee.policies.mediation.kvm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {

  @JacksonXmlProperty(localName = "Key")
  @JsonProperty(value = "key")
  private Key key;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Value")
  @JsonProperty(value = "value")
  private List<String> values;

}
