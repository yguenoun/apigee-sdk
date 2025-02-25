package fr.groupbees.apigeesdk.apigee.policies.security;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.security.oauth.*;
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

public class OAuthV2PolicyTests {

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
        OAuthV2Policy policy = new OAuthV2Policy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "OAuthV2PolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        OAuthV2Policy policy = new OAuthV2Policy();
        policy.setAccessToken("access_token");
        policy.setAccessTokenPrefix("access_token_prefix");
        policy.setAppEndUser("app_end_user");
        policy.setAttributes(
                Arrays.asList(
                        new Attribute(
                                "attr1-name",
                                "attr1-ref",
                                true,
                                "attr1-val"
                        ),
                        new Attribute(
                                "attr2-name",
                                null,
                                false,
                                "attr2-val"
                        ),
                        new Attribute(
                                "attr3-name",
                                "attr3-ref",
                                null,
                                null
                        )
                )
        );
        policy.setClientId("client_id");
        policy.setCode("code");
        policy.setExpiresIn(new ValueRef<>("exp-val", "exp-ref"));
        policy.setExternalAccessToken("external_access_token");
        policy.setExternalAuthorization(true);
        policy.setExternalAuthorizationCode("external_authorization_code");
        policy.setExternalRefreshToken("external_refresh_token");
        policy.setGenerateResponse(new GenerateResponse(true));
        policy.setGenerateErrorResponse(new GenerateResponse(true));
        policy.setGrantType("grant_type");
        policy.setOperation(OAuthOperation.GenerateAccessTokenImplicitGrant);
        policy.setPassWord("password");
        policy.setRedirectUri("redirect_uri");
        policy.setRefreshToken("refresh_token");
        policy.setRefreshTokenExpiresIn(new ValueRef<>("expt-val", "expt-ref"));
        policy.setResponseType("response_type");
        policy.setReuseRefreshToken(true);
        policy.setScope("scope");
        policy.setState("state");
        policy.setStoreToken(true);
        policy.setSupportedGrantTypes(
                new SupportedGrantTypes(
                        Arrays.asList(OAuthGrantType.client_credentials, OAuthGrantType.password)
                )
        );
        policy.setTokens(
                Arrays.asList(
                        new Token(
                                TokenType.accesstoken,
                                true,
                                "token1"
                        ),
                        new Token(
                                TokenType.refreshtoken,
                                false,
                                "token2"
                        ),
                        new Token(
                                TokenType.accesstoken,
                                null,
                                "token3"
                        )
                )
        );
        policy.setUserName("username");

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "OAuthV2PolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "OAuthV2PolicyTests.yaml");

        OAuthV2Policy policy = ymlMapper.readValue(yml, OAuthV2Policy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getAccessToken(), is("access_token"));
        assertThat(policy.getAccessTokenPrefix(), is("access_token_prefix"));
        assertThat(policy.getAppEndUser(), is("app_end_user"));

        List<Attribute> attributes = policy.getAttributes();
        assertThat(attributes, notNullValue());
        assertThat(attributes, hasSize(3));
        assertThat(attributes.get(0).getDisplay(), is(true));
        assertThat(attributes.get(0).getName(), is("attr1-name"));
        assertThat(attributes.get(0).getRef(), is("attr1-ref"));
        assertThat(attributes.get(0).getValue(), is("attr1-val"));
        assertThat(attributes.get(1).getDisplay(), is(false));
        assertThat(attributes.get(1).getName(), is("attr2-name"));
        assertThat(attributes.get(1).getValue(), is("attr2-val"));
        assertThat(attributes.get(2).getName(), is("attr3-name"));
        assertThat(attributes.get(2).getValue(), is("attr3-val"));

        assertThat(policy.getClientId(), is("client_id"));
        assertThat(policy.getCode(), is("code"));
        assertThat(policy.getExpiresIn(), notNullValue());
        MatcherAssert.assertThat(policy.getExpiresIn().getRef(), is("exp-ref"));
        MatcherAssert.assertThat(policy.getExpiresIn().getValue(), is("exp-val"));
        assertThat(policy.getExternalAccessToken(), is("external_access_token"));
        assertThat(policy.getExternalAuthorization(), is(true));
        assertThat(policy.getExternalAuthorizationCode(), is("external_authorization_code"));
        assertThat(policy.getExternalRefreshToken(), is("external_refresh_token"));
        assertThat(policy.getGenerateErrorResponse(), notNullValue());
        assertThat(policy.getGenerateErrorResponse().getEnabled(), is(true));
        assertThat(policy.getGenerateResponse(), notNullValue());
        assertThat(policy.getGenerateResponse().getEnabled(), is(true));
        assertThat(policy.getGrantType(), is("grant_type"));
        assertThat(policy.getOperation(), is(OAuthOperation.GenerateAccessTokenImplicitGrant));
        assertThat(policy.getPassWord(), is("password"));
        assertThat(policy.getRedirectUri(), is("redirect_uri"));
        assertThat(policy.getRefreshToken(), is("refresh_token"));
        assertThat(policy.getRefreshTokenExpiresIn(), notNullValue());
        MatcherAssert.assertThat(policy.getRefreshTokenExpiresIn().getRef(), is("expt-ref"));
        MatcherAssert.assertThat(policy.getRefreshTokenExpiresIn().getValue(), is("expt-val"));
        assertThat(policy.getResponseType(), is("response_type"));
        assertThat(policy.getReuseRefreshToken(), is(true));
        assertThat(policy.getScope(), is("scope"));
        assertThat(policy.getState(), is("state"));
        assertThat(policy.getStoreToken(), is(true));
        assertThat(policy.getUserName(), is("username"));

        SupportedGrantTypes supportedGrantTypes = policy.getSupportedGrantTypes();
        assertThat(supportedGrantTypes, notNullValue());
        assertThat(supportedGrantTypes.getGrantTypes(), notNullValue());
        assertThat(supportedGrantTypes.getGrantTypes(), hasSize(2));
        assertThat(supportedGrantTypes.getGrantTypes().get(0), is(OAuthGrantType.client_credentials));
        assertThat(supportedGrantTypes.getGrantTypes().get(1), is(OAuthGrantType.password));

        List<Token> tokens = policy.getTokens();
        assertThat(tokens, notNullValue());
        assertThat(tokens, hasSize(3));
        assertThat(tokens.get(0).getCascade(), is(true));
        assertThat(tokens.get(0).getType(), is(TokenType.accesstoken));
        assertThat(tokens.get(0).getValue(), is("token1"));
        assertThat(tokens.get(1).getCascade(), is(false));
        assertThat(tokens.get(1).getType(), is(TokenType.refreshtoken));
        assertThat(tokens.get(1).getValue(), is("token2"));
        assertThat(tokens.get(2).getCascade(), nullValue());
        assertThat(tokens.get(2).getType(), is(TokenType.accesstoken));
        assertThat(tokens.get(2).getValue(), is("token3"));
    }

}
