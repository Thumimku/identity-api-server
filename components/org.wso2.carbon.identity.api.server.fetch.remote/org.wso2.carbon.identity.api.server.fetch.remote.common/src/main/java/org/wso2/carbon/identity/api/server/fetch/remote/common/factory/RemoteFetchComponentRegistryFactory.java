
package org.wso2.carbon.identity.api.server.fetch.remote.common.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchComponentRegistry;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the RemoteFetchComponentRegistry type of object inside the container.
 */
public class RemoteFetchComponentRegistryFactory extends AbstractFactoryBean<RemoteFetchComponentRegistry> {

    private RemoteFetchComponentRegistry remoteFetchComponentRegistry;

    @Override
    public Class<?> getObjectType() {

        return Object.class;
    }

    @Override
    protected RemoteFetchComponentRegistry createInstance() throws Exception {

        if (this.remoteFetchComponentRegistry == null) {
            RemoteFetchComponentRegistry taskOperationService = (RemoteFetchComponentRegistry) PrivilegedCarbonContext
                    .getThreadLocalCarbonContext().getOSGiService(RemoteFetchComponentRegistry.class, null);
            if (taskOperationService != null) {
                this.remoteFetchComponentRegistry = taskOperationService;
            } else {
                throw new Exception("Unable to retrieve TemplateManager service.");
            }
        }
        return this.remoteFetchComponentRegistry;
    }
}
