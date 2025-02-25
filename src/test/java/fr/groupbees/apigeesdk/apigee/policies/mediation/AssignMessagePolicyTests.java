package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssignMessagePolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/mediation/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_DefaultValues() throws Exception {
        AssignMessagePolicy policy = new AssignMessagePolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "AssignMessagePolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        AssignMessagePolicy policy = new AssignMessagePolicy();
        policy.setFaultRules(new LinkedHashSet<>());
        policy.setIgnoreUnresolvedVariables(false);
        policy.setAssignTo(new AssignTo(true, "transport", "type", "value"));
        policy.setAdd(new Add());
        policy.setSet(new Set());
        policy.setCopy(new Copy());
        policy.setRemove(new Remove());
        policy.setAssignVariables(Arrays.asList(new AssignVariable(), new AssignVariable()));
        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "AssignMessagePolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "AssignMessagePolicyTests.yaml");

        AssignMessagePolicy policy = ymlMapper.readValue(yml, AssignMessagePolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getAdd(), notNullValue());
        assertThat(policy.getSet(), notNullValue());
        assertThat(policy.getCopy(), notNullValue());
        assertThat(policy.getRemove(), notNullValue());
        assertThat(policy.getFaultRules(), notNullValue());
        assertThat(policy.getFaultRules().size(), is(0));
        assertThat(policy.getIgnoreUnresolvedVariables(), is(false));
        assertThat(policy.getAssignTo(), notNullValue());
        assertThat(policy.getAssignTo().getCreateNew(), is(true));
        assertThat(policy.getAssignTo().getTransport(), is("transport"));
        assertThat(policy.getAssignTo().getType(), is("type"));
        assertThat(policy.getAssignTo().getValue(), is("value"));
        assertThat(policy.getAssignVariables(), notNullValue());
        assertThat(policy.getAssignVariables(), hasSize(2));
    }

}
