package fr.groupbees.apigeesdk.apigee.policies.mediation.raisefault;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaultResponse {

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "AssignVariable")
  @JsonProperty(value = "assignVariable")
  private List<AssignVariable> assignVariables;

  @JacksonXmlProperty(localName = "Copy")
  @JsonProperty(value = "copy")
  private Copy copy;

  @JacksonXmlProperty(localName = "Add")
  @JsonProperty(value = "add")
  private Add add;

  @JacksonXmlProperty(localName = "Remove")
  @JsonProperty(value = "remove")
  private Remove remove;

  @JacksonXmlProperty(localName = "Set")
  @JsonProperty(value = "set")
  private Set set;

}
