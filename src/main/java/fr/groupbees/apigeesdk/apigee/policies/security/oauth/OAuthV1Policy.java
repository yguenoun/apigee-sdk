package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

// TODO configure Policy and create tests

@Data
@JacksonXmlRootElement(localName = "OAuthV1")
public class OAuthV1Policy extends Policy {

    @JacksonXmlProperty(localName = "Operation")
    private String operation;

    @Override
    public void defaultValues() {
        this.defaultHeader();
    }

    //    <Operation>VerifyAccessToken</Operation>
    //    <GenerateResponse enabled="true">
    //        <Format>FORM_PARAM</Format>
    //    </GenerateResponse>
    //    <GenerateErrorResponse enabled="true">
    //        <Format>FORM_PARAM</Format>
    //        <Realm/>
    //    </GenerateErrorResponse>

}
