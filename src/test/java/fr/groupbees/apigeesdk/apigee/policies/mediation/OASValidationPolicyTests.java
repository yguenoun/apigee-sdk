package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.mediation.oasvalidation.AllowUnspecifiedParameters;
import fr.groupbees.apigeesdk.apigee.policies.mediation.oasvalidation.OASValidationPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.oasvalidation.Options;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OASValidationPolicyTests {

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
        OASValidationPolicy policy = new OASValidationPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "OASValidationPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        OASValidationPolicy policy = new OASValidationPolicy();
        policy.setOasResource("swagger");
        policy.setSource("source");
        policy.setOptions(
                new Options(
                        true,
                        new AllowUnspecifiedParameters(false, false, false)
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "OASValidationPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "OASValidationPolicyTests.yaml");

        OASValidationPolicy policy = ymlMapper.readValue(yml, OASValidationPolicy.class);
        assertThat(policy, notNullValue());
        assertThat(policy.getSource(), is("source"));
        assertThat(policy.getOasResource(), is("swagger"));
        assertThat(policy.getOptions(), notNullValue());
        assertThat(policy.getOptions().getValidateMessageBody(), is(true));
        assertThat(policy.getOptions().getAllowUnspecifiedParameters(), notNullValue());
        assertThat(policy.getOptions().getAllowUnspecifiedParameters().getCookie(), is(false));
        assertThat(policy.getOptions().getAllowUnspecifiedParameters().getHeader(), is(false));
        assertThat(policy.getOptions().getAllowUnspecifiedParameters().getQuery(), is(false));
    }

}
