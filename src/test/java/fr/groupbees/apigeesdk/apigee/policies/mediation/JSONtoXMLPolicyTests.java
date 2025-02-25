package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.mediation.jsontoxml.JSONtoXMLPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.jsontoxml.Options;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JSONtoXMLPolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/mediation/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        JSONtoXMLPolicy policy = new JSONtoXMLPolicy();
        policy.setSource("source");
        policy.setOutputVariable("output");

        Options options = new Options();
        options.setOmitXmlDeclaration("omit");
        options.setDefaultNamespaceNodeName("default");
        options.setNamespaceSeparator("separator");
        options.setAttributeBlockName("attrblockname");
        options.setAttributePrefix("attrprefix");
        options.setObjectRootElementName("objroot");
        options.setArrayRootElementName("arrroot");
        options.setArrayItemElementName("arritem");
        options.setNamespaceBlockName("namespace");
        options.setIndent("indent");
        options.setTextNodeName("txtnode");
        options.setNullValue("nullval");
        options.setInvalidCharsReplacement("invalid");

        policy.setOptions(options);

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "JSONtoXMLPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "JSONtoXMLPolicyTests.yaml");

        JSONtoXMLPolicy policy = ymlMapper.readValue(yml, JSONtoXMLPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getSource(), is("source"));
        assertThat(policy.getOutputVariable(), is("output"));
        assertThat(policy.getOptions(), notNullValue());
        assertThat(policy.getOptions().getOmitXmlDeclaration(), is("omit"));
        assertThat(policy.getOptions().getDefaultNamespaceNodeName(), is("default"));
        assertThat(policy.getOptions().getNamespaceSeparator(), is("separator"));
        assertThat(policy.getOptions().getNamespaceBlockName(), is("namespace"));
        assertThat(policy.getOptions().getAttributeBlockName(), is("attrblockname"));
        assertThat(policy.getOptions().getAttributePrefix(), is("attrprefix"));
        assertThat(policy.getOptions().getObjectRootElementName(), is("objroot"));
        assertThat(policy.getOptions().getArrayRootElementName(), is("arrroot"));
        assertThat(policy.getOptions().getArrayItemElementName(), is("arritem"));
        assertThat(policy.getOptions().getIndent(), is("indent"));
        assertThat(policy.getOptions().getTextNodeName(), is("txtnode"));
        assertThat(policy.getOptions().getNullValue(), is("nullval"));
        assertThat(policy.getOptions().getInvalidCharsReplacement(), is("invalid"));
    }

}
