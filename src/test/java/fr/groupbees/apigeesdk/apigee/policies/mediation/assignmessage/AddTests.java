package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/mediation/assignmessage/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        xmlMapper.disable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @AfterAll
    public static void clear() {
        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        Add add = new Add();
        add.setHeaders(Arrays.asList(
                new NameValue("header1", "value1"),
                new NameValue("header2", "value2")
        ));
        add.setFormParams(Arrays.asList(
                new NameValue("formparam1", "value1"),
                new NameValue("formparam2", "value2")
        ));
        add.setQueryParams(Arrays.asList(
                new NameValue("queryparam1", "value1"),
                new NameValue("queryparam2", "value2")
        ));

        String xml = xmlMapper.writeValueAsString(add);

        String expected = FileUtils.readTestResource(configFilePrefixe, "AddTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "AddTests.yaml");

        Add add = ymlMapper.readValue(yml, Add.class);
        assertThat(add, notNullValue());
        assertThat(add.getHeaders(), notNullValue());
        assertThat(add.getHeaders(), hasSize(2));
        assertThat(add.getHeaders().get(0).getName(), is("header1"));
        assertThat(add.getHeaders().get(0).getValue(), is("value1"));
        assertThat(add.getHeaders().get(1).getName(), is("header2"));
        assertThat(add.getHeaders().get(1).getValue(), is("value2"));
        assertThat(add.getFormParams(), notNullValue());
        assertThat(add.getFormParams(), hasSize(2));
        assertThat(add.getFormParams().get(0).getName(), is("formparam1"));
        assertThat(add.getFormParams().get(0).getValue(), is("value1"));
        assertThat(add.getFormParams().get(1).getName(), is("formparam2"));
        assertThat(add.getFormParams().get(1).getValue(), is("value2"));
        assertThat(add.getQueryParams(), notNullValue());
        assertThat(add.getQueryParams(), hasSize(2));
        assertThat(add.getQueryParams().get(0).getName(), is("queryparam1"));
        assertThat(add.getQueryParams().get(0).getValue(), is("value1"));
        assertThat(add.getQueryParams().get(1).getName(), is("queryparam2"));
        assertThat(add.getQueryParams().get(1).getValue(), is("value2"));
    }

}
