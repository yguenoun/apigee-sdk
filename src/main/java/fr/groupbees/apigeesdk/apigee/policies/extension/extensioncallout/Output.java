package fr.groupbees.apigeesdk.apigee.policies.extension.extensioncallout;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Output {

    @JacksonXmlProperty(isAttribute = true, localName = "parsed")
    private Boolean parsed;

    @JacksonXmlText
    private String value;

}
