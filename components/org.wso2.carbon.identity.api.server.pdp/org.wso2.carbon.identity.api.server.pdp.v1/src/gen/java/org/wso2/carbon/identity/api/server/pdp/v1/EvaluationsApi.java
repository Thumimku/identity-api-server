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
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.BatchAccessEvaluationResponse;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/evaluations")
@Api(description = "The evaluations API")

public class EvaluationsApi  {


    private EvaluationsApiService delegate;

    public EvaluationsApi() {

        this.delegate = EvaluationsApiServiceFactory.getEvaluationsApi();
    }
    @Valid
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Batch Access Evaluation", notes = "Evaluate multiple access requests in a single batch operation. ", response = BatchAccessEvaluationResponse.class, tags={ "Evaluations" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Batch access evaluation response object.", response = BatchAccessEvaluationResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Void.class)
    })
    public Response evaluateBatchAccess(@ApiParam(value = "Batch access evaluation request object." ,required=true) @Valid BatchAccessEvaluationRequest batchAccessEvaluationRequest) {

        return delegate.evaluateBatchAccess(batchAccessEvaluationRequest );
    }

}
