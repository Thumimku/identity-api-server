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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import java.io.InputStream;
import java.util.List;

import org.wso2.carbon.identity.api.server.pdp.v1.factories.EvaluationApiServiceFactory;
import org.wso2.carbon.identity.api.server.pdp.v1.model.AccessEvaluationRequest;
import org.wso2.carbon.identity.api.server.pdp.v1.model.AccessEvaluationResponse;
import org.wso2.carbon.identity.api.server.pdp.v1.EvaluationApiService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/evaluation")
@Api(description = "The evaluation API")

public class EvaluationApi  {

    private static final Log LOG = LogFactory.getLog(EvaluationApi.class);

    private EvaluationApiService delegate;

    public EvaluationApi() {

        try {
            // Risky init logic
            this.delegate = EvaluationApiServiceFactory.getEvaluationApi();
        } catch (Exception e) {
            LOG.error("Error initializing EvaluationApi", e);
        }
    }

    @Valid
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Single Access Evaluation", notes = "Evaluate whether a subject is authorized to perform an action on a resource. ", response = AccessEvaluationResponse.class, tags={ "Evaluations" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Access evaluation response object.", response = AccessEvaluationResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Void.class)
    })
    public Response evaluateAccess(@ApiParam(value = "Access evaluation request object." ,required=true) @Valid AccessEvaluationRequest accessEvaluationRequest) {

        return delegate.evaluateAccess(accessEvaluationRequest );
    }

}
