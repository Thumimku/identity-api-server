/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
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

package org.wso2.carbon.identity.api.server.application.management.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.api.server.application.management.v1.AccessTokenConfiguration;
import org.wso2.carbon.identity.api.server.application.management.v1.IdTokenConfiguration;
import org.wso2.carbon.identity.api.server.application.management.v1.OAuth2PKCEConfiguration;
import org.wso2.carbon.identity.api.server.application.management.v1.OIDCLogoutConfiguration;
import org.wso2.carbon.identity.api.server.application.management.v1.RefreshTokenConfiguration;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class OpenIDConnectConfiguration  {
  
    private String clientId;
    private String clientSecret;

@XmlType(name="StateEnum")
@XmlEnum(String.class)
public enum StateEnum {

    @XmlEnumValue("ACTIVE") ACTIVE(String.valueOf("ACTIVE")), @XmlEnumValue("REVOKED") REVOKED(String.valueOf("REVOKED"));


    private String value;

    StateEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static StateEnum fromValue(String value) {
        for (StateEnum b : StateEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

    private StateEnum state = StateEnum.ACTIVE;
    private List<String> grantTypes = new ArrayList<>();

    private List<String> callbackURLs = null;

    private List<String> allowedOrigins = null;

    private Boolean publicClient = false;
    private OAuth2PKCEConfiguration pkce;
    private HybridFlowConfiguration hybridFlow;
    private AccessTokenConfiguration accessToken;
    private RefreshTokenConfiguration refreshToken;
    private SubjectTokenConfiguration subjectToken;
    private IdTokenConfiguration idToken;
    private Boolean useClientIdAsSubClaimForAppTokens;
    private Boolean omitUsernameInIntrospectionRespForAppTokens;
    private OIDCLogoutConfiguration logout;
    private Boolean validateRequestObjectSignature = false;
    private List<String> scopeValidators = null;
    private ClientAuthenticationConfiguration clientAuthentication;
    private RequestObjectConfiguration requestObject;
    private PushAuthorizationRequestConfiguration pushAuthorizationRequest;
    private SubjectConfiguration subject;
    private Boolean isFAPIApplication = false;
    private FapiMetadata fapiMetadata;

    /**
    **/
    public OpenIDConnectConfiguration clientId(String clientId) {

        this.clientId = clientId;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("clientId")
    @Valid
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
    **/
    public OpenIDConnectConfiguration clientSecret(String clientSecret) {

        this.clientSecret = clientSecret;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("clientSecret")
    @Valid
    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
    **/
    public OpenIDConnectConfiguration state(StateEnum state) {

        this.state = state;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("state")
    @Valid
    public StateEnum getState() {
        return state;
    }
    public void setState(StateEnum state) {
        this.state = state;
    }

    /**
    **/
    public OpenIDConnectConfiguration grantTypes(List<String> grantTypes) {

        this.grantTypes = grantTypes;
        return this;
    }
    
    @ApiModelProperty(example = "[\"authorization_code\",\"password\"]", required = true, value = "")
    @JsonProperty("grantTypes")
    @Valid
    @NotNull(message = "Property grantTypes cannot be null.")
 @Size(min=1)
    public List<String> getGrantTypes() {
        return grantTypes;
    }
    public void setGrantTypes(List<String> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public OpenIDConnectConfiguration addGrantTypesItem(String grantTypesItem) {
        this.grantTypes.add(grantTypesItem);
        return this;
    }

        /**
    * Authorized redirect URIs
    **/
    public OpenIDConnectConfiguration callbackURLs(List<String> callbackURLs) {

        this.callbackURLs = callbackURLs;
        return this;
    }
    
    @ApiModelProperty(example = "[\"regexp=(https://app.example.com/callback1|https://app.example.com/callback2)\"]", value = "Authorized redirect URIs")
    @JsonProperty("callbackURLs")
    @Valid
    public List<String> getCallbackURLs() {
        return callbackURLs;
    }
    public void setCallbackURLs(List<String> callbackURLs) {
        this.callbackURLs = callbackURLs;
    }

    public OpenIDConnectConfiguration addCallbackURLsItem(String callbackURLsItem) {
        if (this.callbackURLs == null) {
            this.callbackURLs = new ArrayList<>();
        }
        this.callbackURLs.add(callbackURLsItem);
        return this;
    }

        /**
    * Authorized JavaScript origins. CORS requests will be allowed to these origins.
    **/
    public OpenIDConnectConfiguration allowedOrigins(List<String> allowedOrigins) {

        this.allowedOrigins = allowedOrigins;
        return this;
    }
    
    @ApiModelProperty(example = "[\"https://app.example.com\"]", value = "Authorized JavaScript origins. CORS requests will be allowed to these origins.")
    @JsonProperty("allowedOrigins")
    @Valid
    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }
    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public OpenIDConnectConfiguration addAllowedOriginsItem(String allowedOriginsItem) {
        if (this.allowedOrigins == null) {
            this.allowedOrigins = new ArrayList<>();
        }
        this.allowedOrigins.add(allowedOriginsItem);
        return this;
    }

        /**
    * Enabling this option will allow the client to authenticate without a client secret.
    **/
    public OpenIDConnectConfiguration publicClient(Boolean publicClient) {

        this.publicClient = publicClient;
        return this;
    }
    
    @ApiModelProperty(example = "false", value = "Enabling this option will allow the client to authenticate without a client secret.")
    @JsonProperty("publicClient")
    @Valid
    public Boolean getPublicClient() {
        return publicClient;
    }
    public void setPublicClient(Boolean publicClient) {
        this.publicClient = publicClient;
    }

    /**
    **/
    public OpenIDConnectConfiguration pkce(OAuth2PKCEConfiguration pkce) {

        this.pkce = pkce;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("pkce")
    @Valid
    public OAuth2PKCEConfiguration getPkce() {
        return pkce;
    }
    public void setPkce(OAuth2PKCEConfiguration pkce) {
        this.pkce = pkce;
    }

    /**
     **/
    public OpenIDConnectConfiguration hybridFlow(HybridFlowConfiguration hybridFlow) {

        this.hybridFlow = hybridFlow;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("hybridFlow")
    @Valid
    public HybridFlowConfiguration getHybridFlow() {
        return hybridFlow;
    }
    public void setHybridFlow(HybridFlowConfiguration hybridFlow) {
        this.hybridFlow = hybridFlow;
    }

    /**
    **/
    public OpenIDConnectConfiguration accessToken(AccessTokenConfiguration accessToken) {

        this.accessToken = accessToken;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("accessToken")
    @Valid
    public AccessTokenConfiguration getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(AccessTokenConfiguration accessToken) {
        this.accessToken = accessToken;
    }

    /**
    **/
    public OpenIDConnectConfiguration refreshToken(RefreshTokenConfiguration refreshToken) {

        this.refreshToken = refreshToken;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("refreshToken")
    @Valid
    public RefreshTokenConfiguration getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(RefreshTokenConfiguration refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
    **/
    public OpenIDConnectConfiguration subjectToken(SubjectTokenConfiguration subjectToken) {

        this.subjectToken = subjectToken;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("subjectToken")
    @Valid
    public SubjectTokenConfiguration getSubjectToken() {
        return subjectToken;
    }
    public void setSubjectToken(SubjectTokenConfiguration subjectToken) {
        this.subjectToken = subjectToken;
    }

    /**
    **/
    public OpenIDConnectConfiguration idToken(IdTokenConfiguration idToken) {

        this.idToken = idToken;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("idToken")
    @Valid
    public IdTokenConfiguration getIdToken() {
        return idToken;
    }
    public void setIdToken(IdTokenConfiguration idToken) {
        this.idToken = idToken;
    }

    /**
     * If enabled, client_id will be sent as the subject claim value for app tokens.
     */
    public OpenIDConnectConfiguration useClientIdAsSubClaimForAppTokens(Boolean useClientIdAsSubClaimForAppTokens) {

        this.useClientIdAsSubClaimForAppTokens = useClientIdAsSubClaimForAppTokens;
        return this;
    }

    @ApiModelProperty("If enable, client_id will be sent as the sub claim value for app tokens.")
    @JsonProperty("useClientIdAsSubClaimForAppTokens")
    @Valid
    public Boolean getUseClientIdAsSubClaimForAppTokens() {
        return useClientIdAsSubClaimForAppTokens;
    }
    public void setUseClientIdAsSubClaimForAppTokens(Boolean useClientIdAsSubClaimForAppTokens) {
        this.useClientIdAsSubClaimForAppTokens = useClientIdAsSubClaimForAppTokens;
    }

    /**
     * If enabled, username will not be sent in the introspection response for app tokens.
     */
    public OpenIDConnectConfiguration omitUsernameInIntrospectionRespForAppTokens(
            Boolean omitUsernameInIntrospectionRespForAppTokens) {

        this.omitUsernameInIntrospectionRespForAppTokens = omitUsernameInIntrospectionRespForAppTokens;
        return this;
    }

    @ApiModelProperty("If enabled, username will not be sent in the introspection response for app tokens.")
    @JsonProperty("omitUsernameInIntrospectionRespForAppTokens")
    @Valid
    public Boolean getOmitUsernameInIntrospectionRespForAppTokens() {
        return omitUsernameInIntrospectionRespForAppTokens;
    }
    public void setOmitUsernameInIntrospectionRespForAppTokens(Boolean omitUsernameInIntrospectionRespForAppTokens) {
        this.omitUsernameInIntrospectionRespForAppTokens = omitUsernameInIntrospectionRespForAppTokens;
    }

    /**
    **/
    public OpenIDConnectConfiguration logout(OIDCLogoutConfiguration logout) {

        this.logout = logout;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("logout")
    @Valid
    public OIDCLogoutConfiguration getLogout() {
        return logout;
    }
    public void setLogout(OIDCLogoutConfiguration logout) {
        this.logout = logout;
    }

    /**
    **/
    public OpenIDConnectConfiguration validateRequestObjectSignature(Boolean validateRequestObjectSignature) {

        this.validateRequestObjectSignature = validateRequestObjectSignature;
        return this;
    }
    
    @ApiModelProperty(example = "false", value = "")
    @JsonProperty("validateRequestObjectSignature")
    @Valid
    public Boolean getValidateRequestObjectSignature() {
        return validateRequestObjectSignature;
    }
    public void setValidateRequestObjectSignature(Boolean validateRequestObjectSignature) {
        this.validateRequestObjectSignature = validateRequestObjectSignature;
    }

    /**
    **/
    public OpenIDConnectConfiguration scopeValidators(List<String> scopeValidators) {

        this.scopeValidators = scopeValidators;
        return this;
    }
    
    @ApiModelProperty(example = "[\"Role based scope validator\",\"XACML Scope Validator\"]", value = "")
    @JsonProperty("scopeValidators")
    @Valid
    public List<String> getScopeValidators() {
        return scopeValidators;
    }
    public void setScopeValidators(List<String> scopeValidators) {
        this.scopeValidators = scopeValidators;
    }

    public OpenIDConnectConfiguration addScopeValidatorsItem(String scopeValidatorsItem) {
        if (this.scopeValidators == null) {
            this.scopeValidators = new ArrayList<>();
        }
        this.scopeValidators.add(scopeValidatorsItem);
        return this;
    }

    /**
     **/
    public OpenIDConnectConfiguration clientAuthentication(ClientAuthenticationConfiguration clientAuthentication) {

        this.clientAuthentication = clientAuthentication;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("clientAuthentication")
    @Valid
    public ClientAuthenticationConfiguration getClientAuthentication() {
        return clientAuthentication;
    }
    public void setClientAuthentication(ClientAuthenticationConfiguration clientAuthentication) {
        this.clientAuthentication = clientAuthentication;
    }

    /**
     **/
    public OpenIDConnectConfiguration requestObject(RequestObjectConfiguration requestObject) {

        this.requestObject = requestObject;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("requestObject")
    @Valid
    public RequestObjectConfiguration getRequestObject() {
        return requestObject;
    }
    public void setRequestObject(RequestObjectConfiguration requestObject) {
        this.requestObject = requestObject;
    }

    /**
     **/
    public OpenIDConnectConfiguration pushAuthorizationRequest(PushAuthorizationRequestConfiguration pushAuthorizationRequest) {

        this.pushAuthorizationRequest = pushAuthorizationRequest;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("pushAuthorizationRequest")
    @Valid
    public PushAuthorizationRequestConfiguration getPushAuthorizationRequest() {
        return pushAuthorizationRequest;
    }
    public void setPushAuthorizationRequest(PushAuthorizationRequestConfiguration pushAuthorizationRequest) {
        this.pushAuthorizationRequest = pushAuthorizationRequest;
    }

    /**
     **/
    public OpenIDConnectConfiguration subject(SubjectConfiguration subject) {

        this.subject = subject;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("subject")
    @Valid
    public SubjectConfiguration getSubject() {
        return subject;
    }
    public void setSubject(SubjectConfiguration subject) {
        this.subject = subject;
    }

    /**
     * Enabling this option will make the application FAPI conformant.
     **/
    public OpenIDConnectConfiguration isFAPIApplication(Boolean isFAPIApplication) {

        this.isFAPIApplication = isFAPIApplication;
        return this;
    }

    @ApiModelProperty(example = "false", value = "Enabling this option will make the application FAPI conformant.")
    @JsonProperty("isFAPIApplication")
    @Valid
    public Boolean getIsFAPIApplication() {
        return isFAPIApplication;
    }
    public void setIsFAPIApplication(Boolean isFAPIApplication) {
        this.isFAPIApplication = isFAPIApplication;
    }

    /**
     **/
    public OpenIDConnectConfiguration fapiMetadata(FapiMetadata fapiMetadata) {

        this.fapiMetadata = fapiMetadata;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("fapiMetadata")
    @Valid
    public FapiMetadata getFapiMetadata() {
        return fapiMetadata;
    }
    public void setFapiMetadata(FapiMetadata fapiMetadata) {
        this.fapiMetadata = fapiMetadata;
    }

    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpenIDConnectConfiguration openIDConnectConfiguration = (OpenIDConnectConfiguration) o;
        return Objects.equals(this.clientId, openIDConnectConfiguration.clientId) &&
            Objects.equals(this.clientSecret, openIDConnectConfiguration.clientSecret) &&
            Objects.equals(this.state, openIDConnectConfiguration.state) &&
            Objects.equals(this.grantTypes, openIDConnectConfiguration.grantTypes) &&
            Objects.equals(this.callbackURLs, openIDConnectConfiguration.callbackURLs) &&
            Objects.equals(this.allowedOrigins, openIDConnectConfiguration.allowedOrigins) &&
            Objects.equals(this.publicClient, openIDConnectConfiguration.publicClient) &&
            Objects.equals(this.pkce, openIDConnectConfiguration.pkce) &&
            Objects.equals(this.hybridFlow, openIDConnectConfiguration.hybridFlow) &&
            Objects.equals(this.accessToken, openIDConnectConfiguration.accessToken) &&
            Objects.equals(this.refreshToken, openIDConnectConfiguration.refreshToken) &&
            Objects.equals(this.subjectToken, openIDConnectConfiguration.subjectToken) &&
            Objects.equals(this.idToken, openIDConnectConfiguration.idToken) &&
            Objects.equals(this.useClientIdAsSubClaimForAppTokens,
                    openIDConnectConfiguration.useClientIdAsSubClaimForAppTokens) &&
            Objects.equals(this.omitUsernameInIntrospectionRespForAppTokens,
                    openIDConnectConfiguration.omitUsernameInIntrospectionRespForAppTokens) &&
            Objects.equals(this.logout, openIDConnectConfiguration.logout) &&
            Objects.equals(this.validateRequestObjectSignature, openIDConnectConfiguration.validateRequestObjectSignature) &&
            Objects.equals(this.scopeValidators, openIDConnectConfiguration.scopeValidators) &&
            Objects.equals(this.clientAuthentication, openIDConnectConfiguration.clientAuthentication) &&
            Objects.equals(this.requestObject, openIDConnectConfiguration.requestObject) &&
            Objects.equals(this.pushAuthorizationRequest, openIDConnectConfiguration.pushAuthorizationRequest) &&
            Objects.equals(this.subject, openIDConnectConfiguration.subject) &&
            Objects.equals(this.isFAPIApplication, openIDConnectConfiguration.isFAPIApplication) &&
            Objects.equals(this.fapiMetadata, openIDConnectConfiguration.fapiMetadata);
    }

    @Override
    public int hashCode() {

        return Objects.hash(clientId, clientSecret, state, grantTypes, callbackURLs, allowedOrigins, publicClient, pkce, hybridFlow, accessToken, refreshToken, subjectToken, idToken, useClientIdAsSubClaimForAppTokens, omitUsernameInIntrospectionRespForAppTokens, logout, validateRequestObjectSignature, scopeValidators, clientAuthentication, requestObject, pushAuthorizationRequest, subject, isFAPIApplication, fapiMetadata);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class OpenIDConnectConfiguration {\n");
        
        sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
        sb.append("    clientSecret: ").append(toIndentedString(clientSecret)).append("\n");
        sb.append("    state: ").append(toIndentedString(state)).append("\n");
        sb.append("    grantTypes: ").append(toIndentedString(grantTypes)).append("\n");
        sb.append("    callbackURLs: ").append(toIndentedString(callbackURLs)).append("\n");
        sb.append("    allowedOrigins: ").append(toIndentedString(allowedOrigins)).append("\n");
        sb.append("    publicClient: ").append(toIndentedString(publicClient)).append("\n");
        sb.append("    pkce: ").append(toIndentedString(pkce)).append("\n");
        sb.append("    hybridFlow: ").append(toIndentedString(hybridFlow)).append("\n");
        sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
        sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
        sb.append("    subjectToken: ").append(toIndentedString(subjectToken)).append("\n");
        sb.append("    idToken: ").append(toIndentedString(idToken)).append("\n");
        sb.append("    useClientIdAsSubClaimForAppTokens: ").append(toIndentedString(useClientIdAsSubClaimForAppTokens)).append("\n");
        sb.append("    omitUsernameInIntrospectionRespForAppTokens: ").append(toIndentedString(omitUsernameInIntrospectionRespForAppTokens)).append("\n");
        sb.append("    logout: ").append(toIndentedString(logout)).append("\n");
        sb.append("    validateRequestObjectSignature: ").append(toIndentedString(validateRequestObjectSignature)).append("\n");
        sb.append("    scopeValidators: ").append(toIndentedString(scopeValidators)).append("\n");
        sb.append("    clientAuthentication: ").append(toIndentedString(clientAuthentication)).append("\n");
        sb.append("    requestObject: ").append(toIndentedString(requestObject)).append("\n");
        sb.append("    pushAuthorizationRequest: ").append(toIndentedString(pushAuthorizationRequest)).append("\n");
        sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
        sb.append("    isFAPIApplication: ").append(toIndentedString(isFAPIApplication)).append("\n");
        sb.append("    fapiMetadata: ").append(toIndentedString(fapiMetadata)).append("\n");
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

