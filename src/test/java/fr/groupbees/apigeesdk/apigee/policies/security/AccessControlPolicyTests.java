package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.security.accesscontrol.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccessControlPolicyTests {

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
        AccessControlPolicy policy = new AccessControlPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "AccessControlPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        AccessControlPolicy policy = new AccessControlPolicy();
        policy.setIgnoreTrueClientIPHeader(false);
        policy.setValidateBasedOn("X_FORWARDED_FOR_FIRST_IP");
        policy.setIpRules(
                new IPRules(
                        IPRuleAction.ALLOW,
                        Arrays.asList(
                                new MatchRule(
                                        IPRuleAction.ALLOW,
                                        Arrays.asList(
                                                new SourceAddress(
                                                        "rule1-mask1",
                                                        "rule1-address1"
                                                ),
                                                new SourceAddress(
                                                        "rule1-mask2",
                                                        "rule1-address2"
                                                )
                                        )
                                ),
                                new MatchRule(
                                        IPRuleAction.DENY,
                                        Arrays.asList(
                                                new SourceAddress(
                                                        "rule2-mask1",
                                                        "rule2-address1"
                                                )
                                        )
                                )
                        )
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "AccessControlPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "AccessControlPolicyTests.yaml");

        AccessControlPolicy policy = ymlMapper.readValue(yml, AccessControlPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getIpRules(), notNullValue());
        assertThat(policy.getIpRules().getNoRuleMatchAction(), is(IPRuleAction.ALLOW));
        assertThat(policy.getIgnoreTrueClientIPHeader(), is(false));
        assertThat(policy.getValidateBasedOn(), is("X_FORWARDED_FOR_FIRST_IP"));

        List<MatchRule> matchRules = policy.getIpRules().getMatchRules();
        assertThat(matchRules, notNullValue());
        assertThat(matchRules, hasSize(2));
        assertThat(matchRules.get(0).getAction(), is(IPRuleAction.ALLOW));
        assertThat(matchRules.get(0).getSourceAddresses(), notNullValue());
        assertThat(matchRules.get(0).getSourceAddresses(), hasSize(2));
        assertThat(matchRules.get(0).getSourceAddresses().get(0).getMask(), is("rule1-mask1"));
        assertThat(matchRules.get(0).getSourceAddresses().get(0).getValue(), is("rule1-address1"));
        assertThat(matchRules.get(0).getSourceAddresses().get(1).getMask(), is("rule1-mask2"));
        assertThat(matchRules.get(0).getSourceAddresses().get(1).getValue(), is("rule1-address2"));
        assertThat(matchRules.get(1).getAction(), is(IPRuleAction.DENY));
        assertThat(matchRules.get(1).getSourceAddresses(), notNullValue());
        assertThat(matchRules.get(1).getSourceAddresses(), hasSize(1));
        assertThat(matchRules.get(1).getSourceAddresses().get(0).getMask(), is("rule2-mask1"));
        assertThat(matchRules.get(1).getSourceAddresses().get(0).getValue(), is("rule2-address1"));
    }

}
