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

package org.wso2.carbon.identity.api.server.fetch.remote.v1.core;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.api.server.common.ContextLoader;
import org.wso2.carbon.identity.api.server.common.error.APIError;
import org.wso2.carbon.identity.api.server.common.error.ErrorResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.common.RemoteFetchConfigurationConstants;
import org.wso2.carbon.identity.api.server.fetch.remote.common.RemoteFetchServiceHolder;


import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.ActionListenerAttributes;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.AllComponentUIFieldsResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.ComponentUIFieldsResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationGetResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationListItem;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationListResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationPOSTRequest;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationPatchRequest;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RepositoryManagerAttributes;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.StatusListItem;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.StatusListResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.UIFieldResponse;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
import org.wso2.carbon.identity.remotefetch.common.BasicRemoteFetchConfiguration;
import org.wso2.carbon.identity.remotefetch.common.DeploymentRevision;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchComponent;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchConfiguration;
import org.wso2.carbon.identity.remotefetch.common.ValidationReport;
import org.wso2.carbon.identity.remotefetch.common.actionlistener.ActionListenerComponent;
import org.wso2.carbon.identity.remotefetch.common.configdeployer.ConfigDeployerComponent;
import org.wso2.carbon.identity.remotefetch.common.exceptions.RemoteFetchClientException;
import org.wso2.carbon.identity.remotefetch.common.exceptions.RemoteFetchCoreException;
import org.wso2.carbon.identity.remotefetch.common.exceptions.RemoteFetchServerException;
import org.wso2.carbon.identity.remotefetch.common.repomanager.RepositoryManagerComponent;
import org.wso2.carbon.identity.remotefetch.common.ui.UIField;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.api.server.fetch.remote.v1.core.RemoteFetchUtils.setIfNotNull;

/**
 * Call internal osgi services to perform server remote fetch configuration related operations.
 */
public class ServerRemoteFetchConfigManagementService {

    private static final Log log = LogFactory.getLog(ServerRemoteFetchConfigManagementService.class);

    /**
     * Get list of remote fetch configurations.
     *
     * @param limit  Items per page.
     * @param offset Offset.
     * @return RemoteFetchConfigurationListResponse.
     */
    public RemoteFetchConfigurationListResponse getRemoteFetchConfigs(Integer limit, Integer offset) {

        OptionalInt optionalIntLimit;
        OptionalInt optionalIntOffset;

        try {
            if (limit == null) {
                optionalIntLimit = OptionalInt.empty();
            } else {
                optionalIntLimit = OptionalInt.of(limit);
            }
            if (offset == null) {
                optionalIntOffset = OptionalInt.empty();
            } else {
                optionalIntOffset = OptionalInt.of(offset);
            }
            return createRemoteFetchConfigurationListResponse(RemoteFetchServiceHolder.
                    getRemoteFetchConfigurationService()
                    .getBasicRemoteFetchConfigurationList(optionalIntLimit, optionalIntOffset));
        } catch (RemoteFetchCoreException e) {
            throw handleRemoteFetchConfigurationException(e, RemoteFetchConfigurationConstants.
                    ErrorMessage.ERROR_CODE_ERROR_LISTING_RF_CONFIGS, null);
        }
    }

    /**
     * Delete an Remote fetch configuration.
     *
     * @param remoteFetchConfigurationId Identity Provider resource ID.
     */
    public void deleteRemoteFetchConfig(String remoteFetchConfigurationId) {

        try {
            RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                    .deleteRemoteFetchConfiguration(remoteFetchConfigurationId);

        } catch (RemoteFetchCoreException e) {
            throw handleRemoteFetchConfigurationException(e, RemoteFetchConfigurationConstants.
                            ErrorMessage.ERROR_CODE_ERROR_DELETING_RF_CONFIGS,
                    remoteFetchConfigurationId);
        }
    }

