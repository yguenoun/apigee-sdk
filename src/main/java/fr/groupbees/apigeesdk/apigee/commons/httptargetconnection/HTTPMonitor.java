package fr.groupbees.apigeesdk.apigee.commons.httptargetconnection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HTTPMonitor {

    @JacksonXmlProperty(localName = "Request")
    @JsonProperty(value = "request")
    private HTTPMonitorRequest request;

    @JacksonXmlProperty(localName = "SuccessResponse")
    @JsonProperty(value = "successResponse")
    private HTTPMonitorSuccessResponse successResponse;

}
