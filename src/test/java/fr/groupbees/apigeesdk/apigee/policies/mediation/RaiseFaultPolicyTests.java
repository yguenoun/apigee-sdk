package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.*;
import fr.groupbees.apigeesdk.apigee.policies.mediation.raisefault.FaultResponse;
import fr.groupbees.apigeesdk.apigee.policies.mediation.raisefault.RaiseFaultPolicy;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RaiseFaultPolicyTests {

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
        RaiseFaultPolicy policy = new RaiseFaultPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RaiseFaultPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        RaiseFaultPolicy policy = new RaiseFaultPolicy();
        policy.setIgnoreUnresolvedVariables(false);
        policy.setShortFaultReason(true);
        policy.setFaultResponse(
                new FaultResponse(
                        Arrays.asList(new AssignVariable(), new AssignVariable()),
                        new Copy(),
                        new Add(),
                        new Remove(),
                        new Set()
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RaiseFaultPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "RaiseFaultPolicyTests.yaml");

        RaiseFaultPolicy policy = ymlMapper.readValue(yml, RaiseFaultPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getFaultResponse(), notNullValue());
        assertThat(policy.getFaultResponse().getAdd(), notNullValue());
        assertThat(policy.getFaultResponse().getCopy(), notNullValue());
        assertThat(policy.getFaultResponse().getSet(), notNullValue());
        assertThat(policy.getFaultResponse().getRemove(), notNullValue());
        assertThat(policy.getFaultResponse().getAssignVariables(), notNullValue());
        MatcherAssert.assertThat(policy.getFaultResponse().getAssignVariables(), hasSize(2));
        assertThat(policy.getIgnoreUnresolvedVariables(), is(false));
        assertThat(policy.getShortFaultReason(), is(true));
    }

}
