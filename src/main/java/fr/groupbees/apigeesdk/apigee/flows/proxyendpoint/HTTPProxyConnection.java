package fr.groupbees.apigeesdk.apigee.flows.proxyendpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HTTPProxyConnection {

    @JacksonXmlProperty(localName = "BasePath")
    @JsonProperty(value = "basePath")
    private String basePath;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "VirtualHost")
    @JsonProperty(value = "virtualHost")
    private List<String> virtualHosts = new ArrayList<>();

    @JacksonXmlElementWrapper(localName = "Properties")
    @JacksonXmlProperty(localName = "Property")
    @JsonProperty(value = "properties")
    private List<NameValue> properties;

    public void addVirtualHosts(final String... virtualHosts) {
        this.virtualHosts.addAll(Arrays.asList(virtualHosts));
    }

}
