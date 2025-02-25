package fr.groupbees.apigeesdk.apigee.flows;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.NameValue;
import fr.groupbees.apigeesdk.apigee.flows.proxyendpoint.HTTPProxyConnection;
import fr.groupbees.apigeesdk.apigee.flows.proxyendpoint.PostClientFlow;
import fr.groupbees.apigeesdk.apigee.flows.proxyendpoint.ProxyEndpoint;
import fr.groupbees.apigeesdk.apigee.flows.proxyendpoint.RouteRule;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProxyEndpointTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/flows/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        ProxyEndpoint proxy = new ProxyEndpoint();
        proxy.setName("proxy");
        proxy.setDescription("proxy description");
        proxy.setDefaultFaultRule(new DefaultFaultRule());
        proxy.setFaultRules(new LinkedHashSet<>());
        proxy.setPreFlow(new Flow("pre-flow"));
        proxy.setPostFlow(new Flow("post-flow"));
        proxy.setPostClientFlow(
                new PostClientFlow(
                        "post-client-flow",
                        "post-client-flow-desc",
                        new Steps(
                                new LinkedHashSet<>(
                                        Arrays.asList(
                                                new Step("postcf-res-step1-name", "postcf-res-step1-cond"),
                                                new Step("postcf-res-step2-name")
                                        )
                                )
                        )
                )
        );
        proxy.setFlows(new LinkedHashSet<>());
        proxy.setHttpProxyConnection(
                new HTTPProxyConnection(
                        "/base-path",
                        Arrays.asList("default", "secure"),
                        Arrays.asList(
                                new NameValue("prop1-name", "prop1-val"),
                                new NameValue("prop2-name", "prop2-val")
                        )
                )
        );
        LinkedHashSet<RouteRule> routeRules = new LinkedHashSet<>(
                Arrays.asList(
                        new RouteRule(
                                "rr1-name",
                                "rr1-cond",
                                "rr1-target",
                                null
                        ),
                        new RouteRule(
                                "rr2-name",
                                "rr2-cond",
                                null,
                                "rr2-url"
                        ),
                        new RouteRule("rr3-name", null, null, null)
                )
        );
        proxy.setRouteRules(routeRules);

        String xml = xmlMapper.writeValueAsString(proxy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "ProxyEndpointTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "ProxyEndpointTests.yaml");

        ProxyEndpoint proxyEndpoint = ymlMapper.readValue(yml, ProxyEndpoint.class);

        assertThat(proxyEndpoint, notNullValue());
        assertThat(proxyEndpoint.getName(), is("proxy"));
        assertThat(proxyEndpoint.getDescription(), is("proxy description"));
        assertThat(proxyEndpoint.getPreFlow(), notNullValue());
        MatcherAssert.assertThat(proxyEndpoint.getPreFlow().getName(), is("pre-flow"));
        MatcherAssert.assertThat(proxyEndpoint.getPreFlow().getRequest(), notNullValue());
        MatcherAssert.assertThat(proxyEndpoint.getPreFlow().getResponse(), notNullValue());
        MatcherAssert.assertThat(proxyEndpoint.getPreFlow().getResponse(), notNullValue());
        assertThat(proxyEndpoint.getPostFlow(), notNullValue());
        MatcherAssert.assertThat(proxyEndpoint.getPostFlow().getName(), is("post-flow"));
        MatcherAssert.assertThat(proxyEndpoint.getPostFlow().getRequest(), notNullValue());
        MatcherAssert.assertThat(proxyEndpoint.getPostFlow().getResponse(), notNullValue());
        MatcherAssert.assertThat(proxyEndpoint.getPostFlow().getResponse(), notNullValue());
        assertThat(proxyEndpoint.getDefaultFaultRule(), notNullValue());
        assertThat(proxyEndpoint.getFaultRules(), notNullValue());
        assertThat(proxyEndpoint.getFlows(), notNullValue());

        PostClientFlow postClientFlow = proxyEndpoint.getPostClientFlow();
        assertThat(postClientFlow, notNullValue());
        assertThat(postClientFlow.getName(), is("post-client-flow"));
        assertThat(postClientFlow.getDescription(), is("post-client-flow-desc"));
        assertThat(postClientFlow.getResponse(), notNullValue());
        MatcherAssert.assertThat(postClientFlow.getResponse().getSteps(), notNullValue());
        MatcherAssert.assertThat(postClientFlow.getResponse().getSteps(), hasSize(2));

        Iterator<Step> steps = postClientFlow.getResponse().getSteps().iterator();
        Step step = steps.next();

        assertThat(step.getName(), is("postcf-res-step1-name"));
        assertThat(step.getCondition(), is("postcf-res-step1-cond"));
        step = steps.next();
        assertThat(step.getName(), is("postcf-res-step2-name"));
        assertThat(step.getCondition(), is("postcf-res-step2-cond"));
        assertThat(steps.hasNext(), is(false));

        HTTPProxyConnection httpProxyConnection = proxyEndpoint.getHttpProxyConnection();
        assertThat(httpProxyConnection, notNullValue());
        assertThat(httpProxyConnection.getBasePath(), is("/base-path"));
        assertThat(httpProxyConnection.getProperties(), notNullValue());
        MatcherAssert.assertThat(httpProxyConnection.getProperties().get(0).getName(), is("prop1-name"));
        MatcherAssert.assertThat(httpProxyConnection.getProperties().get(0).getValue(), is("prop1-value"));
        MatcherAssert.assertThat(httpProxyConnection.getProperties().get(1).getName(), is("prop2-name"));
        MatcherAssert.assertThat(httpProxyConnection.getProperties().get(1).getValue(), is("prop2-value"));
        assertThat(httpProxyConnection.getVirtualHosts(), notNullValue());
        assertThat(httpProxyConnection.getVirtualHosts(), hasSize(2));
        assertThat(httpProxyConnection.getVirtualHosts().get(0), is("default"));
        assertThat(httpProxyConnection.getVirtualHosts().get(1), is("secure"));

        assertThat(proxyEndpoint.getRouteRules(), notNullValue());
        assertThat(proxyEndpoint.getRouteRules(), hasSize(3));

        Iterator<RouteRule> routeRules = proxyEndpoint.getRouteRules().iterator();
        RouteRule routeRule = routeRules.next();

        assertThat(routeRule.getName(), is("rr1-name"));
        assertThat(routeRule.getCondition(), is("rr1-cond"));
        assertThat(routeRule.getTargetEndpoint(), is("rr1-target"));
        assertThat(routeRule.getUrl(), nullValue());
        routeRule = routeRules.next();
        assertThat(routeRule.getName(), is("rr2-name"));
        assertThat(routeRule.getCondition(), is("rr2-cond"));
        assertThat(routeRule.getTargetEndpoint(), nullValue());
        assertThat(routeRule.getUrl(), is("rr2-url"));
        routeRule = routeRules.next();
        assertThat(routeRule.getName(), is("rr3-name"));
        assertThat(routeRule.getCondition(), nullValue());
        assertThat(routeRule.getTargetEndpoint(), nullValue());
        assertThat(routeRule.getUrl(), nullValue());
        assertThat(routeRules.hasNext(), is(false));
    }

}
