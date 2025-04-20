/*
 * Copyright (c) 2020-2025, WSO2 LLC. (http://www.wso2.com).
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

package org.wso2.carbon.identity.api.server.pdp.v1.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.api.server.pdp.v1.model.AccessEvaluationRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.AccessEvaluationResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.model.Action;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ActionSearchRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ActionSearchResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationRequestEvaluations;
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationResponseEvaluations;
import org.wso2.carbon.identity.api.server.pdp.v1.model.Resource;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ResourceSearchRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ResourceSearchResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.model.Subject;
import org.wso2.carbon.identity.api.server.pdp.v1.model.SubjectSearchRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.SubjectSearchResponse;
import org.wso2.carbon.identity.authorization.framework.exception.AccessEvaluationException;
import org.wso2.carbon.identity.authorization.framework.model.AuthorizationAction;
import org.wso2.carbon.identity.authorization.framework.model.AuthorizationResource;
import org.wso2.carbon.identity.authorization.framework.model.AuthorizationSubject;
import org.wso2.carbon.identity.authorization.framework.model.BulkAccessEvaluationRequest;
import org.wso2.carbon.identity.authorization.framework.model.BulkAccessEvaluationResponse;
import org.wso2.carbon.identity.authorization.framework.service.AccessEvaluationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Call internal OSGi services to perform server CORS management.
 */
public class PDPAccessEvaluationService {

    private final AccessEvaluationService accessEvaluationService;
    private static final Log LOG = LogFactory.getLog(PDPAccessEvaluationService.class);

    public PDPAccessEvaluationService(AccessEvaluationService accessEvaluationService) {

        this.accessEvaluationService = accessEvaluationService;
    }

    public AccessEvaluationResponse evaluate(AccessEvaluationRequest accessEvaluationRequest) {

        try {

            org.wso2.carbon.identity.authorization.framework.model.AccessEvaluationResponse
                    authorizationEvaluationResponse =
                    accessEvaluationService.evaluate(mapToAuthorizationRequest(accessEvaluationRequest));
            AccessEvaluationResponse accessEvaluationResponse = new AccessEvaluationResponse();

            if (authorizationEvaluationResponse.getDecision()) {
                accessEvaluationResponse.setDecision(true);
                accessEvaluationResponse.setContext(null);
            } else {
                accessEvaluationResponse.setDecision(false);
                accessEvaluationResponse.setContext(null);
            }
            return accessEvaluationResponse;
        } catch (AccessEvaluationException e) {
            throw new RuntimeException(e);
        }
    }

    public SubjectSearchResponse searchSubject(SubjectSearchRequest subjectSearchRequest) {

        SubjectSearchResponse subjectSearchResponse = new SubjectSearchResponse();
        Map<String, Object> page = new HashMap<>();
        page.put("next token", "12345");
        Subject subject = new Subject();
        subject.id("23224").type("user");
        subjectSearchResponse.page(page).addResultsItem(subject);
        return subjectSearchResponse;
    }

    public ActionSearchResponse searchAction(ActionSearchRequest actionSearchRequest) {

        ActionSearchResponse actionSearchResponse = new ActionSearchResponse();
        Map<String, Object> page = new HashMap<>();
        page.put("next token", "12345");
        Action action = new Action();
        action.name("can_check");
        actionSearchResponse.page(page).addResultsItem(action);
        return actionSearchResponse;
    }

    public ResourceSearchResponse searchResource(ResourceSearchRequest resourceSearchRequest) {

        ResourceSearchResponse resourceSearchResponse = new ResourceSearchResponse();
        Map<String, Object> page = new HashMap<>();
        page.put("next token", "12345");
        Resource resource = new Resource();
        resource.id("23224").type("account");
        resourceSearchResponse.page(page).addResultsItem(resource);
        return resourceSearchResponse;
    }

