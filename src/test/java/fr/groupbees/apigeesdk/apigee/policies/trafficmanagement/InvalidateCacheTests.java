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

public class InvalidateCacheTests {

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
        InvalidateCachePolicy policy = new InvalidateCachePolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "InvalidateCacheTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        InvalidateCachePolicy policy = new InvalidateCachePolicy();
        policy.setCacheResource("cache");
        policy.setPurgeChildEntries(true);
        policy.setScope(CacheScope.Proxy);

        CacheKey cacheKey = new CacheKey();
        cacheKey.setPrefix("prefix");

        CacheKeyValueRef<String> keyFragment1 = new CacheKeyValueRef<>("val1", "ref1", "type1");
        CacheKeyValueRef<String> keyFragment2 = new CacheKeyValueRef<>("val2", "ref2", "type2");
        cacheKey.setKeyFragments(Arrays.asList(keyFragment1, keyFragment2));

        CacheContext cacheContext = new CacheContext();
        cacheContext.setApiProxyName(new ValueRef<>("apiproxy", "apiproxy-ref"));
        cacheContext.setProxyName(new ValueRef<>("proxy", "proxy-ref"));
        cacheContext.setTargetName(new ValueRef<>("target", "target-ref"));

        policy.setCacheKey(cacheKey);
        policy.setCacheContext(cacheContext);
        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "InvalidateCacheTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "InvalidateCacheTests.yaml");

        InvalidateCachePolicy policy = ymlMapper.readValue(yml, InvalidateCachePolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getCacheContext(), notNullValue());
        assertThat(policy.getCacheContext().getApiProxyName(), notNullValue());
        assertThat(policy.getCacheContext().getApiProxyName().getRef(), is("apiproxy-ref"));
        assertThat(policy.getCacheContext().getApiProxyName().getValue(), is("apiproxy"));
        assertThat(policy.getCacheContext().getProxyName(), notNullValue());
        assertThat(policy.getCacheContext().getProxyName().getRef(), is("proxy-ref"));
        assertThat(policy.getCacheContext().getProxyName().getValue(), is("proxy"));
        assertThat(policy.getCacheContext().getTargetName(), notNullValue());
        assertThat(policy.getCacheContext().getTargetName().getRef(), is("target-ref"));
        assertThat(policy.getCacheContext().getTargetName().getValue(), is("target"));
        assertThat(policy.getCacheKey(), notNullValue());
        assertThat(policy.getCacheKey().getKeyFragments(), notNullValue());
        assertThat(policy.getCacheKey().getKeyFragments(), hasSize(2));
        assertThat(policy.getCacheKey().getKeyFragments().get(0).getRef(), is("ref1"));
        assertThat(policy.getCacheKey().getKeyFragments().get(0).getValue(), is("val1"));
        assertThat(policy.getCacheKey().getKeyFragments().get(1).getRef(), is("ref2"));
        assertThat(policy.getCacheKey().getKeyFragments().get(1).getValue(), is("val2"));
        assertThat(policy.getCacheKey().getPrefix(), is("prefix"));
        assertThat(policy.getCacheResource(), is("cache"));
        assertThat(policy.getPurgeChildEntries(), is(true));
        assertThat(policy.getScope(), is(CacheScope.Proxy));
    }

}
