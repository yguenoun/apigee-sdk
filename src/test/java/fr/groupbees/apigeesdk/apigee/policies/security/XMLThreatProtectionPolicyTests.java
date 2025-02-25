package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.security.xmlthreatprotection.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class XMLThreatProtectionPolicyTests {

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
        XMLThreatProtectionPolicy policy = new XMLThreatProtectionPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "XMLThreatProtectionPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        XMLThreatProtectionPolicy policy = new XMLThreatProtectionPolicy();
        policy.setSource("source");
        policy.setNameLimits(new NameLimits(10, 20, 30, 40));
        policy.setStructuralLimits(new StructuralLimits(
                10,
                20,
                30,
                new ChildCount(true, false, true, false, 40)
        ));
        policy.setValueLimits(new ValueLimits(10, 20, 30, 40, 50));

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "XMLThreatProtectionPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "XMLThreatProtectionPolicyTests.yaml");

        XMLThreatProtectionPolicy policy = ymlMapper.readValue(yml, XMLThreatProtectionPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getNameLimits(), notNullValue());
        assertThat(policy.getNameLimits().getAttribute(), is(20));
        assertThat(policy.getNameLimits().getElement(), is(10));
        assertThat(policy.getNameLimits().getNamespacePrefix(), is(30));
        assertThat(policy.getNameLimits().getProcessingInstructionTarget(), is(40));
        assertThat(policy.getSource(), is("source"));
        assertThat(policy.getStructuralLimits(), notNullValue());
        assertThat(policy.getStructuralLimits().getAttributeCountPerElement(), is(20));
        assertThat(policy.getStructuralLimits().getChildCount(), notNullValue());
        assertThat(policy.getStructuralLimits().getChildCount().getIncludeComment(), is(true));
        assertThat(policy.getStructuralLimits().getChildCount().getIncludeElement(), is(false));
        assertThat(policy.getStructuralLimits().getChildCount().getIncludeProcessingInstruction(), is(true));
        assertThat(policy.getStructuralLimits().getChildCount().getIncludeText(), is(false));
        assertThat(policy.getStructuralLimits().getChildCount().getValue(), is(40));
        assertThat(policy.getStructuralLimits().getNamespaceCountPerElement(), is(30));
        assertThat(policy.getStructuralLimits().getNodeDepth(), is(10));
        assertThat(policy.getValueLimits(), notNullValue());
        assertThat(policy.getValueLimits().getAttribute(), is(20));
        assertThat(policy.getValueLimits().getComment(), is(40));
        assertThat(policy.getValueLimits().getNamespaceURI(), is(30));
        assertThat(policy.getValueLimits().getProcessingInstructionData(), is(50));
        assertThat(policy.getValueLimits().getText(), is(10));
    }

}
