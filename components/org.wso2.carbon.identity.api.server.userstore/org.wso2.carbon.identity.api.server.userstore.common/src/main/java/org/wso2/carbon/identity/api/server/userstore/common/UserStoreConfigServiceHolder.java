/*
 * Copyright (c) 2019-2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.api.server.userstore.common;

import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.claim.metadata.mgt.ClaimMetadataManagementService;
import org.wso2.carbon.identity.user.store.configuration.UserStoreConfigService;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * Service holder class for User Store.
 */
public class UserStoreConfigServiceHolder {

    private UserStoreConfigServiceHolder() {}

    private static class UserStoreServiceHolder {

        static final UserStoreConfigService SERVICE = (UserStoreConfigService) PrivilegedCarbonContext
                .getThreadLocalCarbonContext().getOSGiService(UserStoreConfigService.class, null);
    }

    private static class RealmServiceHolder {

        static final RealmService SERVICE = (RealmService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(RealmService.class, null);
    }

    private static class ClaimMetadataManagementServiceHolder {

        static final ClaimMetadataManagementService SERVICE = (ClaimMetadataManagementService) PrivilegedCarbonContext
                .getThreadLocalCarbonContext().getOSGiService(ClaimMetadataManagementService.class, null);
    }

    /**
     * Get the UserStoreConfigService OSGi service.
     *
     * @return UserStoreConfigService.
     */
    public static UserStoreConfigService getUserStoreConfigService() {

        return UserStoreServiceHolder.SERVICE;
    }

    /**
     * Get the RealmService OSGi service.
     *
     * @return RealmService.
     */
    public static RealmService getRealmService() {

        return RealmServiceHolder.SERVICE;
    }

    /**
     * Get the ClaimMetadataManagementService OSGi service.
     *
     * @return ClaimMetadataManagementService.
     */
    public static ClaimMetadataManagementService getClaimMetadataManagementService() {

        return ClaimMetadataManagementServiceHolder.SERVICE;
    }
}
