package fr.groupbees.apigeesdk.apigee.policies.mediation.extractvariables;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "ExtractVariables")
public class ExtractVariablesPolicy extends Policy {

  @JacksonXmlProperty(localName = "Source")
  @JsonProperty(value = "source")
  private Source source;

  @JacksonXmlProperty(localName = "VariablePrefix")
  @JsonProperty(value = "variablePrefix")
  private String variablePrefix;

  @JacksonXmlProperty(localName = "IgnoreUnresolvedVariables")
  @JsonProperty(value = "ignoreUnresolvedVariables")
  private Boolean ignoreUnresolvedVariables;

  @JacksonXmlElementWrapper(localName = "URIPath")
  @JacksonXmlProperty(localName = "Pattern")
  @JsonProperty(value = "uriPath")
  private List<Pattern> uriPaths;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "QueryParam")
  @JsonProperty(value = "queryParam")
  private List<NamedPattern> queryParams;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Header")
  @JsonProperty(value = "header")
  private List<NamedPattern> headers;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "FormParam")
  @JsonProperty(value = "formParam")
  private List<NamedPattern> formParams;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Variable")
  @JsonProperty(value = "variable")
  private List<NamedPattern> variables;

  @JacksonXmlProperty(localName = "JSONPayload")
  @JsonProperty(value = "jsonPayload")
  private JSONPayload jsonPayload;

  @JacksonXmlProperty(localName = "XMLPayload")
  @JsonProperty(value = "xmlPayload")
  private XMLPayload xmlPayload;

  @Override
  public void defaultValues() {
    this.defaultHeader();
    this.ignoreUnresolvedVariables = true;
  }

  // must have at least one of them fulfilled <URIPath>, <QueryParam>, <Header>, <FormParam>, <JSONPayload>, or <XMLPayload>
  // must validate Variables types: string, boolean, integer, long, float, double, nodeset

}
