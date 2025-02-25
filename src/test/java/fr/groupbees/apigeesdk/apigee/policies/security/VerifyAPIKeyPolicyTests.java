package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class VerifyAPIKeyPolicyTests {

    private static XmlMapper xmpMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/security/";

    @BeforeAll
    public static void setup() {
        xmpMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        VerifyAPIKeyPolicy policy = new VerifyAPIKeyPolicy();
        policy.setApiKey(new Ref("apikey"));

        String xml = xmpMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "VerifyAPIKeyPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "VerifyAPIKeyPolicyTests.yaml");

        VerifyAPIKeyPolicy policy = ymlMapper.readValue(yml, VerifyAPIKeyPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getApiKey(), notNullValue());
        MatcherAssert.assertThat(policy.getApiKey().getRef(), is("request.header.api-key"));
    }

}
