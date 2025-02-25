package fr.groupbees.apigeesdk.apigee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {

  private Path path;

  private ResourceType type;

}
