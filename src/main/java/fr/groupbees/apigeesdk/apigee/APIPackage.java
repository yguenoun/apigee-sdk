package fr.groupbees.apigeesdk.apigee;

import fr.groupbees.apigeesdk.apigee.flows.proxyendpoint.ProxyEndpoint;
import fr.groupbees.apigeesdk.apigee.flows.targetendpoint.TargetEndpoint;
import fr.groupbees.apigeesdk.apigee.policies.Policy;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;

@Data
@NoArgsConstructor
public class APIPackage implements CommonPackage {

    private APIProxy apiProxy = new APIProxy();
    private LinkedHashSet<ProxyEndpoint> proxyEndpoints = new LinkedHashSet<>();
    private LinkedHashSet<TargetEndpoint> targetEndpoints = new LinkedHashSet<>();
    private LinkedHashSet<Policy> policies = new LinkedHashSet<>();
    private LinkedHashSet<Resource> resources = new LinkedHashSet<>();

    public APIPackage addProxyEndpoint(final ProxyEndpoint proxyEndpoint) {
        proxyEndpoints.add(proxyEndpoint);
        apiProxy.addProxyEndpoint(proxyEndpoint);
        return this;
    }

    public APIPackage addTargetEndpoint(final TargetEndpoint targetEndpoint) {
        targetEndpoints.add(targetEndpoint);
        apiProxy.addTargetEndpoint(targetEndpoint);
        return this;
    }

    @Override
    public void addPolicy(final Policy policy) {
        policies.add(policy);
        apiProxy.addPolicy(policy);
    }

    @Override
    public void addResource(final Resource resource) {
        resources.add(resource);
        apiProxy.addResource(resource);
    }
}
