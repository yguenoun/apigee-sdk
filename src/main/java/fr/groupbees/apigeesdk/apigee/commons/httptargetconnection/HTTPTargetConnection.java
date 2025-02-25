package fr.groupbees.apigeesdk.apigee.commons.httptargetconnection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import fr.groupbees.apigeesdk.apigee.commons.SSLInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "HTTPTargetConnection")
public class HTTPTargetConnection {

    @JacksonXmlProperty(localName = "URL")
    @JsonProperty(value = "url")
    private String url;

    @JacksonXmlProperty(localName = "Path")
    @JsonProperty(value = "path")
    private String path;

    @JacksonXmlProperty(localName = "LoadBalancer")
    @JsonProperty(value = "loadBalancer")
    private LoadBalancer loadBalancer;

    @JacksonXmlProperty(localName = "SSLInfo")
    @JsonProperty(value = "sslInfo")
    private SSLInfo sslInfo;

    @JacksonXmlElementWrapper(localName = "Properties")
    @JacksonXmlProperty(localName = "Property")
    @JsonProperty(value = "properties")
    private List<NameValue> properties;

    @JacksonXmlProperty(localName = "HealthMonitor")
    @JsonProperty(value = "healthMonitor")
    private HealthMonitor healthMonitor;

}
