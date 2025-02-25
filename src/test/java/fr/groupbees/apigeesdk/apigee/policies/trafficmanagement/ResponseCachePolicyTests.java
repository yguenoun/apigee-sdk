package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseCachePolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/trafficmanagement/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_DefaultValues() throws Exception {
        ResponseCachePolicy policy = new ResponseCachePolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "ResponseCachePolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        ResponseCachePolicy policy = new ResponseCachePolicy();
        policy.setCacheResource("cache");
        policy.setScope(CacheScope.Proxy);
        policy.setExcludeErrorResponse(true);
        policy.setUseAcceptHeader(true);
        policy.setUseResponseCacheHeaders(true);
        policy.setCacheLookupTimeoutInSeconds(10);

        CacheKey cacheKey = new CacheKey();
        cacheKey.setPrefix("prefix");

        CacheKeyValueRef<String> keyFragment1 = new CacheKeyValueRef<>("val1", "ref1", null);
        CacheKeyValueRef<String> keyFragment2 = new CacheKeyValueRef<>("val2", "ref2", null);
        cacheKey.setKeyFragments(Arrays.asList(keyFragment1, keyFragment2));

        CacheContext cacheContext = new CacheContext();
        cacheContext.setApiProxyName(new ValueRef<>("apiproxy", "apiproxy-ref"));
        cacheContext.setProxyName(new ValueRef<>("proxy", "proxy-ref"));
        cacheContext.setTargetName(new ValueRef<>("target", "target-ref"));

        ExpirySettings expirySettings = new ExpirySettings();
        expirySettings.setExpiryDate(new ValueRef<>(10, "date-ref"));
        expirySettings.setTimeOfDay(new ValueRef<>(20, "time-ref"));
        expirySettings.setTimeoutInSec(new ValueRef<>(30, "sec-ref"));

        policy.setCacheKey(cacheKey);
        policy.setExpirySettings(expirySettings);

        policy.setSkipCacheLookup("skip-look");
        policy.setSkipCachePopulation("skip-pop");

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "ResponseCachePolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "ResponseCachePolicyTests.yaml");

        ResponseCachePolicy policy = ymlMapper.readValue(yml, ResponseCachePolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getCacheKey(), notNullValue());
        assertThat(policy.getCacheKey().getKeyFragments(), notNullValue());
        assertThat(policy.getCacheKey().getKeyFragments(), hasSize(2));
        assertThat(policy.getCacheKey().getKeyFragments().get(0).getRef(), is("ref1"));
        assertThat(policy.getCacheKey().getKeyFragments().get(0).getValue(), is("val1"));
        assertThat(policy.getCacheKey().getKeyFragments().get(1).getRef(), is("ref2"));
        assertThat(policy.getCacheKey().getKeyFragments().get(1).getValue(), is("val2"));
        assertThat(policy.getCacheKey().getPrefix(), is("prefix"));
        assertThat(policy.getCacheLookupTimeoutInSeconds(), is(10));
        assertThat(policy.getCacheResource(), is("cache"));
        assertThat(policy.getExcludeErrorResponse(), is(true));
        assertThat(policy.getExpirySettings(), notNullValue());
        assertThat(policy.getExpirySettings().getExpiryDate(), notNullValue());
        assertThat(policy.getExpirySettings().getExpiryDate().getRef(), is("date-ref"));
        assertThat(policy.getExpirySettings().getExpiryDate().getValue(), is(10));
        assertThat(policy.getExpirySettings().getTimeOfDay(), notNullValue());
        assertThat(policy.getExpirySettings().getTimeOfDay().getRef(), is("time-ref"));
        assertThat(policy.getExpirySettings().getTimeOfDay().getValue(), is(20));
        assertThat(policy.getExpirySettings().getTimeoutInSec(), notNullValue());
        assertThat(policy.getExpirySettings().getTimeoutInSec().getRef(), is("sec-ref"));
        assertThat(policy.getExpirySettings().getTimeoutInSec().getValue(), is(30));
        assertThat(policy.getScope(), is(CacheScope.Proxy));
        assertThat(policy.getSkipCacheLookup(), is("skip-look"));
        assertThat(policy.getSkipCachePopulation(), is("skip-pop"));
        assertThat(policy.getUseAcceptHeader(), is(true));
        assertThat(policy.getUseResponseCacheHeaders(), is(true));
    }

}
