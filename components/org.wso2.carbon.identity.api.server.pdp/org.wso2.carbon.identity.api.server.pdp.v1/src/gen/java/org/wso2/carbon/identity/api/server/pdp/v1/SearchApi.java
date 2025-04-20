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

package org.wso2.carbon.identity.api.server.pdp.v1;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import java.io.InputStream;
import java.util.List;

import org.wso2.carbon.identity.api.server.pdp.v1.factories.EvaluationsApiServiceFactory;
import org.wso2.carbon.identity.api.server.pdp.v1.factories.SearchApiServiceFactory;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ActionSearchRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ActionSearchResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ResourceSearchRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.ResourceSearchResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.model.SubjectSearchRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.SubjectSearchResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.SearchApiService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/search")
@Api(description = "The search API")

public class SearchApi  {

    private SearchApiService delegate;

    public SearchApi() {

        this.delegate = SearchApiServiceFactory.getSearchApi();
    }

    @Valid
    @POST
    @Path("/action")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Search for Action", notes = "Discover actions based on attributes. Returns a list of matching actions and metadata. ", response = ActionSearchResponse.class, tags={ "Search", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of matching subject identifiers and metadata.", response = ActionSearchResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Void.class)
    })
    public Response searchAction(@ApiParam(value = "resource search request based on attributes." ,required=true) @Valid ActionSearchRequest actionSearchRequest) {

        return delegate.searchAction(actionSearchRequest );
    }

    @Valid
    @POST
    @Path("/resource")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Search for resource", notes = "Discover resource(s) based on attributes. Returns a list of matching resource identifiers and metadata. ", response = ResourceSearchResponse.class, tags={ "Search", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of matching subject identifiers and metadata.", response = ResourceSearchResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Void.class)
    })
    public Response searchResource(@ApiParam(value = "resource search request based on attributes." ,required=true) @Valid ResourceSearchRequest resourceSearchRequest) {

        return delegate.searchResource(resourceSearchRequest );
    }

    @Valid
    @POST
    @Path("/subject")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Search for subjects", notes = "Discover subject(s) based on attributes. Returns a list of matching subject identifiers and metadata. ", response = SubjectSearchResponse.class, tags={ "Search" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of matching subject identifiers and metadata.", response = SubjectSearchResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Void.class)
    })
    public Response searchSubject(@ApiParam(value = "Subject search request based on attribute filters." ,required=true) @Valid SubjectSearchRequest subjectSearchRequest) {

        return delegate.searchSubject(subjectSearchRequest );
    }

}
