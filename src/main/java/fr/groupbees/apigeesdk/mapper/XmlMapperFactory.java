package fr.groupbees.apigeesdk.mapper;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.IOException;

public class XmlMapperFactory {

  private static XmlMapper mapper;

  private static XmlMapper createXmlMapper() {
    WstxInputFactory inputFactory = new WstxInputFactory();
    WstxOutputFactory outputFactory = new WstxOutputFactory();
    outputFactory.getConfig().doUseDoubleQuotesInXmlDecl(true);

    XmlFactory xf = new XmlFactory(inputFactory, outputFactory);

    mapper = new XmlMapper(xf);

    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
    mapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

    mapper.registerModule(new SimpleModule()
      .addDeserializer(
        JsonNode.class,
        new JsonNodeDeserializer() {
          @Override
          public JsonNode deserialize(JsonParser p, DeserializationContext ctxt) throws
            IOException {
            String rootName = ((FromXmlParser) p).getStaxReader().getLocalName();
            return ctxt.getNodeFactory()
              .objectNode().set(rootName, super.deserialize(p, ctxt));
          }
        }
      ));

    return mapper;
  }


  public static XmlMapper getInstance() {
    if (mapper == null) {
      mapper = createXmlMapper();
    }
    return mapper;
  }

}
