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
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.ComponentUIFieldsResponse;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class AllComponentUIFieldsResponse  {
  
    private List<ComponentUIFieldsResponse> actionListener = null;

    private List<ComponentUIFieldsResponse> repositoryManager = null;

    private List<ComponentUIFieldsResponse> configDeployer = null;


    /**
    **/
    public AllComponentUIFieldsResponse actionListener(List<ComponentUIFieldsResponse> actionListener) {

        this.actionListener = actionListener;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("actionListener")
    @Valid
    public List<ComponentUIFieldsResponse> getActionListener() {
        return actionListener;
    }
    public void setActionListener(List<ComponentUIFieldsResponse> actionListener) {
        this.actionListener = actionListener;
    }

    public AllComponentUIFieldsResponse addActionListenerItem(ComponentUIFieldsResponse actionListenerItem) {
        if (this.actionListener == null) {
            this.actionListener = new ArrayList<>();
        }
        this.actionListener.add(actionListenerItem);
        return this;
    }

        /**
    **/
    public AllComponentUIFieldsResponse repositoryManager(List<ComponentUIFieldsResponse> repositoryManager) {

        this.repositoryManager = repositoryManager;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("repositoryManager")
    @Valid
    public List<ComponentUIFieldsResponse> getRepositoryManager() {
        return repositoryManager;
    }
    public void setRepositoryManager(List<ComponentUIFieldsResponse> repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    public AllComponentUIFieldsResponse addRepositoryManagerItem(ComponentUIFieldsResponse repositoryManagerItem) {
        if (this.repositoryManager == null) {
            this.repositoryManager = new ArrayList<>();
        }
        this.repositoryManager.add(repositoryManagerItem);
        return this;
    }

        /**
    **/
    public AllComponentUIFieldsResponse configDeployer(List<ComponentUIFieldsResponse> configDeployer) {

        this.configDeployer = configDeployer;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("configDeployer")
    @Valid
    public List<ComponentUIFieldsResponse> getConfigDeployer() {
        return configDeployer;
    }
    public void setConfigDeployer(List<ComponentUIFieldsResponse> configDeployer) {
        this.configDeployer = configDeployer;
    }

    public AllComponentUIFieldsResponse addConfigDeployerItem(ComponentUIFieldsResponse configDeployerItem) {
        if (this.configDeployer == null) {
            this.configDeployer = new ArrayList<>();
        }
        this.configDeployer.add(configDeployerItem);
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
        AllComponentUIFieldsResponse allComponentUIFieldsResponse = (AllComponentUIFieldsResponse) o;
        return Objects.equals(this.actionListener, allComponentUIFieldsResponse.actionListener) &&
            Objects.equals(this.repositoryManager, allComponentUIFieldsResponse.repositoryManager) &&
            Objects.equals(this.configDeployer, allComponentUIFieldsResponse.configDeployer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionListener, repositoryManager, configDeployer);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class AllComponentUIFieldsResponse {\n");
        
        sb.append("    actionListener: ").append(toIndentedString(actionListener)).append("\n");
        sb.append("    repositoryManager: ").append(toIndentedString(repositoryManager)).append("\n");
        sb.append("    configDeployer: ").append(toIndentedString(configDeployer)).append("\n");
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