    /**
     * Get remote fetch configuration by resource Id.
     *
     * @param remoteFetchConfigurationId resource Id.
     * @return RemoteFetchConfigurationListResponse.
     */
    public RemoteFetchConfigurationGetResponse getRemoteFetchConfig(String remoteFetchConfigurationId) {

        try {
            RemoteFetchConfiguration remoteFetchConfiguration =
                    RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                            .getRemoteFetchConfiguration(remoteFetchConfigurationId);

            if (remoteFetchConfiguration == null) {
                throw handleException(Response.Status.NOT_FOUND, RemoteFetchConfigurationConstants.
                                ErrorMessage.ERROR_CODE_RE_CONFIG_NOT_FOUND,
                        remoteFetchConfigurationId);
            }
            return createRemoteFetchConfigurationResponse(remoteFetchConfiguration);

        } catch (RemoteFetchCoreException e) {
            throw handleRemoteFetchConfigurationException(e, RemoteFetchConfigurationConstants.
                    ErrorMessage.ERROR_CODE_ERROR_RETRIEVING_RF_CONFIG, null);
        }
    }

    /**
     * Update remote fetch configuration by resource id.
     *
     * @param id                                   Id.
     * @param remoteFetchConfigurationPatchRequest
     */
    public void updateRemoteFetchConfig(String id,
                                        RemoteFetchConfigurationPatchRequest remoteFetchConfigurationPatchRequest) {

        try {
            RemoteFetchConfiguration remoteFetchConfiguration =
                    RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                            .getRemoteFetchConfiguration(id);

            if (remoteFetchConfiguration == null) {
                throw handleException(Response.Status.NOT_FOUND, RemoteFetchConfigurationConstants.
                        ErrorMessage.ERROR_CODE_RE_CONFIG_NOT_FOUND, id);
            }

            RemoteFetchConfiguration remoteFetchConfigurationToUpdate = deepCopyRemoteFetchConfiguration
                    (id, remoteFetchConfiguration);

            setIfNotNull(remoteFetchConfigurationPatchRequest.getIsEnabled(),
                    remoteFetchConfigurationToUpdate::setEnabled);

            setIfNotNull(remoteFetchConfigurationPatchRequest.getRemoteFetchName(),
                    remoteFetchConfigurationToUpdate::setRemoteFetchName);

            RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                    .updateRemoteFetchConfiguration(remoteFetchConfigurationToUpdate);


        } catch (RemoteFetchCoreException e) {
            throw handleRemoteFetchConfigurationException(e, RemoteFetchConfigurationConstants.ErrorMessage.
                    ERROR_CODE_ERROR_UPDATING_RF_CONFIG, null);
        }

    }

    /**
     * Trigger remote fetch.
     *
     * @param remoteFetchConfigurationId
     * @return
     */
    public String triggerRemoteFetch(String remoteFetchConfigurationId) {

        try {
            RemoteFetchConfiguration remoteFetchConfiguration =
                    RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                            .getRemoteFetchConfiguration(remoteFetchConfigurationId);

            if (remoteFetchConfiguration == null) {
                throw handleException(Response.Status.NOT_FOUND, RemoteFetchConfigurationConstants.
                        ErrorMessage.ERROR_CODE_RE_CONFIG_NOT_FOUND, remoteFetchConfigurationId);
            } else {
                RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                        .triggerRemoteFetch(remoteFetchConfiguration);

                return remoteFetchConfiguration.getRemoteFetchConfigurationId();
            }
        } catch (RemoteFetchCoreException e) {
            throw handleException(Response.Status.INTERNAL_SERVER_ERROR, RemoteFetchConfigurationConstants.ErrorMessage
                    .ERROR_CODE_ERROR_TRIGGER_REMOTE_FETCH, remoteFetchConfigurationId);
        }


    }

    /**
     * Add remote fetch configuration.
     *
     * @param remoteFetchConfigurationPOSTRequest remoteFetchConfigurationPOSTRequest
     * @return resource id.
     */
    public String addRemoteFetchConfiguration(RemoteFetchConfigurationPOSTRequest remoteFetchConfigurationPOSTRequest) {

        ValidationReport validationReport;
        try {
            validationReport = RemoteFetchServiceHolder.getRemoteFetchConfigurationService().
                    addRemoteFetchConfiguration(
                            createRemoteFetchConfiguration(remoteFetchConfigurationPOSTRequest));
        } catch (RemoteFetchCoreException e) {
            throw handleRemoteFetchConfigurationException(e, RemoteFetchConfigurationConstants.ErrorMessage.
                    ERROR_CODE_ERROR_ADDING_RF_CONFIG, null);
        }

        return validationReport.getId();

    }

