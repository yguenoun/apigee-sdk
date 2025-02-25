package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.extension.statisticcollector.Statistic;
import fr.groupbees.apigeesdk.apigee.policies.extension.statisticcollector.StatisticsCollectorPolicy;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StatisticsCollectorPolicyTests {

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
        StatisticsCollectorPolicy policy = new StatisticsCollectorPolicy();
        policy.setStatistics(
                Arrays.asList(
                        new Statistic(
                                "st1-name",
                                "st1-ref",
                                "st1-type",
                                "st1-val"
                        ),
                        new Statistic(
                                "st2-name",
                                "st2-ref",
                                null,
                                null
                        )
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "StatisticsCollectorPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "StatisticsCollectorPolicyTests.yaml");

        StatisticsCollectorPolicy policy = ymlMapper.readValue(yml, StatisticsCollectorPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getStatistics(), notNullValue());
        assertThat(policy.getStatistics(), hasSize(2));
        assertThat(policy.getStatistics().get(0).getName(), is("st1-name"));
        assertThat(policy.getStatistics().get(0).getRef(), is("st1-ref"));
        assertThat(policy.getStatistics().get(0).getType(), is("st1-type"));
        assertThat(policy.getStatistics().get(0).getValue(), is("st1-value"));
        assertThat(policy.getStatistics().get(1).getName(), is("st2-name"));
        assertThat(policy.getStatistics().get(1).getRef(), is("st2-ref"));
        assertThat(policy.getStatistics().get(1).getType(), is("st2-type"));
        assertThat(policy.getStatistics().get(1).getValue(), is("st2-value"));
    }

}
