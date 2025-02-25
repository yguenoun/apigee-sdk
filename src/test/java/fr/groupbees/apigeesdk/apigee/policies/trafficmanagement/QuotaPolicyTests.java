package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.quota.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QuotaPolicyTests {

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
        QuotaPolicy policy = new QuotaPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "QuotaPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        QuotaPolicy policy = new QuotaPolicy();
        policy.setType(QuotaType.rollingwindow);
        policy.setInterval(new ValueRef<>(10, "ref"));
        policy.setStartTime("2021-5-27 10:00:00");
        policy.setDistributed(true);
        policy.setSynchronous(true);
        policy.setSynchronous(true);
        policy.setAsynchronousConfiguration(new AsynchronousConfiguration(10, 20));
        policy.setIdentifier(new Ref("ref"));
        policy.setMessageWeight(new Ref("ref"));
        policy.setTimeUnit(new ValueRef<>(QuotaTimeUnit.week, "unit-ref"));

        Allow allow = new Allow();
        allow.setCount(10);
        allow.setCountRef("ref");

        ClassAllow classAllow = new ClassAllow("ref");

        AllowClass allowClass1 = new AllowClass("quota-class-1", 10);
        AllowClass allowClass2 = new AllowClass("quota-class-2", 20);

        classAllow.setAllows(Arrays.asList(allowClass1, allowClass2));
        allow.setClassAllow(classAllow);
        policy.setAllow(allow);

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "QuotaPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "QuotaPolicyTests.yaml");

        QuotaPolicy policy = ymlMapper.readValue(yml, QuotaPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getAllow(), notNullValue());
        assertThat(policy.getAllow().getCount(), is(10));
        assertThat(policy.getAllow().getCountRef(), is("ref"));
        assertThat(policy.getAllow().getClassAllow(), notNullValue());
        assertThat(policy.getAllow().getClassAllow().getRef(), is("ref"));
        assertThat(policy.getAllow().getClassAllow().getAllows(), notNullValue());
        assertThat(policy.getAllow().getClassAllow().getAllows(), hasSize(2));
        assertThat(policy.getAllow().getClassAllow().getAllows().get(0).getCount(), is(10));
        assertThat(policy.getAllow().getClassAllow().getAllows().get(0).getQuotaClass(), is("quota-class-1"));
        assertThat(policy.getAllow().getClassAllow().getAllows().get(1).getCount(), is(20));
        assertThat(policy.getAllow().getClassAllow().getAllows().get(1).getQuotaClass(), is("quota-class-2"));
        assertThat(policy.getAsynchronousConfiguration(), notNullValue());
        assertThat(policy.getAsynchronousConfiguration().getSyncIntervalInSeconds(), is(10));
        assertThat(policy.getAsynchronousConfiguration().getSyncMessageCount(), is(20));
        assertThat(policy.getDistributed(), is(true));
        assertThat(policy.getIdentifier(), notNullValue());
        MatcherAssert.assertThat(policy.getIdentifier().getRef(), is("ref"));
        assertThat(policy.getInterval(), notNullValue());
        MatcherAssert.assertThat(policy.getInterval().getRef(), is("ref"));
        MatcherAssert.assertThat(policy.getInterval().getValue(), is(10));
        assertThat(policy.getMessageWeight(), notNullValue());
        MatcherAssert.assertThat(policy.getMessageWeight().getRef(), is("ref"));
        assertThat(policy.getSynchronous(), is(true));
        assertThat(policy.getStartTime(), is("2021-5-27 10:00:00"));
        assertThat(policy.getTimeUnit(), notNullValue());
        MatcherAssert.assertThat(policy.getTimeUnit().getRef(), is("unit-ref"));
        MatcherAssert.assertThat(policy.getTimeUnit().getValue(), is(QuotaTimeUnit.week));
    }

}