    /**
     * get status of remote fetch.
     * Not Implemented yet.
     *
     * @param remoteFetchConfigurationId
     */
    public StatusListResponse getStatus(String remoteFetchConfigurationId)  {

        try {
        RemoteFetchConfiguration remoteFetchConfiguration =
                RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                        .getRemoteFetchConfiguration(remoteFetchConfigurationId);

        if (remoteFetchConfiguration == null) {
            throw handleException(Response.Status.NOT_FOUND, RemoteFetchConfigurationConstants.
                    ErrorMessage.ERROR_CODE_RE_CONFIG_NOT_FOUND, remoteFetchConfigurationId);
        } else {

            return createStatusListResponse(RemoteFetchServiceHolder.getRemoteFetchConfigurationService()
                    .getDeploymentRevisions(remoteFetchConfigurationId));
            }
        } catch (RemoteFetchCoreException e) {
            throw handleException(Response.Status.INTERNAL_SERVER_ERROR, RemoteFetchConfigurationConstants.ErrorMessage
                    .ERROR_CODE_ERROR_STATUS_REMOTE_FETCH, remoteFetchConfigurationId);
        }


    }

    /**
     * This method is used to create status list from list of deployment revisions.
     * This method uses java stream api to count successful deployments and failed deployments.
     * @param deploymentRevisions List of deployment revisions.
     * @return
     */
    private StatusListResponse createStatusListResponse(List<DeploymentRevision> deploymentRevisions) {

        StatusListResponse statusListResponse = new StatusListResponse();
        if (CollectionUtils.isNotEmpty(deploymentRevisions)) {
            List<StatusListItem> statusListItemList = new ArrayList<>();
            for (DeploymentRevision deploymentRevision : deploymentRevisions) {
                StatusListItem statusListItem = populateDeploymentRevision(deploymentRevision);
                statusListItemList.add(statusListItem);
            }
            statusListResponse.setRemoteFetchRevisionStatuses(statusListItemList);
            statusListResponse.setSuccessfulDeployments(
                    (int) deploymentRevisions.stream().filter(deploymentRevision ->
                            deploymentRevision.getDeploymentStatus().name()
                                    .equals(RemoteFetchConfigurationConstants.DEPLOYED)).count());

            statusListResponse.setFailedDeployments(
                    (int) deploymentRevisions.stream().filter(deploymentRevision ->
                            deploymentRevision.getDeploymentStatus().name()
                                    .equals(RemoteFetchConfigurationConstants.ERROR_DEPLOYING)).count());
        } else {
            statusListResponse.setCount(0);
        }
        return statusListResponse;
    }

    /**
     * Populate deployment revision to get status list item.
     * @param deploymentRevision
     * @return
     */
    private StatusListItem populateDeploymentRevision(DeploymentRevision deploymentRevision) {

        StatusListItem statusListItem = new StatusListItem();
        statusListItem.setDeployedStatus(deploymentRevision.getDeploymentStatus().name());
        statusListItem.setDeployedTime(deploymentRevision.getDeployedDate().toString());
        statusListItem.setItemName(deploymentRevision.getItemName());
        statusListItem.setDeploymentErrorReport(deploymentRevision.getErrorMessage());
        return statusListItem;
    }


    /**
     * This method is used to get action Listener component UI Fields, repository manager Component UI Fields and config
     * deployer component UI Fields.
     *
     */
    public AllComponentUIFieldsResponse getAllComponentUIFields() {

        List<ComponentUIFieldsResponse> actionListenerComponentUIFieldsResponse =
                createActionListenerComponentUIFieldsResponse(RemoteFetchServiceHolder
                        .getRemoteFetchComponentRegistry()
                        .getActionListenerComponentList());

        List<ComponentUIFieldsResponse> repositoryManagerComponentUIFieldsResponse =
                createRepositoryManagerComponentUIFieldsResponse(RemoteFetchServiceHolder
                        .getRemoteFetchComponentRegistry()
                        .getRepositoryManagerComponentList());

        List<ComponentUIFieldsResponse> configDeployerComponentUIFieldsResponse =
                createConfigDeployerComponentUIFieldsResponse(RemoteFetchServiceHolder
                        .getRemoteFetchComponentRegistry()
                        .getConfigDeployerComponentList());


        AllComponentUIFieldsResponse allComponentUIFieldsResponse = new AllComponentUIFieldsResponse();
        allComponentUIFieldsResponse.setActionListener(actionListenerComponentUIFieldsResponse);
        allComponentUIFieldsResponse.setRepositoryManager(repositoryManagerComponentUIFieldsResponse);
        allComponentUIFieldsResponse.setConfigDeployer(configDeployerComponentUIFieldsResponse);

        return allComponentUIFieldsResponse;
    }

