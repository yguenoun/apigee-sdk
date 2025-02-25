package fr.groupbees.apigeesdk.apigee.commons.httptargetconnection;

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
public class HTTPMonitorRequest {

    @JacksonXmlProperty(localName = "ConnectTimeoutInSec")
    @JsonProperty(value = "connectTimeoutInSec")
    private Integer connectTimeoutInSec;

    @JacksonXmlProperty(localName = "SocketReadTimeoutInSec")
    @JsonProperty(value = "socketReadTimeoutInSec")
    private Integer socketReadTimeoutInSec;

    @JacksonXmlProperty(localName = "Port")
    @JsonProperty(value = "port")
    private Integer port;

    @JacksonXmlProperty(localName = "Verb")
    @JsonProperty(value = "verb")
    private String verb;

    @JacksonXmlProperty(localName = "Path")
    @JsonProperty(value = "path")
    private String path;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Header")
    @JsonProperty(value = "header")
    private List<NameValue> headers;

}
