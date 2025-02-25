package fr.groupbees.apigeesdk.apigee.policies.extension.messagelogging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "MessageLogging")
public class MessageLoggingPolicy extends Policy {

    @JacksonXmlProperty(localName = "File")
    @JsonProperty(value = "file")
    private File file;

    @JacksonXmlProperty(localName = "Syslog")
    @JsonProperty(value = "syslog")
    private Syslog syslog;

    @JacksonXmlProperty(localName = "BufferMessage")
    private Boolean bufferMessage;

    @JacksonXmlProperty(localName = "logLevel")
    private LogLevel logLevel;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.bufferMessage = false;
        this.logLevel = LogLevel.INFO;
    }

}
