package fr.groupbees.apigeesdk.apigee.flows;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.LocalTargetConnection;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import fr.groupbees.apigeesdk.apigee.commons.httptargetconnection.HTTPTargetConnection;
import fr.groupbees.apigeesdk.apigee.flows.targetendpoint.ScriptTarget;
import fr.groupbees.apigeesdk.apigee.flows.targetendpoint.TargetEndpoint;
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
import static org.hamcrest.Matchers.*;

public class TargetEndpointTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/flows/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        TargetEndpoint target = new TargetEndpoint();
        target.setName("target");
        target.setDescription("description");
        target.setDefaultFaultRule(new DefaultFaultRule());
        LinkedHashSet<FaultRule> faultRules = new LinkedHashSet<>();
        target.setFaultRules(faultRules);
        target.setPreFlow(new Flow("pre-flow"));
        target.setPostFlow(new Flow("post-flow"));
        target.setFlows(new LinkedHashSet<>());
        target.setHttpTargetConnection(new HTTPTargetConnection());
        target.setLocalTargetConnection(new LocalTargetConnection());
        target.setScriptTarget(
                new ScriptTarget(
                        "resource",
                        Arrays.asList(new NameValue("prop1", "val1"), new NameValue("prop2", "val2")),
                        Arrays.asList("arg1", "arg2", "arg3")
                )
        );

        String xml = xmlMapper.writeValueAsString(target);

        String expected = FileUtils.readTestResource(configFilePrefixe, "TargetEndpointTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "TargetEndpointTests.yaml");

        TargetEndpoint targetEndpoint = ymlMapper.readValue(yml, TargetEndpoint.class);

        assertThat(targetEndpoint, notNullValue());
        assertThat(targetEndpoint.getName(), is("target"));
        assertThat(targetEndpoint.getPreFlow(), notNullValue());
        MatcherAssert.assertThat(targetEndpoint.getPreFlow().getName(), is("pre-flow"));
        MatcherAssert.assertThat(targetEndpoint.getPreFlow().getRequest(), notNullValue());
        MatcherAssert.assertThat(targetEndpoint.getPreFlow().getResponse(), notNullValue());
        MatcherAssert.assertThat(targetEndpoint.getPreFlow().getResponse(), notNullValue());
        assertThat(targetEndpoint.getPostFlow(), notNullValue());
        MatcherAssert.assertThat(targetEndpoint.getPostFlow().getName(), is("post-flow"));
        MatcherAssert.assertThat(targetEndpoint.getPostFlow().getRequest(), notNullValue());
        MatcherAssert.assertThat(targetEndpoint.getPostFlow().getResponse(), notNullValue());
        MatcherAssert.assertThat(targetEndpoint.getPostFlow().getResponse(), notNullValue());
        assertThat(targetEndpoint.getDefaultFaultRule(), notNullValue());
        assertThat(targetEndpoint.getFaultRules(), notNullValue());
        assertThat(targetEndpoint.getFlows(), notNullValue());
        assertThat(targetEndpoint.getLocalTargetConnection(), notNullValue());
        assertThat(targetEndpoint.getHttpTargetConnection(), notNullValue());

        ScriptTarget scriptTarget = targetEndpoint.getScriptTarget();
        assertThat(scriptTarget, notNullValue());
        assertThat(scriptTarget.getArguments(), notNullValue());
        assertThat(scriptTarget.getArguments(), hasSize(3));
        assertThat(scriptTarget.getArguments().get(0), is("arg1"));
        assertThat(scriptTarget.getArguments().get(1), is("arg2"));
        assertThat(scriptTarget.getArguments().get(2), is("arg3"));
        assertThat(scriptTarget.getEnvironmentVariables(), notNullValue());
        MatcherAssert.assertThat(scriptTarget.getEnvironmentVariables(), hasSize(2));
        MatcherAssert.assertThat(scriptTarget.getEnvironmentVariables().get(0).getName(), is("prop1"));
        MatcherAssert.assertThat(scriptTarget.getEnvironmentVariables().get(0).getValue(), is("val1"));
        MatcherAssert.assertThat(scriptTarget.getEnvironmentVariables().get(1).getName(), is("prop2"));
        MatcherAssert.assertThat(scriptTarget.getEnvironmentVariables().get(1).getValue(), is("val2"));
        assertThat(scriptTarget.getResourceURL(), is("resource"));
    }

}
