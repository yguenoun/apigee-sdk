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
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FaultRuleTests {

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
        FaultRule faultRule = new FaultRule(
                "faultrule",
                "condition",
                new LinkedHashSet<>(Arrays.asList(
                        new Step("step1-name", "step1-condition"),
                        new Step("step2-name", "step2-condition")
                ))
        );

        String xml = xmlMapper.writeValueAsString(faultRule);

        String expected = FileUtils.readTestResource(configFilePrefixe, "FaultRuleTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "FaultRuleTests.yaml");

        FaultRule faultRule = ymlMapper.readValue(yml, FaultRule.class);

        assertThat(faultRule, notNullValue());
        assertThat(faultRule.getName(), is("faultrule"));
        assertThat(faultRule.getCondition(), is("condition"));
        assertThat(faultRule.getSteps(), notNullValue());
        assertThat(faultRule.getSteps(), notNullValue());
        assertThat(faultRule.getSteps(), hasSize(2));

        Iterator<Step> steps = faultRule.getSteps().iterator();
        Step step = steps.next();

        assertThat(step.getName(), is("step1-name"));
        assertThat(step.getCondition(), is("step1-condition"));
        step = steps.next();
        assertThat(step.getName(), is("step2-name"));
        assertThat(step.getCondition(), is("step2-condition"));
        assertThat(steps.hasNext(), is(false));
    }

}
