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
public class HeadersWrapper {

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Header")
  private List<NameValue> headers;

  public HeadersWrapper(final List list) {
    if (list != null) {
      if (list.isEmpty()) {
        this.headers = new ArrayList<>();
      } else {
        Object item = list.get(0);
        if (item instanceof NameValue) {
          this.headers = (List<NameValue>) list;
        } else if (item instanceof LinkedHashMap) {
          List<LinkedHashMap<String, String>> linkedList = (List<LinkedHashMap<String, String>>) list;
          this.headers = new ArrayList<>();
          for (LinkedHashMap<String, String> map : linkedList) {
            this.headers.add(new NameValue(map.get("name"), map.get("value")));
          }
        }
      }
    }
  }

  public static HeadersWrapper setter(final Object object) {
    if (object == null) {
      return null;
    }
    if (object instanceof String && "all".equalsIgnoreCase((String) object)) {
      return new HeadersWrapper();
    }
    if (object instanceof HeadersWrapper) {
      return  (HeadersWrapper) object;
    }
    if (object instanceof List) {
      return new HeadersWrapper((List) object);
    }
    if (object instanceof LinkedHashMap) {
      return new HeadersWrapper(List.of((LinkedHashMap<String,String>) object));
    }
    throw new IllegalArgumentException("headers must be a string 'all', a HeadersWrapper, a List<NameValue> or a List<LinkedHashMap>");
  }

}
