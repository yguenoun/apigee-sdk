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

public class SetTests {

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
        Set set = new Set();
        set.setPath("path");
        set.setPayload(new Payload(
                "content-type",
                "prefix",
                "suffix",
                "payload"
        ));
        set.setReasonPhrase("reason-phrase");
        set.setStatusCode("status-code");
        set.setVerb("verb");
        set.setVersion("version");
        set.setHeaders(Arrays.asList(
                new NameValue("header1", "value1"),
                new NameValue("header2", "value2")
        ));
        set.setFormParams(Arrays.asList(
                new NameValue("formParam1", "value1"),
                new NameValue("formParam2", "value2")
        ));
        set.setQueryParams(Arrays.asList(
                new NameValue("queryParam1", "value1"),
                new NameValue("queryParam2", "value2")
        ));

        String xml = xmlMapper.writeValueAsString(set);

        String expected = FileUtils.readTestResource(configFilePrefixe, "SetTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "SetTests.yaml");

        Set set = ymlMapper.readValue(yml, Set.class);
        assertThat(set, notNullValue());
        assertThat(set.getPath(), is("path"));
        assertThat(set.getPayload(), notNullValue());
        assertThat(set.getPayload().getContentType(), is("content-type"));
        assertThat(set.getPayload().getVariablePrefix(), is("prefix"));
        assertThat(set.getPayload().getVariableSuffix(), is("suffix"));
        assertThat(set.getPayload().getValue(), is("payload"));
        assertThat(set.getReasonPhrase(), is("reason-phrase"));
        assertThat(set.getStatusCode(), is("status-code"));
        assertThat(set.getVerb(), is("verb"));
        assertThat(set.getVersion(), is("version"));
        assertThat(set.getHeaders(), notNullValue());
        assertThat(set.getHeaders(), hasSize(2));
        assertThat(set.getHeaders().get(0).getName(), is("header1"));
        assertThat(set.getHeaders().get(0).getValue(), is("value1"));
        assertThat(set.getHeaders().get(1).getName(), is("header2"));
        assertThat(set.getHeaders().get(1).getValue(), is("value2"));
        assertThat(set.getFormParams(), notNullValue());
        assertThat(set.getFormParams(), hasSize(2));
        assertThat(set.getFormParams().get(0).getName(), is("formparam1"));
        assertThat(set.getFormParams().get(0).getValue(), is("value1"));
        assertThat(set.getFormParams().get(1).getName(), is("formparam2"));
        assertThat(set.getFormParams().get(1).getValue(), is("value2"));
        assertThat(set.getQueryParams(), notNullValue());
        assertThat(set.getQueryParams(), hasSize(2));
        assertThat(set.getQueryParams().get(0).getName(), is("queryparam1"));
        assertThat(set.getQueryParams().get(0).getValue(), is("value1"));
        assertThat(set.getQueryParams().get(1).getName(), is("queryparam2"));
        assertThat(set.getQueryParams().get(1).getValue(), is("value2"));
    }

}
