package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.mediation.xmltojson.Options;
import fr.groupbees.apigeesdk.apigee.policies.mediation.xmltojson.Path;
import fr.groupbees.apigeesdk.apigee.policies.mediation.xmltojson.XMLtoJSONPolicy;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class XMLtoJSONPolicyTest {

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
        XMLtoJSONPolicy policy = new XMLtoJSONPolicy();
        policy.setSource("source");
        policy.setOutputVariable("output");
        policy.setFormat("format");

        Options options = new Options();
        options.setDefaultNamespaceNodeName("default");
        options.setNamespaceSeparator("separator");
        options.setAttributeBlockName("attrblockname");
        options.setAttributePrefix("attrprefix");
        options.setTextNodeName("txtnode");
        options.setNullValue(true);
        options.setTextNodeName("txtnode");
        options.setRecognizeNumber(true);
        options.setRecognizeBoolean(true);
        options.setRecognizeNull(true);
        options.setNamespaceBlockName("nblockname");
        options.setTextAlwaysAsProperty(true);
        options.setOutputPrefix("outprefix");
        options.setOutputSuffix("outsuffix");
        options.setStripLevels(4);
        options.setTreatAsArray(
                Arrays.asList(
                        new Path(true, "path1"),
                        new Path(false, "path2")
                )
        );

        policy.setOptions(options);

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "XMLtoJSONPolicyTest.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "XMLtoJSONPolicyTest.yaml");

        XMLtoJSONPolicy policy = ymlMapper.readValue(yml, XMLtoJSONPolicy.class);
        assertThat(policy, notNullValue());
        assertThat(policy.getFormat(), is("format"));
        assertThat(policy.getOptions(), notNullValue());
        assertThat(policy.getOptions().getAttributeBlockName(), is("attrblockname"));
        assertThat(policy.getOptions().getAttributePrefix(), is("attrprefix"));
        assertThat(policy.getOptions().getDefaultNamespaceNodeName(), is("default"));
        assertThat(policy.getOptions().getNamespaceBlockName(), is("nblockname"));
        assertThat(policy.getOptions().getNamespaceSeparator(), is("separator"));
        assertThat(policy.getOptions().getNullValue(), is(true));
        assertThat(policy.getOptions().getOutputPrefix(), is("outprefix"));
        assertThat(policy.getOptions().getOutputSuffix(), is("outsuffix"));
        assertThat(policy.getOptions().getTreatAsArray(), notNullValue());
        assertThat(policy.getOptions().getTreatAsArray(), hasSize(2));
        assertThat(policy.getOptions().getTreatAsArray().get(0).getUnwrap(), is(true));
        assertThat(policy.getOptions().getTreatAsArray().get(0).getValue(), is("path1"));
        assertThat(policy.getOptions().getTreatAsArray().get(1).getUnwrap(), is(false));
        assertThat(policy.getOptions().getTreatAsArray().get(1).getValue(), is("path2"));
        assertThat(policy.getOptions().getRecognizeBoolean(), is(true));
        assertThat(policy.getOptions().getRecognizeNull(), is(true));
        assertThat(policy.getOptions().getRecognizeNumber(), is(true));
        assertThat(policy.getOptions().getStripLevels(), is(4));
        assertThat(policy.getOptions().getTextAlwaysAsProperty(), is(true));
        assertThat(policy.getOptions().getTextNodeName(), is("txtnode"));
        assertThat(policy.getOutputVariable(), is("output"));
        assertThat(policy.getSource(), is("source"));
    }

}
