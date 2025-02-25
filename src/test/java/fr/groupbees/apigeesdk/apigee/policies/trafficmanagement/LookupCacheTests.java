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

public class LookupCacheTests {

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
        LookupCachePolicy policy = new LookupCachePolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "LookupCacheTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        LookupCachePolicy policy = new LookupCachePolicy();
        policy.setCacheResource("cache");
        policy.setScope(CacheScope.Proxy);
        policy.setAssignTo("assign");
        policy.setCacheLookupTimeoutInSeconds(10);

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
        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "LookupCacheTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "LookupCacheTests.yaml");

        LookupCachePolicy policy = ymlMapper.readValue(yml, LookupCachePolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getCacheKey(), notNullValue());
        assertThat(policy.getCacheKey().getKeyFragments(), notNullValue());
        assertThat(policy.getCacheKey().getKeyFragments(), hasSize(2));
        assertThat(policy.getCacheKey().getKeyFragments().get(0).getRef(), is("ref1"));
        assertThat(policy.getCacheKey().getKeyFragments().get(0).getValue(), is("val1"));
        assertThat(policy.getCacheKey().getKeyFragments().get(0).getType(), is("type1"));
        assertThat(policy.getCacheKey().getKeyFragments().get(1).getRef(), is("ref2"));
        assertThat(policy.getCacheKey().getKeyFragments().get(1).getValue(), is("val2"));
        assertThat(policy.getCacheKey().getKeyFragments().get(1).getType(), is("type2"));
        assertThat(policy.getCacheKey().getPrefix(), is("prefix"));
        assertThat(policy.getCacheLookupTimeoutInSeconds(), is(10));
        assertThat(policy.getCacheResource(), is("cache"));
        assertThat(policy.getScope(), is(CacheScope.Proxy));
    }

}
