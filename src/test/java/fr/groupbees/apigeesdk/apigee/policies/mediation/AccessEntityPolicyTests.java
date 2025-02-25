package fr.groupbees.apigeesdk.apigee.policies.mediation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity.AccessEntityPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity.EntityIdentifier;
import fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity.EntityType;
import fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity.Identifier;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccessEntityPolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/mediation/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        AccessEntityPolicy policy = new AccessEntityPolicy();
        policy.setEntityType(new EntityType("type"));
        policy.setEntityIdentifier(new EntityIdentifier("ei-ref", "ei-type"));
        policy.setSecondaryIdentifier(new EntityIdentifier("si-ref", "si-type"));

        Identifier identifier1 = new Identifier(
                new EntityIdentifier("ei1-ref", "ei1-type"),
                new EntityIdentifier("si1-ref", "si1-type")
        );

        Identifier identifier2 = new Identifier(
                new EntityIdentifier("ei2-ref", "ei2-type"),
                new EntityIdentifier("si2-ref", "si2-type")
        );

        Identifier identifier3 = new Identifier(
                new EntityIdentifier("ei3-ref", "ei3-type"),
                new EntityIdentifier("si3-ref", "si3-type")
        );

        policy.setIdentifiers(Arrays.asList(identifier1, identifier2, identifier3));
        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "AccessEntityPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "AccessEntityPolicyTests.yaml");

        AccessEntityPolicy policy = ymlMapper.readValue(yml, AccessEntityPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getEntityType(), notNullValue());
        assertThat(policy.getEntityType().getValue(), is("type"));
        assertThat(policy.getEntityIdentifier(), notNullValue());
        assertThat(policy.getEntityIdentifier().getRef(), is("ei-ref"));
        assertThat(policy.getEntityIdentifier().getType(), is("ei-type"));
        assertThat(policy.getSecondaryIdentifier(), notNullValue());
        assertThat(policy.getSecondaryIdentifier().getRef(), is("si-ref"));
        assertThat(policy.getSecondaryIdentifier().getType(), is("si-type"));

        List<Identifier> identifiers = policy.getIdentifiers();
        assertThat(identifiers, notNullValue());
        assertThat(identifiers, hasSize(3));
        assertThat(identifiers.get(0).getEntityIdentifier(), notNullValue());
        assertThat(identifiers.get(0).getEntityIdentifier().getRef(), is("ei1-ref"));
        assertThat(identifiers.get(0).getEntityIdentifier().getType(), is("ei1-type"));
        assertThat(identifiers.get(0).getSecondaryIdentifier(), notNullValue());
        assertThat(identifiers.get(0).getSecondaryIdentifier().getRef(), is("si1-ref"));
        assertThat(identifiers.get(0).getSecondaryIdentifier().getType(), is("si1-type"));
        assertThat(identifiers.get(1).getEntityIdentifier(), notNullValue());
        assertThat(identifiers.get(1).getEntityIdentifier().getRef(), is("ei2-ref"));
        assertThat(identifiers.get(1).getEntityIdentifier().getType(), is("ei2-type"));
        assertThat(identifiers.get(1).getSecondaryIdentifier(), notNullValue());
        assertThat(identifiers.get(1).getSecondaryIdentifier().getRef(), is("si2-ref"));
        assertThat(identifiers.get(1).getSecondaryIdentifier().getType(), is("si2-type"));
        assertThat(identifiers.get(2).getEntityIdentifier(), notNullValue());
        assertThat(identifiers.get(2).getEntityIdentifier().getRef(), is("ei3-ref"));
        assertThat(identifiers.get(2).getEntityIdentifier().getType(), is("ei3-type"));
        assertThat(identifiers.get(2).getSecondaryIdentifier(), notNullValue());
        assertThat(identifiers.get(2).getSecondaryIdentifier().getRef(), is("si3-ref"));
        assertThat(identifiers.get(2).getSecondaryIdentifier().getType(), is("si3-type"));
    }

}
