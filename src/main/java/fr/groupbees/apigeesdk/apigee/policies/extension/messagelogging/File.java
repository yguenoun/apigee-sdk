package fr.groupbees.apigeesdk.apigee.policies.extension.messagelogging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @JacksonXmlProperty(localName = "Message")
    @JsonProperty(value = "message")
    private String message;

    @JacksonXmlProperty(localName = "FileName")
    @JsonProperty(value = "fileName")
    private String fileName;

    @JacksonXmlProperty(localName = "FileRotationOptions")
    @JsonProperty(value = "fileRotationOptions")
    private FileRotationOptions fileRotationOptions;

}
