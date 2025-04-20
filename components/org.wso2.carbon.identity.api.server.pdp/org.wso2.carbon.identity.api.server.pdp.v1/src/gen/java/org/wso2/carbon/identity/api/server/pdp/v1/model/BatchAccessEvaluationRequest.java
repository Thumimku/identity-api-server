/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
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

package org.wso2.carbon.identity.api.server.pdp.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.wso2.carbon.identity.api.server.pdp.v1.model.Action;
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationRequestEvaluations;
import org.wso2.carbon.identity.api.server.pdp.v1.model.Resource;
import org.wso2.carbon.identity.api.server.pdp.v1.model.Subject;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class BatchAccessEvaluationRequest  {
  
    private Subject subject;
    private Resource resource;
    private Action action;
    private Map<String, Object> context = null;

    private List<BatchAccessEvaluationRequestEvaluations> evaluations = new ArrayList<>();


    /**
    **/
    public BatchAccessEvaluationRequest subject(Subject subject) {

        this.subject = subject;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("subject")
    @Valid
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
    **/
    public BatchAccessEvaluationRequest resource(Resource resource) {

        this.resource = resource;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("resource")
    @Valid
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
    **/
    public BatchAccessEvaluationRequest action(Action action) {

        this.action = action;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("action")
    @Valid
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }

    /**
    * Environmental or contextual information relevant to the access request.
    **/
    public BatchAccessEvaluationRequest context(Map<String, Object> context) {

        this.context = context;
        return this;
    }
    
    @ApiModelProperty(value = "Environmental or contextual information relevant to the access request.")
    @JsonProperty("context")
    @Valid
    public Map<String, Object> getContext() {
        return context;
    }
    public void setContext(Map<String, Object> context) {
        this.context = context;
    }


    public BatchAccessEvaluationRequest putContextItem(String key, Object contextItem) {
        if (this.context == null) {
            this.context = new HashMap<>();
        }
        this.context.put(key, contextItem);
        return this;
    }

        /**
    * A list of access evaluation requests. Each request may inherit default values from the top-level subject, resource, action, and context properties.
    **/
    public BatchAccessEvaluationRequest evaluations(List<BatchAccessEvaluationRequestEvaluations> evaluations) {

        this.evaluations = evaluations;
        return this;
    }
    
    @ApiModelProperty(required = true, value = "A list of access evaluation requests. Each request may inherit default values from the top-level subject, resource, action, and context properties.")
    @JsonProperty("evaluations")
    @Valid
    @NotNull(message = "Property evaluations cannot be null.")

    public List<BatchAccessEvaluationRequestEvaluations> getEvaluations() {
        return evaluations;
    }
    public void setEvaluations(List<BatchAccessEvaluationRequestEvaluations> evaluations) {
        this.evaluations = evaluations;
    }

    public BatchAccessEvaluationRequest addEvaluationsItem(BatchAccessEvaluationRequestEvaluations evaluationsItem) {
        this.evaluations.add(evaluationsItem);
        return this;
    }

    

    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BatchAccessEvaluationRequest batchAccessEvaluationRequest = (BatchAccessEvaluationRequest) o;
        return Objects.equals(this.subject, batchAccessEvaluationRequest.subject) &&
            Objects.equals(this.resource, batchAccessEvaluationRequest.resource) &&
            Objects.equals(this.action, batchAccessEvaluationRequest.action) &&
            Objects.equals(this.context, batchAccessEvaluationRequest.context) &&
            Objects.equals(this.evaluations, batchAccessEvaluationRequest.evaluations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, resource, action, context, evaluations);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class BatchAccessEvaluationRequest {\n");
        
        sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
        sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
        sb.append("    action: ").append(toIndentedString(action)).append("\n");
        sb.append("    context: ").append(toIndentedString(context)).append("\n");
        sb.append("    evaluations: ").append(toIndentedString(evaluations)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
    * Convert the given object to string with each line indented by 4 spaces
    * (except the first line).
    */
    private String toIndentedString(java.lang.Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n");
    }
}

