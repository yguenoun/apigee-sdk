package fr.groupbees.apigeesdk.apigee.policies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import lombok.Data;

import java.util.List;

@Data
public abstract class Policy {

  @JacksonXmlProperty(isAttribute = true)
  @JsonProperty(value = "async")
  protected Boolean async = false;

  @JacksonXmlProperty(isAttribute = true)
  @JsonProperty(value = "continueOnError")
  protected Boolean continueOnError = false;

  @JacksonXmlProperty(isAttribute = true)
  @JsonProperty(value = "enabled")
  protected Boolean enabled = true;

  @JacksonXmlProperty(isAttribute = true)
  @JsonProperty(value = "name")
  protected String name;

  @JacksonXmlProperty(localName = "DisplayName")
  @JsonProperty(value = "displayName")
  protected String displayName;

  @JacksonXmlElementWrapper(localName = "Properties")
  @JacksonXmlProperty(localName = "Property")
  @JsonProperty(value = "properties")
  private List<NameValue> properties;

  public void setName(final String name) {
    this.name = name;
    if (displayName == null) {
      displayName = name;
    }
  }

  public String getDisplayName() {
    if (displayName == null) {
      return name;
    }
    return displayName;
  }

  protected void defaultHeader() {
    this.setContinueOnError(false);
    this.setEnabled(true);
    this.setAsync(false);
  }

  public abstract void defaultValues();

}