    /**
     * This method is used to create config deployer component UI Field response.
     * @param configDeployerComponentList
     * @return
     */
    private List<ComponentUIFieldsResponse> createConfigDeployerComponentUIFieldsResponse
            (List<ConfigDeployerComponent> configDeployerComponentList) {

        if (CollectionUtils.isNotEmpty(configDeployerComponentList)) {

            List<ComponentUIFieldsResponse> actionListenerComponentUIFieldsResponses = new ArrayList<>();
            for (RemoteFetchComponent remoteFetchComponent : configDeployerComponentList) {
                ComponentUIFieldsResponse remoteFetchComponentUIFieldsResponse =
                        populateComponentUIFieldsResponse(remoteFetchComponent);
                actionListenerComponentUIFieldsResponses.add(remoteFetchComponentUIFieldsResponse);
            }
            return actionListenerComponentUIFieldsResponses;

        }
        return null;
    }

    /**
     * This method is used to create repository manager component UI Field response.
     * @param repositoryManagerComponentList
     * @return
     */
    private List<ComponentUIFieldsResponse> createRepositoryManagerComponentUIFieldsResponse
            (List<RepositoryManagerComponent> repositoryManagerComponentList) {

        if (CollectionUtils.isNotEmpty(repositoryManagerComponentList)) {

            List<ComponentUIFieldsResponse> actionListenerComponentUIFieldsResponses = new ArrayList<>();
            for (RemoteFetchComponent remoteFetchComponent : repositoryManagerComponentList) {
                ComponentUIFieldsResponse remoteFetchComponentUIFieldsResponse =
                        populateComponentUIFieldsResponse(remoteFetchComponent);
                actionListenerComponentUIFieldsResponses.add(remoteFetchComponentUIFieldsResponse);
            }
            return actionListenerComponentUIFieldsResponses;

        }
        return null;

    }

    /**
     * This method is used to create action listener component UI Field response.
     * @param actionListenerComponentsList
     * @return
     */
    private List<ComponentUIFieldsResponse> createActionListenerComponentUIFieldsResponse
            (List<ActionListenerComponent> actionListenerComponentsList) {

        if (CollectionUtils.isNotEmpty(actionListenerComponentsList)) {

            List<ComponentUIFieldsResponse> actionListenerComponentUIFieldsResponses = new ArrayList<>();
            for (RemoteFetchComponent remoteFetchComponent : actionListenerComponentsList) {
                ComponentUIFieldsResponse remoteFetchComponentUIFieldsResponse =
                        populateComponentUIFieldsResponse(remoteFetchComponent);
                actionListenerComponentUIFieldsResponses.add(remoteFetchComponentUIFieldsResponse);
            }
            return actionListenerComponentUIFieldsResponses;

        }
        return null;
    }

    /**
     * Create UI field reponse from remote fetch component.
     * @param remoteFetchComponent
     * @return
     */
    private ComponentUIFieldsResponse populateComponentUIFieldsResponse
            (RemoteFetchComponent remoteFetchComponent) {

        ComponentUIFieldsResponse componentUIFieldsResponse = new ComponentUIFieldsResponse();
        setIfNotNull(remoteFetchComponent.getIdentifier(), componentUIFieldsResponse::setIdentifier);

        componentUIFieldsResponse.setUiFields(populateUIFieldResponseList(remoteFetchComponent.getUIFields()));

        return componentUIFieldsResponse;
    }

    /**
     * Create list of UI Field response List from list of UI Fields.
     * @param uiFields
     * @return
     */
    private List<UIFieldResponse> populateUIFieldResponseList(List<UIField> uiFields) {

        if (CollectionUtils.isNotEmpty(uiFields)) {
            List<UIFieldResponse> uiFieldResponseList = new ArrayList<>();
            for (UIField uiField : uiFields) {

                UIFieldResponse uiFieldResponse = populateUIFieldResponse(uiField);
                uiFieldResponseList.add(uiFieldResponse);
            }
            return uiFieldResponseList;
        }
        return null;
    }

