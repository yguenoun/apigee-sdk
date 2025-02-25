package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.quota;

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
public class ClassAllow {

  @JacksonXmlProperty(isAttribute = true, localName = "ref")
  @JsonProperty(value = "ref")
  private String ref;

  @JacksonXmlProperty(localName = "Allow")
  @JacksonXmlElementWrapper(useWrapping = false)
  @JsonProperty(value = "allow")
  private List<AllowClass> allows;

  public ClassAllow(final String ref) {
    this.ref = ref;
  }

}
