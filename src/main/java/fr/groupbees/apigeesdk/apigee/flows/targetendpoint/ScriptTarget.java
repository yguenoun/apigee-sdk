package fr.groupbees.apigeesdk.apigee.flows.targetendpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptTarget {

    @JacksonXmlProperty(localName = "ResourceURL")
    @JsonProperty(value = "resourceURL")
    private String resourceURL;

    @JacksonXmlElementWrapper(localName = "EnvironmentVariables")
    @JacksonXmlProperty(localName = "EnvironmentVariable")
    @JsonProperty(value = "environmentVariables")
    private List<NameValue> environmentVariables;

    @JacksonXmlElementWrapper(localName = "Arguments")
    @JacksonXmlProperty(localName = "Argument")
    @JsonProperty(value = "arguments")
    private List<String> arguments;

}
