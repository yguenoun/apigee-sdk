package fr.groupbees.apigeesdk.apigee.policies.mediation.jsontoxml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Options {

  @JacksonXmlProperty(localName = "OmitXmlDeclaration")
  @JsonProperty(value = "omitXmlDeclaration")
  private String omitXmlDeclaration;

  @JacksonXmlProperty(localName = "DefaultNamespaceNodeName")
  @JsonProperty(value = "defaultNamespaceNodeName")
  private String defaultNamespaceNodeName;

  @JacksonXmlProperty(localName = "NamespaceSeparator")
  @JsonProperty(value = "namespaceSeparator")
  private String namespaceSeparator;

  @JacksonXmlProperty(localName = "NamespaceBlockName")
  @JsonProperty(value = "namespaceBlockName")
  private String NamespaceBlockName;

  @JacksonXmlProperty(localName = "AttributeBlockName")
  @JsonProperty(value = "attributeBlockName")
  private String attributeBlockName;

  @JacksonXmlProperty(localName = "AttributePrefix")
  @JsonProperty(value = "attributePrefix")
  private String attributePrefix;

  @JacksonXmlProperty(localName = "ObjectRootElementName")
  @JsonProperty(value = "objectRootElementName")
  private String objectRootElementName;

  @JacksonXmlProperty(localName = "ArrayRootElementName")
  @JsonProperty(value = "arrayRootElementName")
  private String arrayRootElementName;

  @JacksonXmlProperty(localName = "ArrayItemElementName")
  @JsonProperty(value = "arrayItemElementName")
  private String arrayItemElementName;

  @JacksonXmlProperty(localName = "Indent")
  @JsonProperty(value = "indent")
  private String indent;

  @JacksonXmlProperty(localName = "TextNodeName")
  @JsonProperty(value = "textNodeName")
  private String textNodeName;

  @JacksonXmlProperty(localName = "NullValue")
  @JsonProperty(value = "nullValue")
  private String nullValue;

  @JacksonXmlProperty(localName = "InvalidCharsReplacement")
  @JsonProperty(value = "invalidCharsReplacement")
  private String invalidCharsReplacement;

}
