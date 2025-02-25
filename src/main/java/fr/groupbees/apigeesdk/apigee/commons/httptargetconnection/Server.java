package fr.groupbees.apigeesdk.apigee.commons.httptargetconnection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    @JsonProperty(value = "name")
    private String name;

    @JacksonXmlProperty(localName = "Weight")
    @JsonProperty(value = "weight")
    private Integer weight;

    @JacksonXmlProperty(localName = "IsFallback")
    @JsonProperty(value = "isFallback")
    private Boolean isFallback;

    public Server(final String name) {
        this.name = name;
    }

}
