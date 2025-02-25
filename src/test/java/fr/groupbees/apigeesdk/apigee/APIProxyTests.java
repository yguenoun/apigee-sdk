package fr.groupbees.apigeesdk.apigee;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class APIProxyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        APIProxy apiProxy = new APIProxy();
        apiProxy.setName("name");
        apiProxy.setDisplayName("display-name");
        apiProxy.setDescription("description");
        apiProxy.setRevision(1);
        apiProxy.setConfigurationVersion(new ConfigurationVersion(4, 0));
        apiProxy.setCreatedAt(10000L);
        apiProxy.setCreatedBy("foo");
        apiProxy.setLastModifiedAt(15000L);
        apiProxy.setLastModifiedBy("bar");
        apiProxy.setBasepaths("/v1/path1");
        apiProxy.setPolicies(new LinkedHashSet<>(Arrays.asList("Policy1", "Policy2", "Policy3")));
        apiProxy.setProxyEndpoints(new LinkedHashSet<>(Arrays.asList("ProxyPath1", "ProxyPath2", "ProxyPath3")));
        apiProxy.setTargetEndpoints(new LinkedHashSet<>(Arrays.asList("TargetPath1", "TargetPath2", "TargetPath3")));
        apiProxy.setResources(new LinkedHashSet<>(Arrays.asList("type1://file1.x", "type2://file2.x", "type3://file3.x")));
        apiProxy.setTargetServers(new LinkedHashSet<>(Arrays.asList("targetServer1", "targetServer2", "targetServer3")));
        apiProxy.setSpec("oas://file.yml");
        apiProxy.setSharedFlows(new LinkedHashSet<>(Arrays.asList("sharedFlow1", "sharedFlow2", "sharedFlow3")));

        String xml = xmlMapper.writeValueAsString(apiProxy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "APIProxyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "APIProxyTests.yaml");

        APIProxy apiProxy = ymlMapper.readValue(yml, APIProxy.class);

        assertThat(apiProxy, notNullValue());
        assertThat(apiProxy.getName(), is("name"));
        assertThat(apiProxy.getRevision(), is(1));
        assertThat(apiProxy.getBasepaths(), notNullValue());
        assertThat(apiProxy.getBasepaths(), is("v1/path1"));

        assertThat(apiProxy.getConfigurationVersion(), notNullValue());
        assertThat(apiProxy.getConfigurationVersion().getMajorVersion(), is(4));
        assertThat(apiProxy.getConfigurationVersion().getMinorVersion(), is(0));
        assertThat(apiProxy.getCreatedAt(), is(10000L));
        assertThat(apiProxy.getCreatedBy(), is("foo"));
        assertThat(apiProxy.getDescription(), is("description"));
        assertThat(apiProxy.getDisplayName(), is("display-name"));
        assertThat(apiProxy.getLastModifiedAt(), is(15000L));
        assertThat(apiProxy.getLastModifiedBy(), is("bar"));

        assertThat(apiProxy.getPolicies(), notNullValue());
        assertThat(apiProxy.getPolicies(), hasSize(3));
        Iterator<String> policies = apiProxy.getPolicies().iterator();
        String policy = policies.next();
        assertThat(policy, is("Policy1"));
        policy = policies.next();
        assertThat(policy, is("Policy2"));
        policy = policies.next();
        assertThat(policy, is("Policy3"));

        assertThat(apiProxy.getProxyEndpoints(), notNullValue());
        assertThat(apiProxy.getProxyEndpoints(), hasSize(3));
        Iterator<String> proxies = apiProxy.getProxyEndpoints().iterator();
        String proxy = proxies.next();
        assertThat(proxy, is("ProxyPath1"));
        proxy = proxies.next();
        assertThat(proxy, is("ProxyPath2"));
        proxy = proxies.next();
        assertThat(proxy, is("ProxyPath3"));

        assertThat(apiProxy.getResources(), notNullValue());
        assertThat(apiProxy.getResources(), hasSize(3));
        Iterator<String> resources = apiProxy.getResources().iterator();
        String resource = resources.next();
        assertThat(resource, is("type1://file1.x"));
        resource = resources.next();
        assertThat(resource, is("type2://file2.x"));
        resource = resources.next();
        assertThat(resource, is("type3://file3.x"));

        assertThat(apiProxy.getSharedFlows(), notNullValue());
        assertThat(apiProxy.getSharedFlows(), hasSize(3));
        Iterator<String> sharedFlows = apiProxy.getSharedFlows().iterator();
        String sharedFlow = sharedFlows.next();
        assertThat(sharedFlow, is("sharedFlow1"));
        sharedFlow = sharedFlows.next();
        assertThat(sharedFlow, is("sharedFlow2"));
        sharedFlow = sharedFlows.next();
        assertThat(sharedFlow, is("sharedFlow3"));

        assertThat(apiProxy.getTargetServers(), notNullValue());
        assertThat(apiProxy.getTargetServers(), hasSize(3));
        Iterator<String> targetServers = apiProxy.getTargetServers().iterator();
        String targetServer = targetServers.next();
        assertThat(targetServer, is("targetServer1"));
        targetServer = targetServers.next();
        assertThat(targetServer, is("targetServer2"));
        targetServer = targetServers.next();
        assertThat(targetServer, is("targetServer3"));
    }

}
