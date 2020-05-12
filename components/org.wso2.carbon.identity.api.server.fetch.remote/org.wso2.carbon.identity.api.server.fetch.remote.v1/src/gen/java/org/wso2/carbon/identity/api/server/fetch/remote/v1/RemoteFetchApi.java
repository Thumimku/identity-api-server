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

package org.wso2.carbon.identity.api.server.fetch.remote.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import java.io.InputStream;

import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.AllComponentUIFieldsResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.Error;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationGetResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationListResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationPOSTRequest;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.RemoteFetchConfigurationPatchRequest;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.model.StatusListResponse;
import org.wso2.carbon.identity.api.server.fetch.remote.v1.RemoteFetchApiService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/remote-fetch")
@Api(description = "The remote-fetch API")

public class RemoteFetchApi  {

    @Autowired
    private RemoteFetchApiService delegate;

    @Valid
    @POST
    @Path("/configs")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @ApiOperation(value = "Add a new remotefetch configuration ", notes = "This API provides the capability to create a new remotefetch configuration. <br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = Void.class, tags={ "Remote Fetch Configurations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successfully created.", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response addRemoteFetchConfig(@ApiParam(value = "This represents the remotefetch configuration to be created." ,required=true) @Valid RemoteFetchConfigurationPOSTRequest remoteFetchConfigurationPOSTRequest) {

        return delegate.addRemoteFetchConfig(remoteFetchConfigurationPOSTRequest );
    }

    @Valid
    @DELETE
    @Path("/configs/{id}")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Delete a remotefetch Configuration by using the remotefetch Configuration's ID. ", notes = "This API provides the capability to delete a remotefetch Configuration by giving its ID. <br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = Void.class, tags={ "Remote Fetch Configurations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successfully Deleted", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response deleteRemoteFetchConfig(@ApiParam(value = "ID of the remotefetch Configuration",required=true) @PathParam("id") String id) {

        return delegate.deleteRemoteFetchConfig(id );
    }

    @Valid
    @GET
    @Path("/configs/attributes")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Deployment status of remotefetch configuration. ", notes = "This API provides the status of remote fetch configuration. The status hold deployment status and the deployment failure details . <br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = AllComponentUIFieldsResponse.class, tags={ "Remote fetch Configuration Status", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = AllComponentUIFieldsResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response getAllComponentUIFields() {

        return delegate.getAllComponentUIFields();
    }

    @Valid
    @GET
    @Path("/configs/{id}")
    
    @Produces({ "application/json", "application/xml" })
    @ApiOperation(value = "Retrieve remotefetch Configuration by remotefetch Configuration's ID ", notes = "This API provides the capability to retrieve the Remote Fetch Configuration details by using its ID.<br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = RemoteFetchConfigurationGetResponse.class, tags={ "Remote Fetch Configurations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = RemoteFetchConfigurationGetResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response getRemoteFetchConfig(@ApiParam(value = "ID of the remotefetch Configuration.",required=true) @PathParam("id") String id) {

        return delegate.getRemoteFetchConfig(id );
    }

    @Valid
    @GET
    @Path("/configs")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "List remote fetch configurations ", notes = "This API provides the capability to retrieve the list of remotefetch configurations.<br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = RemoteFetchConfigurationListResponse.class, tags={ "Remote Fetch Configurations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = RemoteFetchConfigurationListResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class),
        @ApiResponse(code = 501, message = "Not Implemented", response = Error.class)
    })
    public Response getRemoteFetchConfigs(    @Valid@ApiParam(value = "Maximum number of records to return. ")  @QueryParam("limit") Integer limit,     @Valid@ApiParam(value = "Number of records to skip for pagination. ")  @QueryParam("offset") Integer offset) {

        return delegate.getRemoteFetchConfigs(limit,  offset );
    }

    @Valid
    @GET
    @Path("/configs/{id}/status")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Deployment status of remotefetch configuration. ", notes = "This API provides the status of remote fetch configuration. The status hold deployment status and the deployment failure details . <br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = StatusListResponse.class, tags={ "Remote fetch Configuration Status", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = StatusListResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response getStatus(@ApiParam(value = "ID of the identity provider.",required=true) @PathParam("id") String id) {

        return delegate.getStatus(id );
    }

    @Valid
    @POST
    @Path("/configs/{id}/trigger")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Trigger a remote fetch configuration. ", notes = "This API pprovides the capability to trigger given remotefetch configuration. <br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = Void.class, tags={ "Trigger", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successfully created.", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response triggerRemoteFetch(@ApiParam(value = "ID of the identity provider.",required=true) @PathParam("id") String id) {

        return delegate.triggerRemoteFetch(id );
    }

    @Valid
    @PATCH
    @Path("/configs/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Patch a remotefetch Configuration property by ID. ", notes = "This API provides the capability to update a remotefetch Configuration property using patch request. Patch is supported only for key-value pairs. <br> <b>Permission required:</b> <br>     * None <br> <b>Scope required:</b> <br>     * internal_login ", response = Void.class, tags={ "Remote Fetch Configurations" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully Updated", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response updateRemoteFetchConfig(@ApiParam(value = "ID of the remotefetch Configuration.",required=true) @PathParam("id") String id, @ApiParam(value = "" ,required=true) @Valid RemoteFetchConfigurationPatchRequest remoteFetchConfigurationPatchRequest) {

        return delegate.updateRemoteFetchConfig(id,  remoteFetchConfigurationPatchRequest );
    }

}
