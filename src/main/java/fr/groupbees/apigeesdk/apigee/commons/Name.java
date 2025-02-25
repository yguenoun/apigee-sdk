package fr.groupbees.apigeesdk.apigee.commons;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

}
