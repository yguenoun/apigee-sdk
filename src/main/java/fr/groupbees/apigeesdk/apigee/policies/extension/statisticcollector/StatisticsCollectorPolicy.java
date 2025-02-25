package fr.groupbees.apigeesdk.apigee.policies.extension.statisticcollector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "StatisticsCollector")
public class StatisticsCollectorPolicy extends Policy {

  @JacksonXmlElementWrapper(localName = "Statistics")
  @JacksonXmlProperty(localName = "Statistic")
  @JsonProperty(value = "statistics")
  private List<Statistic> statistics;

  @Override
  public void defaultValues() {
    this.defaultHeader();
  }

}
