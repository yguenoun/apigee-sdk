package fr.groupbees.apigeesdk.apigee.policies.security.regexpprotection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "RegularExpressionProtection")
public class RegularExpressionProtectionPolicy extends Policy {

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty(value = "source")
    private String source;

    @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
    @JsonProperty(value = "ignoreUnresolvedVariables")
    private Boolean ignoreUnresolvedVariables;

    @JacksonXmlElementWrapper(localName = "URIPath")
    @JacksonXmlProperty(localName = "Pattern")
    @JsonProperty(value = "uriPath")
    private List<String> uriPaths;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "QueryParam")
    @JsonProperty(value = "queryParam")
    private List<NamedPatterns> queryParams;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Header")
    @JsonProperty(value = "header")
    private List<NamedPatterns> headers;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "FormParam")
    @JsonProperty(value = "formParam")
    private List<NamedPatterns> formParams;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Variable")
    @JsonProperty(value = "variable")
    private List<NamedPatterns> variables;

    @JacksonXmlProperty(localName = "XMLPayload")
    @JsonProperty(value = "xmlPayload")
    private XMLPayload xmlPayload;

    @JacksonXmlElementWrapper(localName = "JSONPayload")
    @JacksonXmlProperty(localName = "JSONPath")
    @JsonProperty(value = "jsonPayload")
    private List<JSONPath> jsonPayload;

    @Override
    public void defaultValues() {
        this.defaultHeader();
        this.ignoreUnresolvedVariables = false;
    }

}
