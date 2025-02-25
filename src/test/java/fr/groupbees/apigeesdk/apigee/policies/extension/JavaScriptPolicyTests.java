package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.SSLInfo;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JavaScriptPolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/extension/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        JavaScriptPolicy policy = new JavaScriptPolicy();
        policy.setIncludeURL("include");
        policy.setResourceURL("resource");
        policy.setTimeLimit("200");
        policy.setSslInfo(new SSLInfo());

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "JavaScriptPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "JavaScriptPolicyTests.yaml");

        JavaScriptPolicy policy = ymlMapper.readValue(yml, JavaScriptPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getIncludeURL(), is("include"));
        assertThat(policy.getResourceURL(), is("resource"));
        assertThat(policy.getTimeLimit(), is("200"));
        assertThat(policy.getSslInfo(), notNullValue());
    }

}
