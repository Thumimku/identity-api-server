/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
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

package org.wso2.carbon.identity.api.server.fetch.remote.common;

import org.wso2.carbon.identity.remotefetch.common.RemoteFetchComponentRegistry;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchConfigurationService;


/**
 * Service holder class for remote fetch configurations.
 */
public class RemoteFetchServiceHolder {

    private static RemoteFetchComponentRegistry remoteFetchComponentRegistry;
    private static RemoteFetchConfigurationService remoteFetchConfigurationService;

    /**
     * TODO
     * @return
     */
    public static RemoteFetchComponentRegistry getRemoteFetchComponentRegistry() {

        return remoteFetchComponentRegistry;
    }

    /**
     * TODO
     * @return
     */
    public static void setRemoteFetchComponentRegistry(RemoteFetchComponentRegistry remoteFetchComponentRegistry) {

        RemoteFetchServiceHolder.remoteFetchComponentRegistry = remoteFetchComponentRegistry;
    }

    /**
     * TODO
     * @return
     */
    public static RemoteFetchConfigurationService getRemoteFetchConfigurationService() {

        return remoteFetchConfigurationService;
    }

    /**
     * TODO
     * @return
     */
    public static void setRemoteFetchConfigurationService
            (RemoteFetchConfigurationService remoteFetchConfigurationService) {

        RemoteFetchServiceHolder.remoteFetchConfigurationService = remoteFetchConfigurationService;
    }
}
