/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arbalo AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.opentdc.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.opentdc.service.GenericService;
import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.InternalServerErrorException;
import org.opentdc.service.exception.NotFoundException;
import org.opentdc.service.exception.ValidationException;

@Path("/api/resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesService extends GenericService<ServiceProvider> {
	
	private ServiceProvider sp = null;

	// instance variables
	private static final Logger logger = Logger.getLogger(ResourcesService.class.getName());

	/**
	 * Invoked for each service invocation (Constructor).
	 * 
	 * @throws ReflectiveOperationException 
	 */
	public ResourcesService(
		@Context ServletContext context
	) throws ReflectiveOperationException {
		logger.info("> ResourcesService()");
		if (sp == null) {
			sp = this.getServiceProvider(ResourcesService.class, context);
		}
		logger.info("ResourcesService() initialized");
	}

	/******************************** resource *****************************************/
	@GET
	@Path("/")
	public List<ResourceModel> listResources(
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size			
	) {
		return sp.listResources(queryType, query, position, size);
	}

	@POST
	@Path("/")
	public ResourceModel createResource(
			ResourceModel resource) 
		throws DuplicateException, ValidationException {
		return sp.createResource(resource);
	}

	@GET
	@Path("/{resourceId}")
	public ResourceModel readResource(
			@PathParam("resourceId") String resourceId) 
		throws NotFoundException {
		return sp.readResource(resourceId);
	}

	@PUT
	@Path("/{resourceId}")
	public ResourceModel updateResource(
		@PathParam("resourceId") String resourceId,
		ResourceModel resource
	) throws NotFoundException, ValidationException {
		return sp.updateResource(resourceId, resource);
	}

	@DELETE
	@Path("/{resourceId}")
	public void deleteResource(
			@PathParam("resourceId") String resourceId) 
		throws NotFoundException, InternalServerErrorException {
		sp.deleteResource(resourceId);
	}
	
	/********************************** rateref ***************************************/
	@GET
	@Path("/{resourceId}/rateref")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RateRefModel> listRateRefs(
		@PathParam("resourceId") String resourceId,
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) {
		return sp.listRateRefs(resourceId, query, queryType, position, size);
	}

	@POST
	@Path("/{resourceId}/rateref")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RateRefModel createRateRef(
		@PathParam("resourceId") String resourceId, 
		RateRefModel rateRef
	) throws DuplicateException, ValidationException {
		return sp.createRateRef(resourceId, rateRef);
	}
	
	@GET
	@Path("/{resourceId}/rateref/{rateRefId}")
	@Produces(MediaType.APPLICATION_JSON)
	public RateRefModel readRateRef(
		@PathParam("resourceId") String resourceId,
		@PathParam("rateRefId") String rateRefId
	) throws NotFoundException {
		return sp.readRateRef(resourceId, rateRefId);
	}

	@DELETE
	@Path("/{resourceId}/rateref/{rateRefId}")
	public void deleteRateRef(
		@PathParam("resourceId") String resourceId,
		@PathParam("rateRefId") String rateRefId
	) throws NotFoundException, InternalServerErrorException {
		sp.deleteRateRef(resourceId, rateRefId);
	}
}