    public BatchAccessEvaluationResponse batchEvaluate (BatchAccessEvaluationRequest batchAccessEvaluationRequest) {

        Subject subject = batchAccessEvaluationRequest.getSubject();
        Resource resource = batchAccessEvaluationRequest.getResource();
        Action action = batchAccessEvaluationRequest.getAction();
        ArrayList<org.wso2.carbon.identity.authorization.framework.model.AccessEvaluationRequest> requestItems
                = new ArrayList<>();


        for (BatchAccessEvaluationRequestEvaluations batchAccessEvaluationRequestEvaluation:
                batchAccessEvaluationRequest.getEvaluations()) {

            AccessEvaluationRequest accessEvaluationRequest = new AccessEvaluationRequest()
                    .action(batchAccessEvaluationRequestEvaluation.getAction())
                    .context(batchAccessEvaluationRequestEvaluation.getContext())
                    .subject(batchAccessEvaluationRequestEvaluation.getSubject())
                    .resource(batchAccessEvaluationRequestEvaluation.getResource());
            if (subject != null) {
                accessEvaluationRequest.setSubject(subject);
            }
            if (resource != null) {
                accessEvaluationRequest.setResource(resource);
            }
            if (action != null) {
                accessEvaluationRequest.setAction(action);
            }
            if (batchAccessEvaluationRequest.getContext() != null) {
                accessEvaluationRequest.setContext(batchAccessEvaluationRequest.getContext());
            }
            requestItems.add(mapToAuthorizationRequest(accessEvaluationRequest));
        }
        BulkAccessEvaluationRequest bulkAccessEvaluationRequest = new BulkAccessEvaluationRequest(requestItems);

        try {

            BulkAccessEvaluationResponse bulkEvaluate =
                    accessEvaluationService.bulkEvaluate(bulkAccessEvaluationRequest);
            return convertToBatchResponse(bulkEvaluate);
        } catch (AccessEvaluationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts an AccessEvaluationRequest to an AuthorizationEvaluationRequest.
     *
     * @param accessEvaluationRequest The source object to map.
     * @return The mapped AuthorizationEvaluationRequest.
     */
    public org.wso2.carbon.identity.authorization.framework.model.AccessEvaluationRequest
        mapToAuthorizationRequest(AccessEvaluationRequest accessEvaluationRequest) {

        if (accessEvaluationRequest == null) {
            return null;
        }

        // Map Subject to AuthorizationSubject
        AuthorizationSubject authSubject = mapSubject(accessEvaluationRequest.getSubject());

        // Map Resource to AuthorizationResource
        AuthorizationResource authResource = mapResource(accessEvaluationRequest.getResource());

        // Map Action to AuthorizationAction
        AuthorizationAction authAction = mapAction(accessEvaluationRequest.getAction());

        // Copy Context
        Map<String, Object> context = accessEvaluationRequest.getContext() != null
                ? new HashMap<>(accessEvaluationRequest.getContext())
                : null;

        // Build AuthorizationEvaluationRequest
        org.wso2.carbon.identity.authorization.framework.model.AccessEvaluationRequest authorizationRequest =
                new org.wso2.carbon.identity.authorization.framework.model.AccessEvaluationRequest
                        (authSubject, authAction, authResource);
        authorizationRequest.setContext(context);

        return authorizationRequest;
    }

    /**
     * Maps Subject to AuthorizationSubject.
     */
    private AuthorizationSubject mapSubject(Subject subject) {

        if (subject == null) {
            return null;
        }
        AuthorizationSubject authSubject = new AuthorizationSubject(subject.getType(), subject.getId());
        authSubject.setProperties(subject.getProperties() != null ? new HashMap<>(subject.getProperties()) : null);
        return authSubject;
    }

    /**
     * Maps Resource to AuthorizationResource.
     */
    private AuthorizationResource mapResource(Resource resource) {

        if (resource == null) {
            return null;
        }
        AuthorizationResource authResource = new AuthorizationResource(resource.getType(), resource.getId());
        authResource.setProperties(resource.getProperties() != null ? new HashMap<>(resource.getProperties()) : null);
        return authResource;
    }

    /**
     * Maps Action to AuthorizationAction.
     */
    private AuthorizationAction mapAction(Action action) {

        if (action == null) {
            return null;
        }
        AuthorizationAction authAction = new AuthorizationAction(action.getName());
        authAction.setProperties(action.getProperties() != null ? new HashMap<>(action.getProperties()) : null);
        return authAction;
    }

    public BatchAccessEvaluationResponse convertToBatchResponse(BulkAccessEvaluationResponse bulkResponse) {
        BatchAccessEvaluationResponse batchResponse = new BatchAccessEvaluationResponse();

        if (bulkResponse != null && bulkResponse.getResults() != null) {
            for (org.wso2.carbon.identity.authorization.framework.model.AccessEvaluationResponse accessResponse
                    : bulkResponse.getResults()) {
                BatchAccessEvaluationResponseEvaluations evaluation = new BatchAccessEvaluationResponseEvaluations()
                        .decision(accessResponse.getDecision());

                batchResponse.addEvaluationsItem(evaluation);
            }
        }

        return batchResponse;
    }
}
