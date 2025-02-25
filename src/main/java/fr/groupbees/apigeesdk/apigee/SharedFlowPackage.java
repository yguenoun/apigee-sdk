package fr.groupbees.apigeesdk.apigee;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.groupbees.apigeesdk.apigee.flows.SharedFlow;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import fr.groupbees.apigeesdk.mapper.XmlMapperFactory;
import fr.groupbees.apigeesdk.utils.FileUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

@Data
@NoArgsConstructor
public class SharedFlowPackage implements CommonPackage {

  private SharedFlowBundle sharedFlowBundle = new SharedFlowBundle();
  private LinkedHashSet<SharedFlow> sharedFlows = new LinkedHashSet<>();
  private LinkedHashSet<Policy> policies = new LinkedHashSet<>();
  private LinkedHashSet<Resource> resources = new LinkedHashSet<>();

  public void addSharedFlow(final SharedFlow sharedFlow) {
    sharedFlows.add(sharedFlow);
    sharedFlowBundle.addSharedFlow(sharedFlow);
  }

  @Override
  public void addPolicy(final Policy policy) {
    policies.add(policy);
    sharedFlowBundle.addPolicy(policy);
  }

  @Override
  public void addResource(final Resource resource) {
    resources.add(resource);
    sharedFlowBundle.addResource(resource);
  }

  public void bundlePackage(final String rootPath, final Boolean keepSrc) throws IOException {
    XmlMapper xmlMapper = XmlMapperFactory.getInstance();

    Path bundleRoot = Paths.get(rootPath);

    if (!Files.exists(bundleRoot)) {
      throw new InvalidPathException(rootPath, "Root path doesn't exist");
    }

    Path srcPath = bundleRoot.resolve(sharedFlowBundle.getName()).resolve("sharedflowbundle");

    Files.createDirectories(srcPath);

    if (!sharedFlows.isEmpty()) {
      Path proxiesPath = srcPath.resolve("sharedflows");
      Files.createDirectory(proxiesPath);
      for (SharedFlow p : sharedFlows) {
        Path proxyPath = Files.createFile(proxiesPath.resolve(p.getName() + ".xml"));
        xmlMapper.writeValue(proxyPath.toFile(), p);
      }
    }

    if (!policies.isEmpty()) {
      Path policiesPath = srcPath.resolve("policies");
      Files.createDirectory(policiesPath);
      for (Policy p : policies) {
        Path policyPath = Files.createFile(policiesPath.resolve(p.getName() + ".xml"));
        xmlMapper.writeValue(policyPath.toFile(), p);
      }
    }

    if (!resources.isEmpty()) {
      Path resourcesPath = srcPath.resolve("resources");
      Files.createDirectory(resourcesPath);
      for (Resource resource : resources) {
        Path resourcePath = resourcesPath.resolve(resource.getType().name());
        if (!Files.exists(resourcePath)) {
          Files.createDirectory(resourcePath);
        }
        Files.copy(resource.getPath(), resourcePath.resolve(resource.getPath().getFileName().toString()));
      }
    }

    Path apiProxyPath = Files.createFile(srcPath.resolve(sharedFlowBundle.getName() + ".xml"));
    xmlMapper.writeValue(apiProxyPath.toFile(), sharedFlowBundle);

    FileUtils.zipPackage(
      bundleRoot.resolve(sharedFlowBundle.getName() + ".zip"),
      srcPath.resolve("..").normalize(),
      "sharedflowbundle"
    );

    if (!keepSrc) {
      FileUtils.delete(srcPath.resolve("..").normalize());
    }
  }

}
