package fr.groupbees.apigeesdk.apigee.policies.security.jwx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretKey {

    @JacksonXmlProperty(localName = "Id")
    @JsonProperty(value = "id")
    private ValueRef<String> id;

    @JacksonXmlProperty(localName = "Value")
    @JsonProperty(value = "value")
    private Ref value; // must have prefix 'private.'

}
