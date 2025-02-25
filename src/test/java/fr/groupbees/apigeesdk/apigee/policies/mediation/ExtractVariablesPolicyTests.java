package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.xml.Namespace;
import fr.groupbees.apigeesdk.apigee.policies.mediation.extractvariables.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExtractVariablesPolicyTests {

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
        ExtractVariablesPolicy policy = new ExtractVariablesPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "ExtractVariablesPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        ExtractVariablesPolicy policy = new ExtractVariablesPolicy();
        policy.setSource(new Source(true, "source"));
        policy.setVariablePrefix("prefix");
        policy.setIgnoreUnresolvedVariables(false);
        policy.setUriPaths(
                Arrays.asList(
                        new Pattern(true, "pattern1"),
                        new Pattern(false, "pattern2"),
                        new Pattern(true, "pattern3")
                )
        );
        policy.setHeaders(
                Arrays.asList(
                        new NamedPattern("hname1", Collections.singletonList(new Pattern(true, "h1pattern1"))),
                        new NamedPattern("hname2", Arrays.asList(
                                new Pattern(false, "h2pattern1"),
                                new Pattern(false, "h2pattern2")
                        ))
                )
        );
        policy.setFormParams(
                Arrays.asList(
                        new NamedPattern("fpname1", Collections.singletonList(new Pattern(true, "fp1pattern1"))),
                        new NamedPattern("fpname2", Arrays.asList(
                                new Pattern(false, "fp2pattern1"),
                                new Pattern(false, "fp2pattern2")
                        ))
                )
        );
        policy.setQueryParams(
                Arrays.asList(
                        new NamedPattern("qpname1", Collections.singletonList(new Pattern(true, "qp1pattern1"))),
                        new NamedPattern("qpname2", Arrays.asList(
                                new Pattern(false, "qp2pattern1"),
                                new Pattern(false, "qp2pattern2")
                        ))
                )
        );
        policy.setVariables(
                Arrays.asList(
                        new NamedPattern("vname1", Collections.singletonList(new Pattern(true, "v1pattern1"))),
                        new NamedPattern("vname2", Arrays.asList(
                                new Pattern(false, "v2pattern1"),
                                new Pattern(false, "v2pattern2")
                        ))
                )
        );
        policy.setJsonPayload(
                new JSONPayload(
                        Arrays.asList(
                                new JSONVariable("name1", "type1", "path1"),
                                new JSONVariable("name2", "type2", "path2"),
                                new JSONVariable("name3", "type3", "path3")
                        )
                )
        );
        policy.setXmlPayload(
                new XMLPayload(
                        true,
                        Arrays.asList(
                                new Namespace("prefix1", "namespace1"),
                                new Namespace("prefix2", "namespace2"),
                                new Namespace("prefix3", "namespace3")
                        ),
                        Arrays.asList(
                                new XMLVariable("name1", "type1", "path1"),
                                new XMLVariable("name2", "type2", "path2"),
                                new XMLVariable("name3", "type3", "path3")
                        )
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "ExtractVariablesPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "ExtractVariablesPolicyTests.yaml");

        ExtractVariablesPolicy policy = ymlMapper.readValue(yml, ExtractVariablesPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getIgnoreUnresolvedVariables(), is(false));
        assertThat(policy.getVariablePrefix(), is("prefix"));

        List<NamedPattern> formParams = policy.getFormParams();
        assertThat(formParams, notNullValue());
        assertThat(formParams, hasSize(2));
        assertThat(formParams.get(0).getName(), is("fpname1"));
        assertThat(formParams.get(0).getPatterns(), notNullValue());
        assertThat(formParams.get(0).getPatterns(), hasSize(1));
        assertThat(formParams.get(0).getPatterns().get(0).getIgnoreCase(), is(true));
        assertThat(formParams.get(0).getPatterns().get(0).getValue(), is("fp1pattern1"));
        assertThat(formParams.get(1).getName(), is("fpname2"));
        assertThat(formParams.get(1).getPatterns(), notNullValue());
        assertThat(formParams.get(1).getPatterns(), hasSize(2));
        assertThat(formParams.get(1).getPatterns().get(0).getIgnoreCase(), is(false));
        assertThat(formParams.get(1).getPatterns().get(0).getValue(), is("fp2pattern1"));
        assertThat(formParams.get(1).getPatterns().get(1).getIgnoreCase(), is(false));
        assertThat(formParams.get(1).getPatterns().get(1).getValue(), is("fp2pattern2"));

        List<NamedPattern> headers = policy.getHeaders();
        assertThat(headers, notNullValue());
        assertThat(headers, hasSize(2));
        assertThat(headers.get(0).getName(), is("hname1"));
        assertThat(headers.get(0).getPatterns(), notNullValue());
        assertThat(headers.get(0).getPatterns(), hasSize(1));
        assertThat(headers.get(0).getPatterns().get(0).getIgnoreCase(), is(true));
        assertThat(headers.get(0).getPatterns().get(0).getValue(), is("h1pattern1"));
        assertThat(headers.get(1).getName(), is("hname2"));
        assertThat(headers.get(1).getPatterns(), notNullValue());
        assertThat(headers.get(1).getPatterns(), hasSize(2));
        assertThat(headers.get(1).getPatterns().get(0).getIgnoreCase(), is(false));
        assertThat(headers.get(1).getPatterns().get(0).getValue(), is("h2pattern1"));
        assertThat(headers.get(1).getPatterns().get(1).getIgnoreCase(), is(false));
        assertThat(headers.get(1).getPatterns().get(1).getValue(), is("h2pattern2"));
        assertThat(headers.get(1).getPatterns().get(1).getValue(), is("h2pattern2"));

        List<NamedPattern> queryParams = policy.getQueryParams();
        assertThat(queryParams, notNullValue());
        assertThat(queryParams, hasSize(2));
        assertThat(queryParams.get(0).getName(), is("qpname1"));
        assertThat(queryParams.get(0).getPatterns(), notNullValue());
        assertThat(queryParams.get(0).getPatterns(), hasSize(1));
        assertThat(queryParams.get(0).getPatterns().get(0).getIgnoreCase(), is(true));
        assertThat(queryParams.get(0).getPatterns().get(0).getValue(), is("qp1pattern1"));
        assertThat(queryParams.get(1).getName(), is("qpname2"));
        assertThat(queryParams.get(1).getPatterns(), notNullValue());
        assertThat(queryParams.get(1).getPatterns(), hasSize(2));
        assertThat(queryParams.get(1).getPatterns().get(0).getIgnoreCase(), is(false));
        assertThat(queryParams.get(1).getPatterns().get(0).getValue(), is("qp2pattern1"));
        assertThat(queryParams.get(1).getPatterns().get(1).getIgnoreCase(), is(false));
        assertThat(queryParams.get(1).getPatterns().get(1).getValue(), is("qp2pattern2"));
        assertThat(queryParams.get(1).getPatterns().get(1).getValue(), is("qp2pattern2"));

        assertThat(policy.getUriPaths(), notNullValue());
        assertThat(policy.getUriPaths(), hasSize(3));
        assertThat(policy.getUriPaths().get(0).getIgnoreCase(), is(true));
        assertThat(policy.getUriPaths().get(0).getValue(), is("pattern1"));
        assertThat(policy.getUriPaths().get(1).getIgnoreCase(), is(false));
        assertThat(policy.getUriPaths().get(1).getValue(), is("pattern2"));
        assertThat(policy.getUriPaths().get(2).getIgnoreCase(), is(true));
        assertThat(policy.getUriPaths().get(2).getValue(), is("pattern3"));

        JSONPayload jsonPayload = policy.getJsonPayload();
        assertThat(jsonPayload, notNullValue());
        assertThat(jsonPayload.getVariables(), notNullValue());
        assertThat(jsonPayload.getVariables(), hasSize(3));
        assertThat(jsonPayload.getVariables().get(0).getName(), is("name1"));
        assertThat(jsonPayload.getVariables().get(0).getType(), is("type1"));
        assertThat(jsonPayload.getVariables().get(0).getJsonPath(), is("path1"));
        assertThat(jsonPayload.getVariables().get(1).getName(), is("name2"));
        assertThat(jsonPayload.getVariables().get(1).getType(), is("type2"));
        assertThat(jsonPayload.getVariables().get(1).getJsonPath(), is("path2"));
        assertThat(jsonPayload.getVariables().get(2).getName(), is("name3"));
        assertThat(jsonPayload.getVariables().get(2).getType(), is("type3"));
        assertThat(jsonPayload.getVariables().get(2).getJsonPath(), is("path3"));

        XMLPayload xmlPayload = policy.getXmlPayload();
        assertThat(xmlPayload, notNullValue());
        assertThat(xmlPayload.getStopPayloadProcessing(), is(true));
        assertThat(xmlPayload.getNamespaces(), notNullValue());
        assertThat(xmlPayload.getNamespaces(), hasSize(3));
        assertThat(xmlPayload.getNamespaces().get(0).getPrefix(), is("prefix1"));
        assertThat(xmlPayload.getNamespaces().get(0).getValue(), is("namespace1"));
        assertThat(xmlPayload.getNamespaces().get(1).getPrefix(), is("prefix2"));
        assertThat(xmlPayload.getNamespaces().get(1).getValue(), is("namespace2"));
        assertThat(xmlPayload.getNamespaces().get(2).getPrefix(), is("prefix3"));
        assertThat(xmlPayload.getNamespaces().get(2).getValue(), is("namespace3"));
        assertThat(xmlPayload.getVariables(), notNullValue());
        assertThat(xmlPayload.getVariables(), hasSize(3));
        assertThat(xmlPayload.getVariables().get(0).getName(), is("name1"));
        assertThat(xmlPayload.getVariables().get(0).getType(), is("type1"));
        assertThat(xmlPayload.getVariables().get(0).getXPath(), is("path1"));
        assertThat(xmlPayload.getVariables().get(1).getName(), is("name2"));
        assertThat(xmlPayload.getVariables().get(1).getType(), is("type2"));
        assertThat(xmlPayload.getVariables().get(1).getXPath(), is("path2"));
        assertThat(xmlPayload.getVariables().get(2).getName(), is("name3"));
        assertThat(xmlPayload.getVariables().get(2).getType(), is("type3"));
        assertThat(xmlPayload.getVariables().get(2).getXPath(), is("path3"));
    }

}