    /**
     * Create UI Field response from domain object uiField.
     * @param uiField
     * @return
     */
    private UIFieldResponse populateUIFieldResponse(UIField uiField) {

        UIFieldResponse uiFieldResponse = new UIFieldResponse();

        setIfNotNull(uiField.getId(), uiFieldResponse::setId);
        if (uiField.getType() != null) {
            setIfNotNull(uiField.getType().toString(), uiFieldResponse::setType);
        }
        setIfNotNull(uiField.getDisplayName(), uiFieldResponse::setDisplayName);
        setIfNotNull(uiField.getHelpText(), uiFieldResponse::setHelpText);
        setIfNotNull(uiField.getValidationRegex(), uiFieldResponse::setValidationRegex);
        setIfNotNull(uiField.getDefaultValues(), uiFieldResponse::setDefaultValues);
        setIfNotNull(uiField.isMultiValue(), uiFieldResponse::setIsMultiValue);
        setIfNotNull(uiField.isMandatory(), uiFieldResponse::setIsMandatory);
        setIfNotNull(uiField.isSensitive(), uiFieldResponse::isSensitive);

        return uiFieldResponse;
    }


    /**
     * This method is used to create remote fetch configuration object from POST request.
     * First it creates three hash map from post request.
     * Then it set primitive attributes from post request remote fetch configuration.
     *
     * @param remoteFetchConfigurationPOSTRequest POST request.
     * @return
     */
    private RemoteFetchConfiguration createRemoteFetchConfiguration
    (RemoteFetchConfigurationPOSTRequest remoteFetchConfigurationPOSTRequest) {

        RemoteFetchConfiguration remoteFetchConfiguration = new RemoteFetchConfiguration();
        Map<String, String> repositoryManagerAttributes =
                createRepositoryManagerProperties(remoteFetchConfigurationPOSTRequest);
        Map<String, String> actionListenerAttributes =
                createActionListenerProperties(remoteFetchConfigurationPOSTRequest);
        Map<String, String> configurationDeployerAttributes =
                createConfigDeployerAttributes(remoteFetchConfigurationPOSTRequest);

        setIfNotNull(remoteFetchConfigurationPOSTRequest.getIsEnabled(), remoteFetchConfiguration::setEnabled);
        setIfNotNull(remoteFetchConfigurationPOSTRequest.getRemoteFetchName(),
                remoteFetchConfiguration::setRemoteFetchName);

        remoteFetchConfiguration.setTenantId(IdentityTenantUtil
                .getTenantId(ContextLoader.getTenantDomainFromContext()));


        remoteFetchConfiguration.setConfigurationDeployerType(RemoteFetchConfigurationConstants.TYPE_CONFIG_DEPLOYER);
        remoteFetchConfiguration.setActionListenerType(RemoteFetchConfigurationConstants.TYPE_ACTION_LISTENER);
        remoteFetchConfiguration.setRepositoryManagerType(RemoteFetchConfigurationConstants.TYPE_REPOSITORY_MANAGER);

        remoteFetchConfiguration.setActionListenerAttributes(actionListenerAttributes);
        remoteFetchConfiguration.setRepositoryManagerAttributes(repositoryManagerAttributes);
        remoteFetchConfiguration.setConfigurationDeployerAttributes(configurationDeployerAttributes);
        return remoteFetchConfiguration;

    }

    /**
     * This method is used to create ConfigDeployer hash map from POST request.
     *
     * @param remoteFetchConfigurationPOSTRequest POST request.
     * @return
     */
    private Map<String, String> createConfigDeployerAttributes
    (RemoteFetchConfigurationPOSTRequest remoteFetchConfigurationPOSTRequest) {

        Map<String, String> properties = new HashMap<>();
        return properties;
    }

    /**
     * This method is used to create action listener hash map from POST request.
     *
     * @param remoteFetchConfigurationPOSTRequest
     * @return
     */
    private Map<String, String> createActionListenerProperties
    (RemoteFetchConfigurationPOSTRequest remoteFetchConfigurationPOSTRequest) {

        Map<String, String> properties = new HashMap<>();
        ActionListenerAttributes actionListenerAttributes =
                remoteFetchConfigurationPOSTRequest.getActionListenerAttributes();
        if (!StringUtils.isEmpty(actionListenerAttributes.getFrequency())) {
            properties.put(RemoteFetchConfigurationConstants.FREQUENCY, actionListenerAttributes.getFrequency());
        }

        return properties;

    }

