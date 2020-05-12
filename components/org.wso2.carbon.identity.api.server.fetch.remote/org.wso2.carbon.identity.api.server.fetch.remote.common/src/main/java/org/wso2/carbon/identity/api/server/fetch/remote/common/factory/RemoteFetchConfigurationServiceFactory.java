package org.wso2.carbon.identity.api.server.fetch.remote.common.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchConfigurationService;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the RemoteFetchConfigurationService type of object inside the container.
 */
public class RemoteFetchConfigurationServiceFactory extends AbstractFactoryBean<RemoteFetchConfigurationService> {

    private RemoteFetchConfigurationService remoteFetchConfigurationService;

    @Override
    public Class<?> getObjectType() {

        return Object.class;
    }

    @Override
    protected RemoteFetchConfigurationService createInstance() throws Exception {

        if (this.remoteFetchConfigurationService == null) {
            RemoteFetchConfigurationService taskOperationService =
                    (RemoteFetchConfigurationService) PrivilegedCarbonContext.
                            getThreadLocalCarbonContext()
                            .getOSGiService(RemoteFetchConfigurationService.class, null);
            if (taskOperationService != null) {
                this.remoteFetchConfigurationService = taskOperationService;
            } else {
                throw new Exception("Unable to retrieve TemplateManager service.");
            }
        }
        return this.remoteFetchConfigurationService;
    }
}
