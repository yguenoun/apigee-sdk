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
public class QueryParamsWrapper {

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "QueryParam")
  private List<NameValue> queryParams;

  public QueryParamsWrapper(final List list) {
    if (list != null) {
      if (list.isEmpty()) {
        this.queryParams = new ArrayList<>();
      } else {
        Object item = list.get(0);
        if (item instanceof NameValue) {
          this.queryParams = (List<NameValue>) list;
        } else if (item instanceof LinkedHashMap) {
          List<LinkedHashMap<String, String>> linkedList = (List<LinkedHashMap<String, String>>) list;
          this.queryParams = new ArrayList<>();
          for (LinkedHashMap<String, String> map : linkedList) {
            this.queryParams.add(new NameValue(map.get("name"), map.get("value")));
          }
        }
      }
    }
  }

  public static QueryParamsWrapper setter(final Object object) {
    if (object == null) {
      return null;
    }
    if (object instanceof String && "all".equalsIgnoreCase((String) object)) {
      return new QueryParamsWrapper();
    }
    if (object instanceof QueryParamsWrapper) {
      return  (QueryParamsWrapper) object;
    }
    if (object instanceof List) {
      return new QueryParamsWrapper((List) object);
    }
    throw new IllegalArgumentException("queryParams must be a string 'all', a QueryParamsWrapper, a List<NameValue> or a List<LinkedHashMap>");
  }

}
