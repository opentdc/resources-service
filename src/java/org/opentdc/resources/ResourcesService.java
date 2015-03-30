package org.opentdc.resources;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CXFNonSpringJaxrsServlet (defined in web.xml) uses Singleton as a default
 * scope for service classes specified by a jaxrs.serviceClasses servlet
 * parameter.
 * 
 * @author Bruno Kaiser
 *
 */
@Path("/api/resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesService {
	private static StorageProvider sp = null;

	// instance variables
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Invoked for each service invocation (Constructor)
	 */
	public ResourcesService(@Context ServletContext context) {
		logger.info("> ResourcesService()");
		if (sp == null) {
			sp = StorageFactory.getStorageProvider(context);
		}
		logger.info("ResourcesService() initialized");
	}

	/******************************** resource *****************************************/
	@GET
	@Path("/")
	public List<ResourceModel> listResources() {
		return sp.listResources();
	}

	@POST
	@Path("/")
	public ResourceModel createResource(ResourceModel resource) throws DuplicateException {
		return sp.createResource(resource);
	}

	@GET
	@Path("/{id}")
	public ResourceModel readResource(@PathParam("id") String id) throws NotFoundException {
		return sp.readResource(id);
	}

	@PUT
	@Path("/")
	public ResourceModel updateResource(ResourceModel resource) throws NotFoundException {
		return sp.updateResource(resource);
	}

	@DELETE
	@Path("/{id}")
	public void deleteResource(@PathParam("id") String id) throws NotFoundException {
		sp.deleteResource(id);
	}

	@GET
	@Path("/count")
	public int countResources() {
		return sp.countResources();
	}

}
