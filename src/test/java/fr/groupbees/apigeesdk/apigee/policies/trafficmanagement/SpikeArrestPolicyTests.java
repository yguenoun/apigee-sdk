package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
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

public class SpikeArrestPolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/trafficmanagement/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_DefaultValues() throws Exception {
        SpikeArrestPolicy policy = new SpikeArrestPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "SpikeArrestPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        SpikeArrestPolicy policy = new SpikeArrestPolicy();
        policy.setIdentifier(new Ref("ref"));
        policy.setMessageWeight(new Ref("ref"));
        policy.setRate(new ValueRef("value", "ref"));
        policy.setUseEffectiveCount(true);

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "SpikeArrestPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "SpikeArrestPolicyTests.yaml");

        SpikeArrestPolicy policy = ymlMapper.readValue(yml, SpikeArrestPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getIdentifier(), notNullValue());
        MatcherAssert.assertThat(policy.getIdentifier().getRef(), is("ref"));
        assertThat(policy.getMessageWeight(), notNullValue());
        MatcherAssert.assertThat(policy.getMessageWeight().getRef(), is("ref"));
        assertThat(policy.getRate(), notNullValue());
        MatcherAssert.assertThat(policy.getRate().getRef(), is("ref"));
        MatcherAssert.assertThat(policy.getRate().getValue(), is("value"));
        assertThat(policy.getUseEffectiveCount(), is(true));
    }

}
