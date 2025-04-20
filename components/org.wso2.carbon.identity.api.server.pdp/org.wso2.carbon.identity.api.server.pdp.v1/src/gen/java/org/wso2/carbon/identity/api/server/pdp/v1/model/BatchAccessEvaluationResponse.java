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
import java.util.List;
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationResponseEvaluations;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class BatchAccessEvaluationResponse  {
  
    private List<BatchAccessEvaluationResponseEvaluations> evaluations = new ArrayList<>();


    /**
    * A list of access evaluation responses corresponding to each request in the evaluations array.
    **/
    public BatchAccessEvaluationResponse evaluations(List<BatchAccessEvaluationResponseEvaluations> evaluations) {

        this.evaluations = evaluations;
        return this;
    }
    
    @ApiModelProperty(required = true, value = "A list of access evaluation responses corresponding to each request in the evaluations array.")
    @JsonProperty("evaluations")
    @Valid
    @NotNull(message = "Property evaluations cannot be null.")

    public List<BatchAccessEvaluationResponseEvaluations> getEvaluations() {
        return evaluations;
    }
    public void setEvaluations(List<BatchAccessEvaluationResponseEvaluations> evaluations) {
        this.evaluations = evaluations;
    }

    public BatchAccessEvaluationResponse addEvaluationsItem(BatchAccessEvaluationResponseEvaluations evaluationsItem) {
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
        BatchAccessEvaluationResponse batchAccessEvaluationResponse = (BatchAccessEvaluationResponse) o;
        return Objects.equals(this.evaluations, batchAccessEvaluationResponse.evaluations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evaluations);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class BatchAccessEvaluationResponse {\n");
        
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

