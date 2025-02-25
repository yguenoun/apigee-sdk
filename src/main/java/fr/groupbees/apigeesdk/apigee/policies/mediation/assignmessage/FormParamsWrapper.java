package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class FormParamsWrapper {

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "FormParam")
  private List<NameValue> formParams;

  public FormParamsWrapper(final List list) {
    if (list != null) {
      if (list.isEmpty()) {
        this.formParams = new ArrayList<>();
      } else {
        Object item = list.get(0);
        if (item instanceof NameValue) {
          this.formParams = (List<NameValue>) list;
        } else if (item instanceof LinkedHashMap) {
          List<LinkedHashMap<String, String>> linkedList = (List<LinkedHashMap<String, String>>) list;
          this.formParams = new ArrayList<>();
          for (LinkedHashMap<String, String> map : linkedList) {
            this.formParams.add(new NameValue(map.get("name"), map.get("value")));
          }
        }
      }
    }
  }

  public static FormParamsWrapper setter(final Object object) {
    if (object == null) {
      return null;
    }
    if (object instanceof String && "all".equalsIgnoreCase((String) object)) {
      return new FormParamsWrapper();
    }
    if (object instanceof FormParamsWrapper) {
      return  (FormParamsWrapper) object;
    }
    if (object instanceof List) {
      return new FormParamsWrapper((List) object);
    }
    throw new IllegalArgumentException("formParams must be a string 'all', a FormParamsWrapper, a List<NameValue> or a List<LinkedHashMap>");
  }

}
