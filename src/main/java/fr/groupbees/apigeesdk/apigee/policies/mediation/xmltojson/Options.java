package fr.groupbees.apigeesdk.apigee.policies.mediation.xmltojson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class Options {

  @JacksonXmlProperty(localName = "DefaultNamespaceNodeName")
  @JsonProperty(value = "defaultNamespaceNodeName")
  private String defaultNamespaceNodeName;

  @JacksonXmlProperty(localName = "NamespaceSeparator")
  @JsonProperty(value = "namespaceSeparator")
  private String namespaceSeparator;

  @JacksonXmlProperty(localName = "AttributeBlockName")
  @JsonProperty(value = "attributeBlockName")
  private String attributeBlockName;

  @JacksonXmlProperty(localName = "AttributePrefix")
  @JsonProperty(value = "attributePrefix")
  private String attributePrefix;

  @JacksonXmlProperty(localName = "NullValue")
  @JsonProperty(value = "nullValue")
  private Boolean nullValue;

  @JacksonXmlProperty(localName = "TextNodeName")
  @JsonProperty(value = "textNodeName")
  private String textNodeName;

  @JacksonXmlProperty(localName = "RecognizeNumber")
  @JsonProperty(value = "recognizeNumber")
  private Boolean recognizeNumber;

  @JacksonXmlProperty(localName = "RecognizeBoolean")
  @JsonProperty(value = "recognizeBoolean")
  private Boolean recognizeBoolean;

  @JacksonXmlProperty(localName = "RecognizeNull")
  @JsonProperty(value = "recognizeNull")
  private Boolean recognizeNull;

  @JacksonXmlProperty(localName = "NamespaceBlockName")
  @JsonProperty(value = "namespaceBlockName")
  private String namespaceBlockName;

  @JacksonXmlProperty(localName = "TextAlwaysAsProperty")
  @JsonProperty(value = "textAlwaysAsProperty")
  private Boolean textAlwaysAsProperty;

  @JacksonXmlProperty(localName = "OutputPrefix")
  @JsonProperty(value = "outputPrefix")
  private String outputPrefix;

  @JacksonXmlProperty(localName = "OutputSuffix")
  @JsonProperty(value = "outputSuffix")
  private String outputSuffix;

  @JacksonXmlProperty(localName = "StripLevels")
  @JsonProperty(value = "stripLevels")
  private Integer stripLevels;

  @JacksonXmlElementWrapper(localName = "TreatAsArray")
  @JacksonXmlProperty(localName = "Path")
  @JsonProperty(value = "treatAsArray")
  private List<Path> treatAsArray;

}
