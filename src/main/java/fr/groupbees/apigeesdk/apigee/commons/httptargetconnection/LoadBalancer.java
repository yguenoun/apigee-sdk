package fr.groupbees.apigeesdk.apigee.commons.httptargetconnection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadBalancer {

    @JacksonXmlProperty(localName = "Algorithm")
    @JsonProperty(value = "algorithm")
    private String algorithm;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Server")
    @JsonProperty(value = "server")
    private List<Server> servers = new ArrayList<>();

    @JacksonXmlProperty(localName = "MaxFailures")
    @JsonProperty(value = "maxFailures")
    private Integer maxFailures;

    @JacksonXmlProperty(localName = "RetryEnabled")
    @JsonProperty(value = "retryEnabled")
    private Boolean retryEnabled;

    @JacksonXmlElementWrapper(localName = "ServerUnhealthyResponse")
    @JacksonXmlProperty(localName = "ResponseCode")
    @JsonProperty(value = "serverUnhealthyResponses")
    private List<Integer> serverUnhealthyResponses;

    public void addServer(final String server) {
        this.servers.add(new Server(server));
    }

    public void addServer(final Server server) {
        this.servers.add(server);
    }

}
