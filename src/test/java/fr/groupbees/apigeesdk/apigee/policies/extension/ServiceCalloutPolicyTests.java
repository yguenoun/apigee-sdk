package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.LocalTargetConnection;
import fr.groupbees.apigeesdk.apigee.commons.httptargetconnection.HTTPTargetConnection;
import fr.groupbees.apigeesdk.apigee.policies.extension.servicecallout.Request;
import fr.groupbees.apigeesdk.apigee.policies.extension.servicecallout.ServiceCalloutPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Add;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Copy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Remove;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.Set;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ServiceCalloutPolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/extension/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_DefaultValues() throws Exception {
        ServiceCalloutPolicy policy = new ServiceCalloutPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "ServiceCalloutPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        ServiceCalloutPolicy policy = new ServiceCalloutPolicy();
        policy.setTimeout(1000);
        policy.setResponse("response");
        policy.setHttpTargetConnection(new HTTPTargetConnection());
        policy.setLocalTargetConnection(new LocalTargetConnection());

        Request request = new Request();
        request.setClearPayload(false);
        request.setIgnoreUnresolvedVariables(true);
        request.setVariable("var");
        request.setAdd(new Add());
        request.setSet(new Set());
        request.setCopy(new Copy());
        request.setRemove(new Remove());
        policy.setRequest(request);

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "ServiceCalloutPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "ServiceCalloutPolicyTests.yaml");

        ServiceCalloutPolicy policy = ymlMapper.readValue(yml, ServiceCalloutPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getTimeout(), is(1000));
        assertThat(policy.getResponse(), is("response"));
        assertThat(policy.getHttpTargetConnection(), notNullValue());
        assertThat(policy.getLocalTargetConnection(), notNullValue());
        assertThat(policy.getRequest(), notNullValue());
        assertThat(policy.getRequest().getClearPayload(), is(false));
        assertThat(policy.getRequest().getVariable(), is("var"));
        assertThat(policy.getRequest().getIgnoreUnresolvedVariables(), is(true));
        assertThat(policy.getRequest().getCopy(), notNullValue());
        assertThat(policy.getRequest().getRemove(), notNullValue());
        assertThat(policy.getRequest().getAdd(), notNullValue());
        assertThat(policy.getRequest().getSet(), notNullValue());
    }

}
