package fr.groupbees.apigeesdk.apigee.policies.extension.servicecallout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.LocalTargetConnection;
import fr.groupbees.apigeesdk.apigee.commons.httptargetconnection.HTTPTargetConnection;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ServiceCallout")
public class ServiceCalloutPolicy extends Policy {

    @JacksonXmlProperty(localName = "Request")
    @JsonProperty(value = "request")
    private Request request;

    @JacksonXmlProperty(localName = "Response")
    @JsonProperty(value = "response")
    private String response;

    @JacksonXmlProperty(localName = "Timeout")
    @JsonProperty(value = "timeout")
    private Integer timeout;

    @JacksonXmlProperty(localName = "HTTPTargetConnection")
    @JsonProperty(value = "httpTargetConnection")
    private HTTPTargetConnection httpTargetConnection;

    @JacksonXmlProperty(localName = "LocalTargetConnection")
    @JsonProperty(value = "localTargetConnection")
    private LocalTargetConnection localTargetConnection;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.timeout = 55000;
    }

}
