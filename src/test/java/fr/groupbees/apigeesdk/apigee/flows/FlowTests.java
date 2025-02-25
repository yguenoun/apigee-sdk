package fr.groupbees.apigeesdk.apigee.flows;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FlowTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/flows/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        xmlMapper.disable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @AfterAll
    public static void clear() {
        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        Flow flow = new Flow(
                "flow-name",
                "flow-description",
                "flow-condition",
                new Steps(new LinkedHashSet<>(Arrays.asList(
                        new Step(
                                "request-step1-name",
                                "request-step1-condition"
                        ),
                        new Step(
                                "request-step2-name",
                                "request-step2-condition"
                        )
                ))),
                new Steps(new LinkedHashSet<>(Collections.singletonList(
                        new Step(
                                "response-step-name",
                                "response-step-condition"
                        )
                )))
        );

        String xml = xmlMapper.writeValueAsString(flow);

        String expected = FileUtils.readTestResource(configFilePrefixe, "FlowTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "FlowTests.yaml");

        Flow flow = ymlMapper.readValue(yml, Flow.class);

        assertThat(flow, notNullValue());
        assertThat(flow.getName(), is("flow-name"));
        assertThat(flow.getDescription(), is("flow-description"));
        assertThat(flow.getRequest(), notNullValue());
        assertThat(flow.getRequest().getSteps(), notNullValue());
        assertThat(flow.getRequest().getSteps(), hasSize(2));

        Iterator<Step> steps = flow.getRequest().getSteps().iterator();
        Step step = steps.next();

        assertThat(step.getName(), is("request-step1-name"));
        assertThat(step.getCondition(), is("request-step1-condition"));

        step = steps.next();
        assertThat(step.getName(), is("request-step2-name"));
        assertThat(step.getCondition(), is("request-step2-condition"));
        assertThat(steps.hasNext(), is(false));

        assertThat(flow.getResponse(), notNullValue());
        assertThat(flow.getResponse().getSteps(), notNullValue());
        assertThat(flow.getResponse().getSteps(), hasSize(1));

        steps = flow.getResponse().getSteps().iterator();
        step = steps.next();
        assertThat(step.getName(), is("response-step-name"));
        assertThat(step.getCondition(), is("response-step-condition"));
        assertThat(steps.hasNext(), is(false));
    }

}
