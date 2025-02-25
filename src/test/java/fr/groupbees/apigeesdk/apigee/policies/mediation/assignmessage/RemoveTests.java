package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RemoveTests {

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
        Remove remove = new Remove();
        remove.setPayload(true);
        remove.setHeaders(Arrays.asList(
                new NameValue("header1", "value1"),
                new NameValue("header2", "value2")
        ));
        remove.setFormParams(Arrays.asList(
                new NameValue("formparam1", "value1"),
                new NameValue("formparam2", "value2")
        ));
        remove.setQueryParams(Arrays.asList(
                new NameValue("queryparam1", "value1"),
                new NameValue("queryparam2", "value2")
        ));

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllHeaders_FromEmptyWrapper() throws Exception {
        Remove remove = new Remove();
        remove.setHeaders(new HeadersWrapper());

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-Headers.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllHeaders_FromEmptyList() throws Exception {
        Remove remove = new Remove();
        remove.setHeaders(new ArrayList<>());

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-Headers.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllHeaders_FromAllString() throws Exception {
        Remove remove = new Remove();
        remove.setHeaders("all");

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-Headers.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllFormParams_FromEmptyWrapper() throws Exception {
        Remove remove = new Remove();
        remove.setFormParams(new FormParamsWrapper());

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-FormParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllFormParams_FromEmptyList() throws Exception {
        Remove remove = new Remove();
        remove.setFormParams(new ArrayList<>());

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-FormParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllFormParams_FromAllString() throws Exception {
        Remove remove = new Remove();
        remove.setFormParams("all");

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-FormParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllQueryParams_FromEmptyWrapper() throws Exception {
        Remove remove = new Remove();
        remove.setQueryParams(new QueryParamsWrapper());

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-QueryParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllQueryParams_FromEmptyList() throws Exception {
        Remove remove = new Remove();
        remove.setQueryParams(new ArrayList<>());

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-QueryParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllQueryParams_FromAllString() throws Exception {
        Remove remove = new Remove();
        remove.setQueryParams("all");

        String xml = xmlMapper.writeValueAsString(remove);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RemoveTests-QueryParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {

        String yml = FileUtils.readTestResource(configFilePrefixe, "RemoveTests.yaml");

        Remove remove = ymlMapper.readValue(yml, Remove.class);
        assertThat(remove, notNullValue());
        assertThat(remove.getPayload(), is(true));
        assertThat(remove.getHeaders(), notNullValue());
        MatcherAssert.assertThat(remove.getHeaders().getHeaders(), hasSize(2));
        MatcherAssert.assertThat(remove.getHeaders().getHeaders().get(0).getName(), is("header1"));
        MatcherAssert.assertThat(remove.getHeaders().getHeaders().get(0).getValue(), is("value1"));
        MatcherAssert.assertThat(remove.getHeaders().getHeaders().get(1).getName(), is("header2"));
        MatcherAssert.assertThat(remove.getHeaders().getHeaders().get(1).getValue(), is("value2"));
        assertThat(remove.getFormParams().getFormParams().get(0).getName(), is("formparam1"));
        assertThat(remove.getFormParams().getFormParams().get(0).getValue(), is("value1"));
        assertThat(remove.getFormParams().getFormParams().get(1).getName(), is("formparam2"));
        assertThat(remove.getFormParams().getFormParams().get(1).getValue(), is("value2"));
        MatcherAssert.assertThat(remove.getQueryParams().getQueryParams().get(0).getName(), is("queryparam1"));
        MatcherAssert.assertThat(remove.getQueryParams().getQueryParams().get(0).getValue(), is("value1"));
        MatcherAssert.assertThat(remove.getQueryParams().getQueryParams().get(1).getName(), is("queryparam2"));
        MatcherAssert.assertThat(remove.getQueryParams().getQueryParams().get(1).getValue(), is("value2"));
    }

    @Test
    public void YAML_Parse_AllHeaders() throws Exception {
        String yml = "headers: all";

        Remove remove = ymlMapper.readValue(yml, Remove.class);
        assertThat(remove, notNullValue());
        assertThat(remove.getHeaders(), notNullValue());
        assertThat(remove.getHeaders().getHeaders(), nullValue());
    }

    @Test
    public void YAML_Parse_AllFormParams() throws Exception {
        String yml = "formParams: all";

        Remove remove = ymlMapper.readValue(yml, Remove.class);
        assertThat(remove, notNullValue());
        assertThat(remove.getFormParams(), notNullValue());
    }

    @Test
    public void YAML_Parse_AllQueryParams() throws Exception {
        String yml = "queryParams: all";

        Remove remove = ymlMapper.readValue(yml, Remove.class);
        assertThat(remove, notNullValue());
        assertThat(remove.getQueryParams(), notNullValue());
    }

}
