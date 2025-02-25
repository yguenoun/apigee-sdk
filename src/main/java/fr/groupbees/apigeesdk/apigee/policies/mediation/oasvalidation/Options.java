package fr.groupbees.apigeesdk.apigee.policies.mediation.oasvalidation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Options {

  @JacksonXmlProperty(localName = "ValidateMessageBody")
  @JsonProperty(value = "validateMessageBody")
  private Boolean validateMessageBody;

  @JacksonXmlProperty(localName = "AllowUnspecifiedParameters")
  @JsonProperty(value = "allowUnspecifiedParameters")
  private AllowUnspecifiedParameters allowUnspecifiedParameters;

}
