package fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.quota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.commons.Ref;
import fr.groupbees.apigeesdk.apigee.commons.ValueRef;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JacksonXmlRootElement(localName = "Quota")
public class QuotaPolicy extends Policy {

  @JacksonXmlProperty(isAttribute = true, localName = "type")
  @JsonProperty(value = "type")
  private QuotaType type;

  @JacksonXmlProperty(localName = "Allow")
  @JsonProperty(value = "allow")
  private Allow allow;

  @JacksonXmlProperty(localName = "Interval")
  @JsonProperty(value = "interval")
  private ValueRef<Integer> interval = new ValueRef<>();

  @JacksonXmlProperty(localName = "TimeUnit")
  @JsonProperty(value = "timeUnit")
  private ValueRef<QuotaTimeUnit> timeUnit = new ValueRef<>();

  @JacksonXmlProperty(localName = "StartTime")
  @JsonProperty(value = "startTime")
  private LocalDateTime startTime;

  @JacksonXmlProperty(localName = "Distributed")
  @JsonProperty(value = "distributed")
  private Boolean distributed;

  @JacksonXmlProperty(localName = "Synchronous")
  @JsonProperty(value = "synchronous")
  private Boolean synchronous;

  @JacksonXmlProperty(localName = "AsynchronousConfiguration")
  @JsonProperty(value = "asynchronousConfiguration")
  private AsynchronousConfiguration asynchronousConfiguration;

  @JacksonXmlProperty(localName = "Identifier")
  @JsonProperty(value = "identifier")
  private Ref identifier;

  @JacksonXmlProperty(localName = "MessageWeight")
  @JsonProperty(value = "messageWeight")
  private Ref messageWeight;

  public void defaultValues() {
    this.defaultHeader();
    this.type = QuotaType.calendar;
    this.allow = new Allow(2000, null, null);
    this.distributed = false;
    this.synchronous = false;
    this.asynchronousConfiguration = new AsynchronousConfiguration(10, null);
  }

  public void setStartTime(String startTime) {
    this.startTime = LocalDateTime.parse(
      startTime,
      DateTimeFormatter.ofPattern("yyyy-M-d HH:mm:ss")
    );
  }

  public String getStartTime() {
    return this.startTime != null ? this.startTime.format(DateTimeFormatter.ofPattern(
      "yyyy-M-d HH:mm:ss")) : null;
  }

}
