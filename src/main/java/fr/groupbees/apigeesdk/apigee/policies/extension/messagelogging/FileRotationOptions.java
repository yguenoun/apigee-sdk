package fr.groupbees.apigeesdk.apigee.policies.extension.messagelogging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRotationOptions {

    @JacksonXmlProperty(isAttribute = true, localName = "rotateFileOnStartup")
    @JsonProperty(value = "rotateFileOnStartup")
    private Boolean rotateFileOnStartup;

    @JacksonXmlProperty(localName = "FileRotationType")
    @JsonProperty(value = "fileRotationType")
    private FileRotationType fileRotationType;

    @JacksonXmlProperty(localName = "MaxFileSizeInMB")
    @JsonProperty(value = "maxFileSizeInMB")
    private Integer maxFileSizeInMB;

    @JacksonXmlProperty(localName = "RotationFrequency")
    @JsonProperty(value = "rotationFrequency")
    private RotationFrequency rotationFrequency;

    @JacksonXmlProperty(localName = "MaxFilesToRetain")
    @JsonProperty(value = "maxFilesToRetain")
    private Integer maxFilesToRetain;

}
