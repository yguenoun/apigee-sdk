package fr.groupbees.apigeesdk.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlMapperFactory {

  private static YAMLMapper mapper;

  private static YAMLMapper createYamlObjectMapper() {
    mapper = new YAMLMapper(new YAMLFactory());
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

    return mapper;
  }


  public static YAMLMapper getInstance() {
    if (mapper == null) {
      mapper = createYamlObjectMapper();
    }
    return mapper;
  }

}
