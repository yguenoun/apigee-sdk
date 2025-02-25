package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.xml.Namespace;
import fr.groupbees.apigeesdk.apigee.policies.security.regexpprotection.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegularExpressionProtectionPolicyTests {

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
        RegularExpressionProtectionPolicy policy = new RegularExpressionProtectionPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RegularExpressionProtectionPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        RegularExpressionProtectionPolicy policy = new RegularExpressionProtectionPolicy();
        policy.setIgnoreUnresolvedVariables(true);
        policy.setSource("source");
        policy.setUriPaths(Arrays.asList("uri-pattern1", "uri-pattern2"));
        policy.setQueryParams(
                Arrays.asList(
                        new NamedPatterns(
                                "query1-name",
                                Arrays.asList("query1-pattern1", "query1-pattern2")
                        ),
                        new NamedPatterns(
                                "query2-name",
                                Collections.singletonList("query2-pattern1")
                        )
                )
        );
        policy.setHeaders(
                Arrays.asList(
                        new NamedPatterns(
                                "header1-name",
                                Arrays.asList("header1-pattern1", "header1-pattern2")
                        ),
                        new NamedPatterns(
                                "header2-name",
                                Collections.singletonList("header2-pattern1")
                        )
                )
        );
        policy.setFormParams(
                Arrays.asList(
                        new NamedPatterns(
                                "form1-name",
                                Arrays.asList("form1-pattern1", "form1-pattern2")
                        ),
                        new NamedPatterns(
                                "form2-name",
                                Collections.singletonList("form2-pattern1")
                        )
                )
        );
        policy.setVariables(
                Arrays.asList(
                        new NamedPatterns(
                                "variable1-name",
                                Arrays.asList("variable1-pattern1", "variable1-pattern2")
                        ),
                        new NamedPatterns(
                                "variable2-name",
                                Collections.singletonList("variable2-pattern1")
                        )
                )
        );
        policy.setXmlPayload(
                new XMLPayload(
                        Arrays.asList(
                                new Namespace("n1-pref", "n1-val"),
                                new Namespace("n2-pref", "n2-val")
                        ),
                        Arrays.asList(
                                new XPath(
                                        "xp1-exp",
                                        "xp1-type",
                                        Arrays.asList("xp1-patt1", "xp1-patt2")
                                ),
                                new XPath(
                                        "xp2-exp",
                                        "xp2-type",
                                        Arrays.asList("xp2-patt1", "xp2-patt2", "xp2-patt3")
                                )
                        )
                )
        );
        policy.setJsonPayload(
                Arrays.asList(
                        new JSONPath(
                                "jp1-exp",
                                Arrays.asList("jp1-patt1", "jp1-patt2")
                        ),
                        new JSONPath(
                                "jp2-exp",
                                Arrays.asList("jp2-patt1", "jp2-patt2", "jp2-patt3")
                        )
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "RegularExpressionProtectionPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "RegularExpressionProtectionPolicyTests.yaml");

        RegularExpressionProtectionPolicy policy = ymlMapper.readValue(yml, RegularExpressionProtectionPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getIgnoreUnresolvedVariables(), is(true));
        assertThat(policy.getSource(), is("source"));
        assertThat(policy.getUriPaths(), notNullValue());
        assertThat(policy.getUriPaths(), hasSize(2));
        assertThat(policy.getUriPaths().get(0), is("uri-pattern1"));
        assertThat(policy.getUriPaths().get(1), is("uri-pattern2"));

        List<NamedPatterns> formParams = policy.getFormParams();
        assertThat(formParams, notNullValue());
        assertThat(formParams, hasSize(2));
        assertThat(formParams.get(0).getName(), is("form1-name"));
        assertThat(formParams.get(0).getPatterns(), notNullValue());
        assertThat(formParams.get(0).getPatterns(), hasSize(2));
        assertThat(formParams.get(0).getPatterns().get(0), is("form1-pattern1"));
        assertThat(formParams.get(0).getPatterns().get(1), is("form1-pattern2"));
        assertThat(formParams.get(1).getName(), is("form2-name"));
        assertThat(formParams.get(1).getPatterns(), notNullValue());
        assertThat(formParams.get(1).getPatterns(), hasSize(1));
        assertThat(formParams.get(1).getPatterns().get(0), is("form2-pattern1"));

        List<NamedPatterns> headers = policy.getHeaders();
        assertThat(headers, notNullValue());
        assertThat(headers, hasSize(2));
        assertThat(headers.get(0).getName(), is("header1-name"));
        assertThat(headers.get(0).getPatterns(), notNullValue());
        assertThat(headers.get(0).getPatterns(), hasSize(2));
        assertThat(headers.get(0).getPatterns().get(0), is("header1-pattern1"));
        assertThat(headers.get(0).getPatterns().get(1), is("header1-pattern2"));
        assertThat(headers.get(1).getName(), is("header2-name"));
        assertThat(headers.get(1).getPatterns(), notNullValue());
        assertThat(headers.get(1).getPatterns(), hasSize(1));
        assertThat(headers.get(1).getPatterns().get(0), is("header2-pattern1"));

        List<JSONPath> jsonPayload = policy.getJsonPayload();
        assertThat(jsonPayload, notNullValue());
        assertThat(jsonPayload, hasSize(2));
        assertThat(jsonPayload.get(0).getExpression(), is("jp1-exp"));
        assertThat(jsonPayload.get(0).getPatterns(), notNullValue());
        assertThat(jsonPayload.get(0).getPatterns(), hasSize(2));
        assertThat(jsonPayload.get(0).getPatterns().get(0), is("jp1-patt1"));
        assertThat(jsonPayload.get(0).getPatterns().get(1), is("jp1-patt2"));
        assertThat(jsonPayload.get(1).getExpression(), is("jp2-exp"));
        assertThat(jsonPayload.get(1).getPatterns(), notNullValue());
        assertThat(jsonPayload.get(1).getPatterns(), hasSize(3));
        assertThat(jsonPayload.get(1).getPatterns().get(0), is("jp2-patt1"));
        assertThat(jsonPayload.get(1).getPatterns().get(1), is("jp2-patt2"));
        assertThat(jsonPayload.get(1).getPatterns().get(2), is("jp2-patt3"));

        List<NamedPatterns> queryParams = policy.getQueryParams();
        assertThat(queryParams, notNullValue());
        assertThat(queryParams, hasSize(2));
        assertThat(queryParams.get(0).getName(), is("query1-name"));
        assertThat(queryParams.get(0).getPatterns(), notNullValue());
        assertThat(queryParams.get(0).getPatterns(), hasSize(2));
        assertThat(queryParams.get(0).getPatterns().get(0), is("query1-pattern1"));
        assertThat(queryParams.get(0).getPatterns().get(1), is("query1-pattern2"));
        assertThat(queryParams.get(1).getName(), is("query2-name"));
        assertThat(queryParams.get(1).getPatterns(), notNullValue());
        assertThat(queryParams.get(1).getPatterns(), hasSize(1));
        assertThat(queryParams.get(1).getPatterns().get(0), is("query2-pattern1"));

        List<NamedPatterns> variables = policy.getVariables();
        assertThat(variables, notNullValue());
        assertThat(variables, hasSize(2));
        assertThat(variables.get(0).getName(), is("variable1-name"));
        assertThat(variables.get(0).getPatterns(), notNullValue());
        assertThat(variables.get(0).getPatterns(), hasSize(2));
        assertThat(variables.get(0).getPatterns().get(0), is("variable1-pattern1"));
        assertThat(variables.get(0).getPatterns().get(1), is("variable1-pattern2"));
        assertThat(variables.get(1).getName(), is("variable2-name"));
        assertThat(variables.get(1).getPatterns(), notNullValue());
        assertThat(variables.get(1).getPatterns(), hasSize(1));
        assertThat(variables.get(1).getPatterns().get(0), is("variable2-pattern1"));

        XMLPayload xmlPayload = policy.getXmlPayload();
        assertThat(xmlPayload, notNullValue());
        assertThat(xmlPayload.getNamespaces(), notNullValue());
        MatcherAssert.assertThat(xmlPayload.getNamespaces(), hasSize(2));
        MatcherAssert.assertThat(xmlPayload.getNamespaces().get(0).getPrefix(), is("n1-pref"));
        MatcherAssert.assertThat(xmlPayload.getNamespaces().get(0).getValue(), is("n1-val"));
        MatcherAssert.assertThat(xmlPayload.getNamespaces().get(1).getPrefix(), is("n2-pref"));
        MatcherAssert.assertThat(xmlPayload.getNamespaces().get(1).getValue(), is("n2-val"));
        assertThat(xmlPayload.getXPaths(), notNullValue());
        assertThat(xmlPayload.getXPaths(), hasSize(2));
        assertThat(xmlPayload.getXPaths().get(0).getExpression(), is("xp1-exp"));
        assertThat(xmlPayload.getXPaths().get(0).getType(), is("xp1-type"));
        assertThat(xmlPayload.getXPaths().get(0).getPatterns(), notNullValue());
        assertThat(xmlPayload.getXPaths().get(0).getPatterns(), hasSize(2));
        assertThat(xmlPayload.getXPaths().get(0).getPatterns().get(0), is("xp1-patt1"));
        assertThat(xmlPayload.getXPaths().get(0).getPatterns().get(1), is("xp1-patt2"));
        assertThat(xmlPayload.getXPaths().get(1).getExpression(), is("xp2-exp"));
        assertThat(xmlPayload.getXPaths().get(1).getType(), is("xp2-type"));
        assertThat(xmlPayload.getXPaths().get(1).getPatterns(), notNullValue());
        assertThat(xmlPayload.getXPaths().get(1).getPatterns(), hasSize(3));
        assertThat(xmlPayload.getXPaths().get(1).getPatterns().get(0), is("xp2-patt1"));
        assertThat(xmlPayload.getXPaths().get(1).getPatterns().get(1), is("xp2-patt2"));
        assertThat(xmlPayload.getXPaths().get(1).getPatterns().get(2), is("xp2-patt3"));
    }

}
