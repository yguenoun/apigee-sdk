package fr.groupbees.apigeesdk.apigee.commons.httptargetconnection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TCPMonitor {

    @JacksonXmlProperty(localName = "ConnectTimeoutInSec")
    @JsonProperty(value = "connectTimeoutInSec")
    private Integer connectTimeoutInSec;

    @JacksonXmlProperty(localName = "Port")
    @JsonProperty(value = "port")
    private Integer port;

}