    /**
     * This method is used to create Repository manager hash map from post request.
     *
     * @param remoteFetchConfigurationPOSTRequest POST request.
     * @return
     */
    private Map<String, String> createRepositoryManagerProperties
    (RemoteFetchConfigurationPOSTRequest remoteFetchConfigurationPOSTRequest) {

        Map<String, String> properties = new HashMap<>();
        RepositoryManagerAttributes repositoryManagerAttributes =
                remoteFetchConfigurationPOSTRequest.getRepositoryManagerAttributes();
        if (!StringUtils.isEmpty(repositoryManagerAttributes.getAccessToken())) {
            properties.put(RemoteFetchConfigurationConstants.ACCESS_TOKEN,
                    repositoryManagerAttributes.getAccessToken());
        }
        if (!StringUtils.isEmpty(repositoryManagerAttributes.getBranch())) {
            properties.put(RemoteFetchConfigurationConstants.BRANCH, repositoryManagerAttributes.getBranch());
        }
        if (!StringUtils.isEmpty(repositoryManagerAttributes.getDirectory())) {
            properties.put(RemoteFetchConfigurationConstants.DIRECTORY, repositoryManagerAttributes.getDirectory());
        }
        if (!StringUtils.isEmpty(repositoryManagerAttributes.getUri())) {
            properties.put(RemoteFetchConfigurationConstants.URI, repositoryManagerAttributes.getUri());
        }
        if (!StringUtils.isEmpty(repositoryManagerAttributes.getUserName())) {
            properties.put(RemoteFetchConfigurationConstants.USER_NAME, repositoryManagerAttributes.getUserName());
        }
        return properties;
    }

    /**
     * This method is used to create GET response from remote fetch configuration id.
     *
     * @param remoteFetchConfiguration remote fetch configuration domain object.
     * @return
     */
    private RemoteFetchConfigurationGetResponse createRemoteFetchConfigurationResponse
    (RemoteFetchConfiguration remoteFetchConfiguration) {

        RemoteFetchConfigurationGetResponse remoteFetchConfigurationGetResponse
                = new RemoteFetchConfigurationGetResponse();
        ActionListenerAttributes actionListenerAttributes = createActionListenerAttributeProperties
                (remoteFetchConfiguration);

        RepositoryManagerAttributes repositoryManagerAttributes = createRepositoryManagerAttributeProperties
                (remoteFetchConfiguration);

        remoteFetchConfigurationGetResponse.setActionListenerAttributes(actionListenerAttributes);
        remoteFetchConfigurationGetResponse.setRepositoryManagerAttributes(repositoryManagerAttributes);
        remoteFetchConfigurationGetResponse.setConfigurationDeployerAttributes(null);

        setIfNotNull(remoteFetchConfiguration.getRemoteFetchConfigurationId(),
                remoteFetchConfigurationGetResponse::setId);
        setIfNotNull(remoteFetchConfiguration.isEnabled(),
                remoteFetchConfigurationGetResponse::setIsEnabled);
        setIfNotNull(remoteFetchConfiguration.getRemoteFetchName(),
                remoteFetchConfigurationGetResponse::setRemoteFetchName);
        setIfNotNull(remoteFetchConfiguration.getRepositoryManagerType(),
                remoteFetchConfigurationGetResponse::setRepositoryManagerType);
        setIfNotNull(remoteFetchConfiguration.getConfigurationDeployerType(),
                remoteFetchConfigurationGetResponse::setConfigurationDeployerType);
        setIfNotNull(remoteFetchConfiguration.getRepositoryManagerType(),
                remoteFetchConfigurationGetResponse::setRepositoryManagerType);

        return remoteFetchConfigurationGetResponse;
    }

    /**
     * This method used to create Action Listener attributes from domain object.
     *
     * @param remoteFetchConfiguration
     * @return
     */
    private ActionListenerAttributes createActionListenerAttributeProperties
    (RemoteFetchConfiguration remoteFetchConfiguration) {

        ActionListenerAttributes actionListenerAttributes = new ActionListenerAttributes();
        setIfNotNull(remoteFetchConfiguration
                        .getActionListenerAttributes().get(RemoteFetchConfigurationConstants.FREQUENCY),
                actionListenerAttributes::setFrequency);
        return actionListenerAttributes;

    }

