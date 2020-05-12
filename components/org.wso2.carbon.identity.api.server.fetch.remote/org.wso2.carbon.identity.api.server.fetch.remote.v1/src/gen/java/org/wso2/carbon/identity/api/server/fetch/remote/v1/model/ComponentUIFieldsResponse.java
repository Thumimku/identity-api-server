/*
* Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.wso2.carbon.identity.api.server.fetch.remote.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.UIFieldResponse;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class ComponentUIFieldsResponse  {
  
    private String identifier;
    private List<UIFieldResponse> uiFields = null;


    /**
    **/
    public ComponentUIFieldsResponse identifier(String identifier) {

        this.identifier = identifier;
        return this;
    }
    
    @ApiModelProperty(example = "POLLING", value = "")
    @JsonProperty("identifier")
    @Valid
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
    **/
    public ComponentUIFieldsResponse uiFields(List<UIFieldResponse> uiFields) {

        this.uiFields = uiFields;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("UIFields")
    @Valid
    public List<UIFieldResponse> getUiFields() {
        return uiFields;
    }
    public void setUiFields(List<UIFieldResponse> uiFields) {
        this.uiFields = uiFields;
    }

    public ComponentUIFieldsResponse addUiFieldsItem(UIFieldResponse uiFieldsItem) {
        if (this.uiFields == null) {
            this.uiFields = new ArrayList<>();
        }
        this.uiFields.add(uiFieldsItem);
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
        ComponentUIFieldsResponse componentUIFieldsResponse = (ComponentUIFieldsResponse) o;
        return Objects.equals(this.identifier, componentUIFieldsResponse.identifier) &&
            Objects.equals(this.uiFields, componentUIFieldsResponse.uiFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, uiFields);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ComponentUIFieldsResponse {\n");
        
        sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
        sb.append("    uiFields: ").append(toIndentedString(uiFields)).append("\n");
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

