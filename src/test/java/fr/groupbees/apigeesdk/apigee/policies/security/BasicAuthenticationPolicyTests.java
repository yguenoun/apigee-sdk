package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.policies.security.basicauth.AssignTo;
import fr.groupbees.apigeesdk.apigee.policies.security.basicauth.BasicAuthOperation;
import fr.groupbees.apigeesdk.apigee.policies.security.basicauth.BasicAuthenticationPolicy;
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

public class BasicAuthenticationPolicyTests {

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
        BasicAuthenticationPolicy policy = new BasicAuthenticationPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "BasicAuthenticationPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        BasicAuthenticationPolicy policy = new BasicAuthenticationPolicy();
        policy.setOperation(BasicAuthOperation.Decode);
        policy.setUser(new Ref("user"));
        policy.setPassword(new Ref("pwd"));
        policy.setAssignTo(new AssignTo(false, "assign"));
        policy.setSource("source");
        policy.setIgnoreUnresolvedVariables(false);

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "BasicAuthenticationPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "BasicAuthenticationPolicyTests.yaml");

        BasicAuthenticationPolicy policy = ymlMapper.readValue(yml, BasicAuthenticationPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getAssignTo(), notNullValue());
        assertThat(policy.getAssignTo().getCreateNew(), is(false));
        assertThat(policy.getAssignTo().getValue(), is("assign"));
        assertThat(policy.getIgnoreUnresolvedVariables(), is(false));
        assertThat(policy.getOperation(), is(BasicAuthOperation.Decode));
        assertThat(policy.getPassword(), notNullValue());
        MatcherAssert.assertThat(policy.getPassword().getRef(), is("pwd"));
        assertThat(policy.getSource(), is("source"));
        assertThat(policy.getUser(), notNullValue());
        MatcherAssert.assertThat(policy.getUser().getRef(), is("user"));
    }

}
