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
import javax.validation.constraints.*;

/**
 * Additional context or reasons for the decision.
 **/

import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;
@ApiModel(description = "Additional context or reasons for the decision.")
public class AccessEvaluationResponseContext  {
  
    private String id;
    private Object reasonAdmin;
    private Object reasonUser;

    /**
    * Identifier for the decision context.
    **/
    public AccessEvaluationResponseContext id(String id) {

        this.id = id;
        return this;
    }
    
    @ApiModelProperty(value = "Identifier for the decision context.")
    @JsonProperty("id")
    @Valid
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
    * Administrative reason for the decision, not intended for end-user exposure.
    **/
    public AccessEvaluationResponseContext reasonAdmin(Object reasonAdmin) {

        this.reasonAdmin = reasonAdmin;
        return this;
    }
    
    @ApiModelProperty(value = "Administrative reason for the decision, not intended for end-user exposure.")
    @JsonProperty("reason_admin")
    @Valid
    public Object getReasonAdmin() {
        return reasonAdmin;
    }
    public void setReasonAdmin(Object reasonAdmin) {
        this.reasonAdmin = reasonAdmin;
    }

    /**
    * User-facing reason for the decision.
    **/
    public AccessEvaluationResponseContext reasonUser(Object reasonUser) {

        this.reasonUser = reasonUser;
        return this;
    }
    
    @ApiModelProperty(value = "User-facing reason for the decision.")
    @JsonProperty("reason_user")
    @Valid
    public Object getReasonUser() {
        return reasonUser;
    }
    public void setReasonUser(Object reasonUser) {
        this.reasonUser = reasonUser;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccessEvaluationResponseContext accessEvaluationResponseContext = (AccessEvaluationResponseContext) o;
        return Objects.equals(this.id, accessEvaluationResponseContext.id) &&
            Objects.equals(this.reasonAdmin, accessEvaluationResponseContext.reasonAdmin) &&
            Objects.equals(this.reasonUser, accessEvaluationResponseContext.reasonUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reasonAdmin, reasonUser);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class AccessEvaluationResponseContext {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    reasonAdmin: ").append(toIndentedString(reasonAdmin)).append("\n");
        sb.append("    reasonUser: ").append(toIndentedString(reasonUser)).append("\n");
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

