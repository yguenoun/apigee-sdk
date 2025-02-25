package fr.groupbees.apigeesdk.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class JsonMapperFactory {

  private static JsonMapper mapper;

  private static JsonMapper createJsonMapper() {
    mapper = new JsonMapper(new JsonFactory());
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    return mapper;
  }

  public static JsonMapper getInstance() {
    if (mapper == null) {
      mapper = createJsonMapper();
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    return mapper;
  }
}
