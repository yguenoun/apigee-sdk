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
public class HTTPMonitorSuccessResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ResponseCode")
    @JsonProperty(value = "responseCode")
    private List<Integer> responseCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Header")
    @JsonProperty(value = "header")
    private List<NameValue> headers;

}
