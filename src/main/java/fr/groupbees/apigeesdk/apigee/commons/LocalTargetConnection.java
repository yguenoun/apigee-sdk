package fr.groupbees.apigeesdk.apigee.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "LocalTargetConnection")
public class LocalTargetConnection {

    @JacksonXmlProperty(localName = "APIProxy")
    @JsonProperty(value = "apiProxy")
    private String apiProxy;

    @JacksonXmlProperty(localName = "ProxyEndpoint")
    @JsonProperty(value = "proxyEndpoint")
    private String proxyEndpoint;

    @JacksonXmlProperty(localName = "Path")
    @JsonProperty(value = "path")
    private String path;

}
