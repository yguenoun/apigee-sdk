package fr.groupbees.apigeesdk.apigee;

import fr.groupbees.apigeesdk.apigee.policies.Policy;

public interface CommonPackage {

  void addPolicy(final Policy policy);
  void addResource(final Resource resource);

}