    /**
     * This method is used to create Repository Manager Attributes from domain object.
     *
     * @param remoteFetchConfiguration
     * @return
     */
    private RepositoryManagerAttributes createRepositoryManagerAttributeProperties
    (RemoteFetchConfiguration remoteFetchConfiguration) {

        RepositoryManagerAttributes repositoryManagerAttributes = new RepositoryManagerAttributes();

        setIfNotNull((remoteFetchConfiguration
                        .getRepositoryManagerAttributes().get(RemoteFetchConfigurationConstants.ACCESS_TOKEN)),
                repositoryManagerAttributes::setAccessToken);

        setIfNotNull(remoteFetchConfiguration
                        .getRepositoryManagerAttributes().get(RemoteFetchConfigurationConstants.BRANCH),
                repositoryManagerAttributes::setBranch);

        setIfNotNull(remoteFetchConfiguration
                        .getRepositoryManagerAttributes().get(RemoteFetchConfigurationConstants.DIRECTORY),
                repositoryManagerAttributes::setDirectory);

        setIfNotNull(remoteFetchConfiguration
                        .getRepositoryManagerAttributes().get(RemoteFetchConfigurationConstants.URI),
                repositoryManagerAttributes::setUri);

        setIfNotNull(remoteFetchConfiguration
                        .getRepositoryManagerAttributes().get(RemoteFetchConfigurationConstants.USER_NAME),
                repositoryManagerAttributes::setUserName);
        return repositoryManagerAttributes;

    }

    /**
     * This method is used to create list response from basic remote fetch configuration.
     * This method is create list item from basic remote fetch configuration list items.
     *
     * @param basicRemoteFetchConfigurationList List response.
     * @return
     */
    private RemoteFetchConfigurationListResponse createRemoteFetchConfigurationListResponse
    (List<BasicRemoteFetchConfiguration> basicRemoteFetchConfigurationList) {

        RemoteFetchConfigurationListResponse remoteFetchConfigurationListResponse =
                new RemoteFetchConfigurationListResponse();
        if (CollectionUtils.isNotEmpty(basicRemoteFetchConfigurationList)) {
            List<RemoteFetchConfigurationListItem> remoteFetchConfigurations = new ArrayList<>();
            for (BasicRemoteFetchConfiguration basicRemoteFetchConfiguration : basicRemoteFetchConfigurationList) {
                RemoteFetchConfigurationListItem remoteFetchConfigurationListItem =
                        populateRemoteFetchConfigurationListResponse(basicRemoteFetchConfiguration);
                remoteFetchConfigurations.add(remoteFetchConfigurationListItem);
            }
            remoteFetchConfigurationListResponse.setRemotefetchConfigurations(remoteFetchConfigurations);
            remoteFetchConfigurationListResponse.setCount(remoteFetchConfigurations.size());
        } else {
            remoteFetchConfigurationListResponse.setCount(0);
        }
        return remoteFetchConfigurationListResponse;
    }

    /**
     * This method is used to populate remote fetch configuration list item from basic remote fetch configuration.
     *
     * @param basicRemoteFetchConfiguration basic remote fetch configuration.
     * @return
     */
    private RemoteFetchConfigurationListItem populateRemoteFetchConfigurationListResponse
    (BasicRemoteFetchConfiguration basicRemoteFetchConfiguration) {

        RemoteFetchConfigurationListItem remoteFetchConfigurationListItem = new RemoteFetchConfigurationListItem();

        remoteFetchConfigurationListItem.setId(basicRemoteFetchConfiguration.getId());
        remoteFetchConfigurationListItem.setIsEnabled(basicRemoteFetchConfiguration.isEnabled());
        remoteFetchConfigurationListItem.setActionListenerType(basicRemoteFetchConfiguration.getActionListenerType());
        remoteFetchConfigurationListItem.setConfigurationDeployerType
                (basicRemoteFetchConfiguration.getConfigurationDeployerType());
        remoteFetchConfigurationListItem.setRepositoryManagerType
                (basicRemoteFetchConfiguration.getRepositoryManagerType());
        remoteFetchConfigurationListItem.setRemoteFetchName(basicRemoteFetchConfiguration.getRemoteFetchName());
        if (basicRemoteFetchConfiguration.getLastDeployed() == null) {
            remoteFetchConfigurationListItem.setLastDeployed(null);
        } else {
            remoteFetchConfigurationListItem.setLastDeployed
                    (basicRemoteFetchConfiguration.getLastDeployed().toString());
        }
        remoteFetchConfigurationListItem.setFailedDeployments(basicRemoteFetchConfiguration.getFailedDeployments());
        remoteFetchConfigurationListItem.setSuccessfulDeployments(basicRemoteFetchConfiguration.
                getSuccessfulDeployments());

        return remoteFetchConfigurationListItem;
    }

