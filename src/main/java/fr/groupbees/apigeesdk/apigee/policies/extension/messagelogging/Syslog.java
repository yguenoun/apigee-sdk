package fr.groupbees.apigeesdk.apigee.policies.extension.messagelogging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.SSLInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Syslog {

    @JacksonXmlProperty(localName = "Message")
    @JsonProperty(value = "message")
    private String message;

    @JacksonXmlProperty(localName = "Host")
    @JsonProperty(value = "host")
    private String host; // default: localhost

    @JacksonXmlProperty(localName = "Port")
    @JsonProperty(value = "port")
    private Integer port; // default: 514

    @JacksonXmlProperty(localName = "Protocol")
    @JsonProperty(value = "protocol")
    private SyslogProtocol protocol; // default: UDP

    @JacksonXmlProperty(localName = "FormatMessage")
    @JsonProperty(value = "formatMessage")
    private Boolean formatMessage; // default: false

    @JacksonXmlProperty(localName = "PayloadOnly")
    @JsonProperty(value = "payloadOnly")
    private Boolean payloadOnly; // default: false

    @JacksonXmlProperty(localName = "SSLInfo")
    @JsonProperty(value = "sslInfo")
    private SSLInfo sslInfo;

}
