package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.mediation.kvm.*;
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

public class KeyValueOperationsPolicyTests {

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
        KeyValueMapOperationsPolicy policy = new KeyValueMapOperationsPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "KeyValueOperationsPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        KeyValueMapOperationsPolicy policy = new KeyValueMapOperationsPolicy();
        policy.setMapIdentifier("map");
        policy.setExpiryTimeInSecs(500);
        policy.setScope(KVMScope.apiproxy);
        policy.setExclusiveCache("deprecated");

        policy.setInitialEntries(
                Arrays.asList(
                        new Entry(
                                new Key(
                                        Collections.singletonList(new ValueRef<>("e1-param1", "e1-ref1"))
                                ),
                                Arrays.asList("e1-value1", "e1-value2")
                        ),
                        new Entry(
                                new Key(
                                        Arrays.asList(
                                                new ValueRef<>("e2-param1", "e2-ref1"),
                                                new ValueRef<>("e2-param2", "e2-ref2")
                                        )
                                ),
                                Arrays.asList("e2-value1", "e2-value2")
                        )
                )
        );

        policy.setDelete(
                Arrays.asList(
                        new Delete(
                                new Key(
                                        Arrays.asList(
                                                new ValueRef<>("del1-param1", "del1-ref1"),
                                                new ValueRef<>("del1-param2", "del1-ref2")
                                        )
                                )
                        ),
                        new Delete(
                                new Key(
                                        Collections.singletonList(
                                                new ValueRef<>("del2-param1", "del2-ref1")
                                        )
                                )
                        )
                )
        );

        policy.setGet(
                Arrays.asList(
                        new Get(
                                "get1-assign",
                                1,
                                new Key(
                                        Collections.singletonList(
                                                new ValueRef<>("get1-param1", "get1-ref1")
                                        )
                                )
                        ),
                        new Get(
                                "get2-assign",
                                2,
                                new Key(
                                        Arrays.asList(
                                                new ValueRef<>("get2-param1", "get2-ref1"),
                                                new ValueRef<>("get2-param2", "get2-ref2")
                                        )
                                )
                        )
                )
        );

        policy.setPut(
                Arrays.asList(
                        new Put(
                                true,
                                new Key(
                                        Arrays.asList(
                                                new ValueRef<>("put1-param1", "put1-key-ref1"),
                                                new ValueRef<>("put1-param2", "put1-key-ref2")
                                        )
                                ),
                                Arrays.asList(
                                        new ValueRef<>("put1-value1", "put1-value-ref1"),
                                        new ValueRef<>("put1-value2", "put1-value-ref2")
                                )
                        ),
                        new Put(
                                false,
                                new Key(
                                        Collections.singletonList(
                                                new ValueRef<>("put2-value1", "put2-key-ref1")
                                        )
                                ),
                                Collections.singletonList(
                                        new ValueRef<>("put2-value1", "put2-value-ref1")
                                )
                        )
                )
        );

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "KeyValueOperationsPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "KeyValueOperationsPolicyTests.yaml");

        KeyValueMapOperationsPolicy policy = ymlMapper.readValue(yml, KeyValueMapOperationsPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getMapIdentifier(), is("map"));
        assertThat(policy.getExpiryTimeInSecs(), is(500));
        assertThat(policy.getScope(), is(KVMScope.apiproxy));
        assertThat(policy.getExclusiveCache(), is("deprecated"));

        List<Entry> initialEntries = policy.getInitialEntries();
        assertThat(initialEntries, notNullValue());
        assertThat(initialEntries, hasSize(2));
        assertThat(initialEntries.get(0).getKey(), notNullValue());
        assertThat(initialEntries.get(0).getKey().getParameter(), notNullValue());
        assertThat(initialEntries.get(0).getKey().getParameter(), hasSize(1));
        assertThat(initialEntries.get(0).getKey().getParameter().get(0).getRef(), is("e1-ref1"));
        assertThat(initialEntries.get(0).getKey().getParameter().get(0).getValue(), is("e1-param1"));
        assertThat(initialEntries.get(0).getValues(), notNullValue());
        assertThat(initialEntries.get(0).getValues(), hasSize(2));
        assertThat(initialEntries.get(0).getValues().get(0), is("e1-value1"));
        assertThat(initialEntries.get(0).getValues().get(1), is("e1-value2"));
        assertThat(initialEntries.get(1).getKey(), notNullValue());
        assertThat(initialEntries.get(1).getKey().getParameter(), notNullValue());
        assertThat(initialEntries.get(1).getKey().getParameter(), hasSize(2));
        assertThat(initialEntries.get(1).getKey().getParameter().get(0).getRef(), is("e2-ref1"));
        assertThat(initialEntries.get(1).getKey().getParameter().get(0).getValue(), is("e2-param1"));
        assertThat(initialEntries.get(1).getKey().getParameter().get(1).getRef(), is("e2-ref2"));
        assertThat(initialEntries.get(1).getKey().getParameter().get(1).getValue(), is("e2-param2"));
        assertThat(initialEntries.get(1).getValues(), notNullValue());
        assertThat(initialEntries.get(1).getValues(), hasSize(2));
        assertThat(initialEntries.get(1).getValues().get(0), is("e2-value1"));
        assertThat(initialEntries.get(1).getValues().get(1), is("e2-value2"));