    /**
     * This method is used to handle remote fetch core exception and create API error wit suitable response code and
     * status by checking its instance type.
     *
     * @param e
     * @param errorEnum
     * @param data
     * @return
     */
    private APIError handleRemoteFetchConfigurationException(RemoteFetchCoreException e,
                                                             RemoteFetchConfigurationConstants.ErrorMessage errorEnum,
                                                             String data) {

        ErrorResponse errorResponse = getErrorBuilder(errorEnum, data).build(log, e, errorEnum.getDescription());

        Response.Status status;

        if (e instanceof RemoteFetchClientException) {
            if (e.getErrorCode() != null) {
                String errorCode = e.getErrorCode();
                errorCode =
                        errorCode.contains(RemoteFetchConfigurationConstants.ERROR_CODE_DELIMITER) ?
                                errorCode : RemoteFetchConfigurationConstants.
                                REMOTE_FETCH_CONFIGURATION_MANAGEMENT_PREFIX + errorCode;
                errorResponse.setCode(errorCode);
            }
            errorResponse.setDescription(e.getMessage());
            status = Response.Status.BAD_REQUEST;
        } else if (e instanceof RemoteFetchServerException) {
            if (e.getErrorCode() != null) {
                String errorCode = e.getErrorCode();
                errorCode =
                        errorCode.contains(RemoteFetchConfigurationConstants.ERROR_CODE_DELIMITER) ?
                                errorCode : RemoteFetchConfigurationConstants.
                                REMOTE_FETCH_CONFIGURATION_MANAGEMENT_PREFIX + errorCode;
                errorResponse.setCode(errorCode);
            }
            errorResponse.setDescription(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return new APIError(status, errorResponse);
    }

    /**
     * From the error message and the data this method returns error builder.
     *
     * @param errorMsg error message.
     * @param data     data.
     * @return
     */
    private ErrorResponse.Builder getErrorBuilder(RemoteFetchConfigurationConstants.ErrorMessage errorMsg,
                                                  String data) {

        return new ErrorResponse.Builder().withCode(errorMsg.getCode()).withMessage(errorMsg.getMessage())
                .withDescription(includeData(errorMsg, data));
    }

    /**
     * Include context data to error message.
     *
     * @param error Constant.ErrorMessage.
     * @param data  Context data.
     * @return Formatted error message.
     */
    private static String includeData(RemoteFetchConfigurationConstants.ErrorMessage error,
                                      String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getDescription(), data);
        } else {
            message = error.getDescription();
        }
        return message;
    }

    /**
     * Handle exceptions generated in API.
     *
     * @param status HTTP Status.
     * @param error  Error Message information.
     * @return APIError.
     */
    private APIError handleException(Response.Status status, RemoteFetchConfigurationConstants.ErrorMessage error,
                                     String data) {

        return new APIError(status, getErrorBuilder(error, data).build());
    }

    /**
     * Clone given remote fetch configuration object.
     * @param id remote fetch configuration id.
     * @param remoteFetchConfiguration remote fetch configuration object which need to be cloned.
     * @return
     */
    private RemoteFetchConfiguration deepCopyRemoteFetchConfiguration
            (String id, RemoteFetchConfiguration remoteFetchConfiguration) {

        try {
            return (RemoteFetchConfiguration) BeanUtils.cloneBean(remoteFetchConfiguration);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException
                e) {
            throw handleException(Response.Status.INTERNAL_SERVER_ERROR, RemoteFetchConfigurationConstants.ErrorMessage
                    .ERROR_CODE_ERROR_UPDATING_RF_CONFIG, id);
        }

    }
}
