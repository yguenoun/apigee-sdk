package fr.groupbees.apigeesdk.apigee.policies.extension.extensioncallout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ConnectorCallout")
public class ExtensionCalloutPolicy extends Policy {

    @JacksonXmlProperty(localName = "Connector")
    @JsonProperty(value = "connector")
    private String connector;

    @JacksonXmlProperty(localName = "Action")
    @JsonProperty(value = "action")
    private String action;

    @JacksonXmlProperty(localName = "Input")
    @JsonProperty(value = "input")
    private String input;

    @JacksonXmlProperty(localName = "Output")
    @JsonProperty(value = "output")
    private Output output;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

}
