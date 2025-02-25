package fr.groupbees.apigeesdk.apigee.policies.extension;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import fr.groupbees.apigeesdk.apigee.commons.SSLInfo;
import fr.groupbees.apigeesdk.apigee.policies.extension.messagelogging.*;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.mapper.YamlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class MessageLoggingPolicyTests {

    private static XmlMapper xmlMapper;
    private static YAMLMapper ymlMapper;
    private static String configFilePrefixe = "src/test/resources/data/apigee/policies/extension/";

    @BeforeAll
    public static void setup() {
        xmlMapper = XmlMapperFactory.getInstance();
        ymlMapper = YamlMapperFactory.getInstance();
    }

    @Test
    public void XML_Generation_DefaultValues() throws Exception {
        MessageLoggingPolicy policy = new MessageLoggingPolicy();
        policy.defaultValues();

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "MessageLoggingPolicyTests-defaultValues.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void XML_Generation_FullConfiguration() throws Exception {
        MessageLoggingPolicy policy = new MessageLoggingPolicy();
        policy.setBufferMessage(true);
        policy.setLogLevel(LogLevel.ALERT);

        File file = new File();
        file.setMessage("file-message");
        file.setFileName("file-name");

        FileRotationOptions options = new FileRotationOptions();
        options.setRotateFileOnStartup(false);
        options.setFileRotationType(FileRotationType.TIME_SIZE);
        options.setMaxFileSizeInMB(10);
        options.setRotationFrequency(new RotationFrequency("hour", 1));
        options.setMaxFilesToRetain(20);
        file.setFileRotationOptions(options);
        policy.setFile(file);

        Syslog syslog = new Syslog();
        syslog.setMessage("syslog-message");
        syslog.setHost("customhost");
        syslog.setPort(80443);
        syslog.setProtocol(SyslogProtocol.TCP);
        syslog.setFormatMessage(true);
        syslog.setPayloadOnly(true);
        syslog.setSslInfo(new SSLInfo());

        policy.setSyslog(syslog);

        String xml = xmlMapper.writeValueAsString(policy);

        String expected = FileUtils.readTestResource(configFilePrefixe, "MessageLoggingPolicyTests.xml");

        assertThat(xml, CompareMatcher.isIdenticalTo(expected).ignoreWhitespace());
    }

    @Test
    public void YAML_Parse_FullConfiguration() throws Exception {
        String yml = FileUtils.readTestResource(configFilePrefixe, "MessageLoggingPolicyTests.yaml");

        MessageLoggingPolicy policy = ymlMapper.readValue(yml, MessageLoggingPolicy.class);

        assertThat(policy, notNullValue());
        assertThat(policy.getBufferMessage(), is(true));
        assertThat(policy.getFile(), notNullValue());
        assertThat(policy.getFile().getFileName(), is("file-name"));
        assertThat(policy.getFile().getMessage(), is("file-message"));
        assertThat(policy.getFile().getFileRotationOptions(), notNullValue());
        assertThat(policy.getFile().getFileRotationOptions().getRotateFileOnStartup(), is(false));
        assertThat(policy.getFile().getFileRotationOptions().getFileRotationType(), is(FileRotationType.TIME_SIZE));
        assertThat(policy.getFile().getFileRotationOptions().getMaxFileSizeInMB(), is(10));
        assertThat(policy.getFile().getFileRotationOptions().getMaxFilesToRetain(), is(20));
        assertThat(policy.getFile().getFileRotationOptions().getRotationFrequency(), notNullValue());
        assertThat(policy.getFile().getFileRotationOptions().getRotationFrequency().getUnit(), is("hour"));
        assertThat(policy.getFile().getFileRotationOptions().getRotationFrequency().getValue(), is(1));
        assertThat(policy.getSyslog(), notNullValue());
        assertThat(policy.getSyslog().getFormatMessage(), is(true));
        assertThat(policy.getSyslog().getHost(), is("customhost"));
        assertThat(policy.getSyslog().getMessage(), is("syslog-message"));
        assertThat(policy.getSyslog().getPayloadOnly(), is(true));
        assertThat(policy.getSyslog().getPort(), is(80443));
        assertThat(policy.getSyslog().getProtocol(), is(SyslogProtocol.TCP));
        assertThat(policy.getSyslog().getSslInfo(), notNullValue());
        assertThat(policy.getLogLevel(), is(LogLevel.INFO));
    }

}
