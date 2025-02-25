package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JSONThreatProtectionPolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/security/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_DefaultValues() throws Exception {
        JSONThreatProtectionPolicy policy = new JSONThreatProtectionPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "JSONThreatProtectionPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        JSONThreatProtectionPolicy policy = new JSONThreatProtectionPolicy();
        policy.setArrayElementCount(20);
        policy.setContainerDepth(10);
        policy.setObjectEntryCount(15);
        policy.setObjectEntryNameLength(50);
        policy.setStringValueLength(500);
        policy.setSource("source");

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "JSONThreatProtectionPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "JSONThreatProtectionPolicyTests.yaml");

        JSONThreatProtectionPolicy policy = ymlMapper.readValue(yml, JSONThreatProtectionPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getArrayElementCount(), is(20));
        assertThat(policy.getContainerDepth(), is(10));
        assertThat(policy.getObjectEntryCount(), is(15));
        assertThat(policy.getObjectEntryNameLength(), is(50));
        assertThat(policy.getSource(), is("source"));
        assertThat(policy.getStringValueLength(), is(500));
    }

}
