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

public class CopyTests {

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
        Copy copy = new Copy();
        copy.setSource("source");
        copy.setPath(true);
        copy.setPayload(true);
        copy.setReasonPhrase(true);
        copy.setStatusCode(true);
        copy.setVerb(true);
        copy.setVersion(true);
        copy.setHeaders(Arrays.asList(
                new NameValue("header1", "value1"),
                new NameValue("header2", "value2")
        ));
        copy.setFormParams(Arrays.asList(
                new NameValue("formparam1", "value1"),
                new NameValue("formparam2", "value2")
        ));
        copy.setQueryParams(Arrays.asList(
                new NameValue("queryparam1", "value1"),
                new NameValue("queryparam2", "value2")
        ));

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllHeaders_FromEmptyWrapper() throws Exception {
        Copy copy = new Copy();
        copy.setHeaders(new HeadersWrapper());

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-Headers.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllHeaders_FromEmptyList() throws Exception {
        Copy copy = new Copy();
        copy.setHeaders(new ArrayList<>());

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-Headers.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllHeaders_FromAllString() throws Exception {
        Copy copy = new Copy();
        copy.setHeaders("all");

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-Headers.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllFormParams_FromEmptyWrapper() throws Exception {
        Copy copy = new Copy();
        copy.setFormParams(new FormParamsWrapper());

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-FormParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllFormParams_FromEmptyList() throws Exception {
        Copy copy = new Copy();
        copy.setFormParams(new ArrayList<>());

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-FormParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllFormParams_FromAllString() throws Exception {
        Copy copy = new Copy();
        copy.setFormParams("all");

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-FormParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllQueryParams_FromEmptyWrapper() throws Exception {
        Copy copy = new Copy();
        copy.setQueryParams(new QueryParamsWrapper());

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-QueryParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllQueryParams_FromEmptyList() throws Exception {
        Copy copy = new Copy();
        copy.setQueryParams(new ArrayList<>());

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-QueryParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_AllQueryParams_FromAllString() throws Exception {
        Copy copy = new Copy();
        copy.setQueryParams("all");

        String xml = xmlMapper.writeValueAsString(copy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "CopyTests-QueryParams.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "CopyTests.yaml");

        Copy copy = ymlMapper.readValue(yml, Copy.class);
        assertThat(copy, notNullValue());
        assertThat(copy.getPath(), is(true));
        assertThat(copy.getPayload(), is(true));
        assertThat(copy.getVerb(), is(true));
        assertThat(copy.getVersion(), is(true));
        assertThat(copy.getReasonPhrase(), is(true));
        assertThat(copy.getStatusCode(), is(true));
        assertThat(copy.getSource(), is("source"));
        assertThat(copy.getHeaders(), notNullValue());
        assertThat(copy.getHeaders().getHeaders(), hasSize(2));
        assertThat(copy.getHeaders().getHeaders().get(0).getName(), is("header1"));
        assertThat(copy.getHeaders().getHeaders().get(0).getValue(), is("value1"));
        assertThat(copy.getHeaders().getHeaders().get(1).getName(), is("header2"));
        assertThat(copy.getHeaders().getHeaders().get(1).getValue(), is("value2"));
        assertThat(copy.getFormParams().getFormParams().get(0).getName(), is("formparam1"));
        assertThat(copy.getFormParams().getFormParams().get(0).getValue(), is("value1"));
        assertThat(copy.getFormParams().getFormParams().get(1).getName(), is("formparam2"));
        assertThat(copy.getFormParams().getFormParams().get(1).getValue(), is("value2"));
        MatcherAssert.assertThat(copy.getQueryParams().getQueryParams().get(0).getName(), is("queryparam1"));
        MatcherAssert.assertThat(copy.getQueryParams().getQueryParams().get(0).getValue(), is("value1"));
        MatcherAssert.assertThat(copy.getQueryParams().getQueryParams().get(1).getName(), is("queryparam2"));
        MatcherAssert.assertThat(copy.getQueryParams().getQueryParams().get(1).getValue(), is("value2"));
    }

    @Test
    public void YAML_Parse_AllHeaders() throws Exception {
        String yml = "headers: all";

        Copy copy = ymlMapper.readValue(yml, Copy.class);
        assertThat(copy, notNullValue());
        assertThat(copy.getHeaders(), notNullValue());
        assertThat(copy.getHeaders().getHeaders(), nullValue());
    }

    @Test
    public void YAML_Parse_AllFormParams() throws Exception {
        String yml = "formParams: all";

        Copy copy = ymlMapper.readValue(yml, Copy.class);
        assertThat(copy, notNullValue());
        assertThat(copy.getFormParams(), notNullValue());
    }

    @Test
    public void YAML_Parse_AllQueryParams() throws Exception {
        String yml = "queryParams: all";

        Copy copy = ymlMapper.readValue(yml, Copy.class);
        assertThat(copy, notNullValue());
        assertThat(copy.getQueryParams(), notNullValue());
    }

}
