package fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AssignVariableTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/mediation/assignmessage/";

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
        AssignVariable assignVariable = new AssignVariable();
        assignVariable.setName("name");
        assignVariable.setPropertySetRef("propset-ref");
        assignVariable.setRef("ref");
        assignVariable.setTemplate(new ValueRef<>(
                "tvalue",
                "tref"
        ));
        assignVariable.setValue("value");

        String xml = xmlMapper.writeValueAsString(assignVariable);

        String expected = FileUtils.readTestResource(configFilePrefixe, "AssignVariableTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "AssignVariableTests.yaml");

        AssignVariable assignVariable = ymlMapper.readValue(yml, AssignVariable.class);
        assertThat(assignVariable, notNullValue());
        assertThat(assignVariable.getName(), is("name"));
        assertThat(assignVariable.getPropertySetRef(), is("propset-ref"));
        assertThat(assignVariable.getRef(), is("ref"));
        assertThat(assignVariable.getTemplate(), notNullValue());
        MatcherAssert.assertThat(assignVariable.getTemplate().getRef(), is("tref"));
        MatcherAssert.assertThat(assignVariable.getTemplate().getValue(), is("tvalue"));
        assertThat(assignVariable.getValue(), is("value"));
    }

}
