package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.NamedValueRef;
import fr.groupbees.apigeesdk.apigee.flows.FaultRule;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class FlowCalloutPolicyTests {

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
        FlowCalloutPolicy policy = new FlowCalloutPolicy();
        policy.setSharedFlowBundle("shared-flow");
        LinkedHashSet<FaultRule> faultRules = new LinkedHashSet<>();
        policy.setFaultRules(faultRules);
        policy.setParameters(Arrays.asList(
                new NamedValueRef<>("param1-name", "param1-val", "param1-ref"),
                new NamedValueRef<>("param2-name", null, "param2-ref"),
                new NamedValueRef<>("param3-name", "param3-val", null)
        ));

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "FlowCalloutPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "FlowCalloutPolicyTests.yaml");

        FlowCalloutPolicy policy = ymlMapper.readValue(yml, FlowCalloutPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getFaultRules(), notNullValue());
        assertThat(policy.getFaultRules().size(), is(0));
        MatcherAssert.assertThat(policy.getParameters().get(0).getName(), is("param1-name"));
        MatcherAssert.assertThat(policy.getParameters().get(0).getRef(), is("param1-ref"));
        MatcherAssert.assertThat(policy.getParameters().get(0).getValue(), is("param1-value"));
        MatcherAssert.assertThat(policy.getParameters().get(1).getName(), is("param2-name"));
        MatcherAssert.assertThat(policy.getParameters().get(1).getRef(), is("param2-ref"));
        MatcherAssert.assertThat(policy.getParameters().get(1).getValue(), is("param2-value"));
        assertThat(policy.getSharedFlowBundle(), is("shared-flow"));

    }

}
