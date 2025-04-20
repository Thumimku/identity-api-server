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
import org.wso2.carbon.identity.api.server.pdp.v1.model.AccessEvaluationResponseContext;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class AccessEvaluationResponse  {
  
    private Boolean decision;
    private AccessEvaluationResponseContext context;

    /**
    * The result of the access evaluation. &#x60;true&#x60; represents \&quot;Permit\&quot;, &#x60;false&#x60; represents \&quot;Deny\&quot;.
    **/
    public AccessEvaluationResponse decision(Boolean decision) {

        this.decision = decision;
        return this;
    }
    
    @ApiModelProperty(required = true, value = "The result of the access evaluation. `true` represents \"Permit\", `false` represents \"Deny\".")
    @JsonProperty("decision")
    @Valid
    @NotNull(message = "Property decision cannot be null.")

    public Boolean getDecision() {
        return decision;
    }
    public void setDecision(Boolean decision) {
        this.decision = decision;
    }

    /**
    **/
    public AccessEvaluationResponse context(AccessEvaluationResponseContext context) {

        this.context = context;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("context")
    @Valid
    public AccessEvaluationResponseContext getContext() {
        return context;
    }
    public void setContext(AccessEvaluationResponseContext context) {
        this.context = context;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccessEvaluationResponse accessEvaluationResponse = (AccessEvaluationResponse) o;
        return Objects.equals(this.decision, accessEvaluationResponse.decision) &&
            Objects.equals(this.context, accessEvaluationResponse.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decision, context);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class AccessEvaluationResponse {\n");
        
        sb.append("    decision: ").append(toIndentedString(decision)).append("\n");
        sb.append("    context: ").append(toIndentedString(context)).append("\n");
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