        List<Delete> deletes = policy.getDelete();
        assertThat(deletes, notNullValue());
        assertThat(deletes, hasSize(2));
        assertThat(deletes.get(0).getKey(), notNullValue());
        assertThat(deletes.get(0).getKey().getParameter(), notNullValue());
        assertThat(deletes.get(0).getKey().getParameter(), hasSize(2));
        assertThat(deletes.get(0).getKey().getParameter().get(0).getRef(), is("del1-ref1"));
        assertThat(deletes.get(0).getKey().getParameter().get(0).getValue(), is("del1-param1"));
        assertThat(deletes.get(0).getKey().getParameter().get(1).getRef(), is("del1-ref2"));
        assertThat(deletes.get(0).getKey().getParameter().get(1).getValue(), is("del1-param2"));
        assertThat(deletes.get(1).getKey(), notNullValue());
        assertThat(deletes.get(1).getKey().getParameter(), notNullValue());
        assertThat(deletes.get(1).getKey().getParameter(), hasSize(1));
        assertThat(deletes.get(1).getKey().getParameter().get(0).getRef(), is("del2-ref1"));
        assertThat(deletes.get(1).getKey().getParameter().get(0).getValue(), is("del2-param1"));

        List<Get> gets = policy.getGet();
        assertThat(gets, notNullValue());
        assertThat(gets, hasSize(2));
        assertThat(gets.get(0).getAssignTo(), is("get1-assign"));
        assertThat(gets.get(0).getIndex(), is(1));
        assertThat(gets.get(0).getKey(), notNullValue());
        assertThat(gets.get(0).getKey().getParameter(), notNullValue());
        assertThat(gets.get(0).getKey().getParameter(), hasSize(1));
        assertThat(gets.get(0).getKey().getParameter().get(0).getRef(), is("get1-ref1"));
        assertThat(gets.get(0).getKey().getParameter().get(0).getValue(), is("get1-param1"));
        assertThat(gets.get(1).getAssignTo(), is("get2-assign"));
        assertThat(gets.get(1).getIndex(), is(2));
        assertThat(gets.get(1).getKey(), notNullValue());
        assertThat(gets.get(1).getKey().getParameter(), notNullValue());
        assertThat(gets.get(1).getKey().getParameter(), hasSize(2));
        assertThat(gets.get(1).getKey().getParameter().get(0).getRef(), is("get2-ref1"));
        assertThat(gets.get(1).getKey().getParameter().get(0).getValue(), is("get2-param1"));
        assertThat(gets.get(1).getKey().getParameter().get(1).getRef(), is("get2-ref2"));
        assertThat(gets.get(1).getKey().getParameter().get(1).getValue(), is("get2-param2"));

        List<Put> puts = policy.getPut();
        assertThat(puts, notNullValue());
        assertThat(puts, hasSize(2));
        assertThat(puts.get(0).getOverride(), is(true));
        assertThat(puts.get(0).getKey(), notNullValue());
        assertThat(puts.get(0).getKey().getParameter(), notNullValue());
        assertThat(puts.get(0).getKey().getParameter(), hasSize(2));
        assertThat(puts.get(0).getKey().getParameter().get(0).getRef(), is("put1-key-ref1"));
        assertThat(puts.get(0).getKey().getParameter().get(0).getValue(), is("put1-param1"));
        assertThat(puts.get(0).getKey().getParameter().get(1).getRef(), is("put1-key-ref2"));
        assertThat(puts.get(0).getKey().getParameter().get(1).getValue(), is("put1-param2"));
        assertThat(puts.get(0).getValue(), notNullValue());
        MatcherAssert.assertThat(puts.get(0).getValue(), hasSize(2));
        MatcherAssert.assertThat(puts.get(0).getValue().get(0).getRef(), is("put1-value-ref1"));
        MatcherAssert.assertThat(puts.get(0).getValue().get(0).getValue(), is("put1-value1"));
        MatcherAssert.assertThat(puts.get(0).getValue().get(1).getRef(), is("put1-value-ref2"));
        MatcherAssert.assertThat(puts.get(0).getValue().get(1).getValue(), is("put1-value2"));
        assertThat(puts.get(1).getOverride(), is(false));
        assertThat(puts.get(1).getKey(), notNullValue());
        assertThat(puts.get(1).getKey().getParameter(), notNullValue());
        assertThat(puts.get(1).getKey().getParameter(), hasSize(1));
        assertThat(puts.get(1).getKey().getParameter().get(0).getRef(), is("put2-key-ref1"));
        assertThat(puts.get(1).getKey().getParameter().get(0).getValue(), is("put2-param1"));
        assertThat(puts.get(1).getValue(), notNullValue());
        MatcherAssert.assertThat(puts.get(1).getValue(), hasSize(1));
        MatcherAssert.assertThat(puts.get(1).getValue().get(0).getRef(), is("put2-value-ref1"));
        MatcherAssert.assertThat(puts.get(1).getValue().get(0).getValue(), is("put2-value1"));
    }

}
