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
import org.wso2.carbon.identity.api.server.pdp.v1.model.Resource;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class ResourceSearchResponse  {
  
    private List<Resource> results = new ArrayList<>();

    private Map<String, Object> page = new HashMap<>();


    /**
    * List of matching subjects.
    **/
    public ResourceSearchResponse results(List<Resource> results) {

        this.results = results;
        return this;
    }
    
    @ApiModelProperty(required = true, value = "List of matching subjects.")
    @JsonProperty("results")
    @Valid
    @NotNull(message = "Property results cannot be null.")

    public List<Resource> getResults() {
        return results;
    }
    public void setResults(List<Resource> results) {
        this.results = results;
    }

    public ResourceSearchResponse addResultsItem(Resource resultsItem) {
        this.results.add(resultsItem);
        return this;
    }

        /**
    * A page token for paged requests.
    **/
    public ResourceSearchResponse page(Map<String, Object> page) {

        this.page = page;
        return this;
    }
    
    @ApiModelProperty(required = true, value = "A page token for paged requests.")
    @JsonProperty("page")
    @Valid
    @NotNull(message = "Property page cannot be null.")

    public Map<String, Object> getPage() {
        return page;
    }
    public void setPage(Map<String, Object> page) {
        this.page = page;
    }


    public ResourceSearchResponse putPageItem(String key, Object pageItem) {
        this.page.put(key, pageItem);
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
        ResourceSearchResponse resourceSearchResponse = (ResourceSearchResponse) o;
        return Objects.equals(this.results, resourceSearchResponse.results) &&
            Objects.equals(this.page, resourceSearchResponse.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, page);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceSearchResponse {\n");
        
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
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

