package fr.groupbees.apigeesdk.apigee.commons.httptargetconnection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthMonitor {

    @JacksonXmlProperty(localName = "IsEnabled")
    @JsonProperty(value = "isEnabled")
    private Boolean isEnabled;

    @JacksonXmlProperty(localName = "IntervalInSec")
    @JsonProperty(value = "intervalInSec")
    private Integer intervalInSec;

    @JacksonXmlProperty(localName = "TCPMonitor")
    @JsonProperty(value = "tcpMonitor")
    private TCPMonitor tcpMonitor;

    @JacksonXmlProperty(localName = "HTTPMonitor")
    @JsonProperty(value = "httpMonitor")
    private HTTPMonitor httpMonitor;

}
