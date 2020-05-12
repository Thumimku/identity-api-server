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
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class UIFieldResponse  {
  
    private String id;
    private String type;
    private String displayName;
    private String helpText;
    private String validationRegex;
    private List<String> defaultValues = null;

    private Boolean isMandatory = true;
    private Boolean isSensitive = true;
    private Boolean isMultiValue = true;

    /**
    **/
    public UIFieldResponse id(String id) {

        this.id = id;
        return this;
    }
    
    @ApiModelProperty(example = "frequency", value = "")
    @JsonProperty("id")
    @Valid
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
    **/
    public UIFieldResponse type(String type) {

        this.type = type;
        return this;
    }
    
    @ApiModelProperty(example = "TEXT_BOX", value = "")
    @JsonProperty("type")
    @Valid
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    /**
    **/
    public UIFieldResponse displayName(String displayName) {

        this.displayName = displayName;
        return this;
    }
    
    @ApiModelProperty(example = "Polling Frequency", value = "")
    @JsonProperty("displayName")
    @Valid
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
    **/
    public UIFieldResponse helpText(String helpText) {

        this.helpText = helpText;
        return this;
    }
    
    @ApiModelProperty(example = "Number of seconds polling should occur", value = "")
    @JsonProperty("helpText")
    @Valid
    public String getHelpText() {
        return helpText;
    }
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    /**
    **/
    public UIFieldResponse validationRegex(String validationRegex) {

        this.validationRegex = validationRegex;
        return this;
    }
    
    @ApiModelProperty(example = "^\\d+$", value = "")
    @JsonProperty("validationRegex")
    @Valid
    public String getValidationRegex() {
        return validationRegex;
    }
    public void setValidationRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

    /**
    **/
    public UIFieldResponse defaultValues(List<String> defaultValues) {

        this.defaultValues = defaultValues;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("defaultValues")
    @Valid
    public List<String> getDefaultValues() {
        return defaultValues;
    }
    public void setDefaultValues(List<String> defaultValues) {
        this.defaultValues = defaultValues;
    }

    public UIFieldResponse addDefaultValuesItem(String defaultValuesItem) {
        if (this.defaultValues == null) {
            this.defaultValues = new ArrayList<>();
        }
        this.defaultValues.add(defaultValuesItem);
        return this;
    }

        /**
    **/
    public UIFieldResponse isMandatory(Boolean isMandatory) {

        this.isMandatory = isMandatory;
        return this;
    }
    
    @ApiModelProperty(example = "true", value = "")
    @JsonProperty("isMandatory")
    @Valid
    public Boolean getIsMandatory() {
        return isMandatory;
    }
    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    /**
    **/
    public UIFieldResponse isSensitive(Boolean isSensitive) {

        this.isSensitive = isSensitive;
        return this;
    }
    
    @ApiModelProperty(example = "true", value = "")
    @JsonProperty("isSensitive")
    @Valid
    public Boolean getIsSensitive() {
        return isSensitive;
    }
    public void setIsSensitive(Boolean isSensitive) {
        this.isSensitive = isSensitive;
    }

    /**
    **/
    public UIFieldResponse isMultiValue(Boolean isMultiValue) {

        this.isMultiValue = isMultiValue;
        return this;
    }
    
    @ApiModelProperty(example = "true", value = "")
    @JsonProperty("isMultiValue")
    @Valid
    public Boolean getIsMultiValue() {
        return isMultiValue;
    }
    public void setIsMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UIFieldResponse uiFieldResponse = (UIFieldResponse) o;
        return Objects.equals(this.id, uiFieldResponse.id) &&
            Objects.equals(this.type, uiFieldResponse.type) &&
            Objects.equals(this.displayName, uiFieldResponse.displayName) &&
            Objects.equals(this.helpText, uiFieldResponse.helpText) &&
            Objects.equals(this.validationRegex, uiFieldResponse.validationRegex) &&
            Objects.equals(this.defaultValues, uiFieldResponse.defaultValues) &&
            Objects.equals(this.isMandatory, uiFieldResponse.isMandatory) &&
            Objects.equals(this.isSensitive, uiFieldResponse.isSensitive) &&
            Objects.equals(this.isMultiValue, uiFieldResponse.isMultiValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, displayName, helpText, validationRegex, defaultValues, isMandatory, isSensitive, isMultiValue);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class UIFieldResponse {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    helpText: ").append(toIndentedString(helpText)).append("\n");
        sb.append("    validationRegex: ").append(toIndentedString(validationRegex)).append("\n");
        sb.append("    defaultValues: ").append(toIndentedString(defaultValues)).append("\n");
        sb.append("    isMandatory: ").append(toIndentedString(isMandatory)).append("\n");
        sb.append("    isSensitive: ").append(toIndentedString(isSensitive)).append("\n");
        sb.append("    isMultiValue: ").append(toIndentedString(isMultiValue)).append("\n");
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

