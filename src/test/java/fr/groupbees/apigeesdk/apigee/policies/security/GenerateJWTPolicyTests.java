package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.security.jwx.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GenerateJWTPolicyTests {

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
        GenerateJWTPolicy policy = new GenerateJWTPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "GenerateJWTPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        GenerateJWTPolicy policy = new GenerateJWTPolicy();
        policy.setAlgorithm("algtop");
        policy.setAudience(new ValueRef<>("aud-val", "aud-ref"));
        policy.setCriticalHeaders(new ValueRef<>("h1,h2,h3", "crit-ref"));
        policy.setOutputVariable("output");
        policy.setIgnoreUnresolvedVariables(true);
        policy.setExpiresIn("1h");
        policy.setId(new ValueRef<>("id-val", "id-ref"));
        policy.setIssuer(new ValueRef<>("iss-val", "iss-ref"));
        policy.setNotBefore("10m");
        policy.setSubject(new ValueRef<>("sub-val", "sub-ref"));
        policy.setPrivateKey(
                new PrivateKey(
                        new ValueRef<>("privateid-val", "privateid-ref"),
                        new Ref("privatepwd-ref"),
                        new Ref("privateval-ref")
                )
        );
        policy.setSecretKey(
                new SecretKey(
                        new ValueRef<>("secretid-val", "secretid-ref"),
                        new Ref("secretval-ref")
                )
        );
        policy.setAdditionalHeaders(
                Arrays.asList(
                        new Claim(
                                "ahclaim1-name",
                                "ahclaim1-ref",
                                "ahclaim1-type",
                                false,
                                "ahclaim1-val"
                        ),
                        new Claim(
                                "ahclaim2-name",
                                "ahclaim2-ref",
                                "ahclaim2-type",
                                true,
                                null
                        )
                )
        );
        policy.setAdditionalClaims(
                new AdditionalClaims(
                        "ac-ref",
                        Arrays.asList(
                                new Claim(
                                        "acclaim1-name",
                                        "acclaim1-ref",
                                        "acclaim1-type",
                                        false,
                                        "acclaim1-val"
                                ),
                                new Claim(
                                        "acclaim2-name",
                                        "acclaim2-ref",
                                        "acclaim2-type",
                                        true,
                                        null
                                )
                        )
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "GenerateJWTPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "GenerateJWTPolicyTests.yaml");

        GenerateJWTPolicy policy = ymlMapper.readValue(yml, GenerateJWTPolicy.class);

        assertThat(policy, notNullValue());

        AdditionalClaims additionalClaims = policy.getAdditionalClaims();
        assertThat(additionalClaims, notNullValue());
        assertThat(additionalClaims.getRef(), is("ac-ref"));
        assertThat(additionalClaims.getClaims(), notNullValue());
        assertThat(additionalClaims.getClaims(), hasSize(2));
        assertThat(additionalClaims.getClaims().get(0).getArray(), is(false));
        assertThat(additionalClaims.getClaims().get(0).getName(), is("acclaim1-name"));
        assertThat(additionalClaims.getClaims().get(0).getRef(), is("acclaim1-ref"));
        assertThat(additionalClaims.getClaims().get(0).getType(), is("acclaim1-type"));
        assertThat(additionalClaims.getClaims().get(0).getValue(), is("acclaim1-val"));
        assertThat(additionalClaims.getClaims().get(1).getArray(), is(true));
        assertThat(additionalClaims.getClaims().get(1).getName(), is("acclaim2-name"));
        assertThat(additionalClaims.getClaims().get(1).getRef(), is("acclaim2-ref"));
        assertThat(additionalClaims.getClaims().get(1).getType(), is("acclaim2-type"));
        assertThat(additionalClaims.getClaims().get(1).getValue(), nullValue());

        assertThat(policy.getAlgorithm(), is("algtop"));
        assertThat(policy.getAudience(), notNullValue());
        MatcherAssert.assertThat(policy.getAudience().getRef(), is("aud-ref"));
        MatcherAssert.assertThat(policy.getAudience().getValue(), is("aud-val"));

        List<Claim> additionalHeaders = policy.getAdditionalHeaders();
        assertThat(additionalHeaders, notNullValue());
        assertThat(additionalHeaders, hasSize(2));
        assertThat(additionalHeaders.get(0).getArray(), is(false));
        assertThat(additionalHeaders.get(0).getName(), is("ahclaim1-name"));
        assertThat(additionalHeaders.get(0).getRef(), is("ahclaim1-ref"));
        assertThat(additionalHeaders.get(0).getType(), is("ahclaim1-type"));
        assertThat(additionalHeaders.get(0).getValue(), is("ahclaim1-val"));
        assertThat(additionalHeaders.get(1).getArray(), is(true));
        assertThat(additionalHeaders.get(1).getName(), is("ahclaim2-name"));
        assertThat(additionalHeaders.get(1).getRef(), is("ahclaim2-ref"));
        assertThat(additionalHeaders.get(1).getType(), is("ahclaim2-type"));

        assertThat(policy.getCriticalHeaders(), notNullValue());
        MatcherAssert.assertThat(policy.getCriticalHeaders().getRef(), is("crit-ref"));
        MatcherAssert.assertThat(policy.getCriticalHeaders().getValue(), is("h1,h2,h3"));
        assertThat(policy.getExpiresIn(), is("1h"));
        assertThat(policy.getId(), notNullValue());
        MatcherAssert.assertThat(policy.getId().getRef(), is("id-ref"));
        MatcherAssert.assertThat(policy.getId().getValue(), is("id-val"));
        assertThat(policy.getIgnoreUnresolvedVariables(), is(true));
        assertThat(policy.getIssuer(), notNullValue());
        MatcherAssert.assertThat(policy.getIssuer().getRef(), is("iss-ref"));
        MatcherAssert.assertThat(policy.getIssuer().getValue(), is("iss-val"));
        assertThat(policy.getNotBefore(), is("10m"));
        assertThat(policy.getOutputVariable(), is("output"));
        assertThat(policy.getPrivateKey(), notNullValue());
        MatcherAssert.assertThat(policy.getPrivateKey().getId().getRef(), is("privateid-ref"));
        MatcherAssert.assertThat(policy.getPrivateKey().getId().getValue(), is("privateid-val"));
        MatcherAssert.assertThat(policy.getPrivateKey().getPassword().getRef(), is("privatepwd-ref"));
        MatcherAssert.assertThat(policy.getPrivateKey().getValue().getRef(), is("privateval-ref"));
        assertThat(policy.getSecretKey(), notNullValue());
        MatcherAssert.assertThat(policy.getSecretKey().getId().getRef(), is("secretid-ref"));
        MatcherAssert.assertThat(policy.getSecretKey().getId().getValue(), is("secretid-val"));
        MatcherAssert.assertThat(policy.getSecretKey().getValue().getRef(), is("secretval-ref"));
        assertThat(policy.getSubject(), notNullValue());
        MatcherAssert.assertThat(policy.getSubject().getRef(), is("sub-ref"));
        MatcherAssert.assertThat(policy.getSubject().getValue(), is("sub-val"));
    }

}
