package org.opentdc.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.NotFoundException;
import org.opentdc.exception.NotImplementedException;
import org.opentdc.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoImpl extends StorageProvider {
	private static String url = null;
	private static String userName = null;
	private static String password = null;
	private static String providerName = null;

	// instance variables

	public MongoImpl(ServletContext context) {
		logger.info("> MongoImpl()");

		super.initStorageProvider();

		if (url == null) {
			url = context.getInitParameter("backend.url");
		}
		if (userName == null) {
			userName = context.getInitParameter("backend.userName");
		}
		if (password == null) {
			password = context.getInitParameter("backend.password");
		}
		if (providerName == null) {
			providerName = context.getInitParameter("backend.providerName");  // in MongoDB terms: the DB-name
		}

		logger.info("MongoImpl() initialized");
	}

	/******************************** resource *****************************************/
	/**
	 * List all resources.
	 * 
	 * @return a list of all resources.
	 */
	@Override
	public ArrayList<ResourceModel> listResources() {
		// TODO: implement listResources
		logger.info("listResources() -> " + countResources() + " resources");
		throw new NotImplementedException("listResources is not yet implemented for storage in MongoDB");
	}

	/**
	 * Create a new Resource.
	 * 
	 * @param resource
	 * @return the newly created resource (can be different than resource param)
	 * @throws DuplicateException
	 *             if a resource with the same ID already exists.
	 */
	@Override
	public ResourceModel createResource(ResourceModel resource) throws DuplicateException {
		if (readResource(resource.getId()) != null) {
			// object with same ID exists already
			throw new DuplicateException();
		}
		// TODO: implement createResource
		logger.info("createResource() -> " + countResources() + " resources");
		throw new NotImplementedException(
			"method createResource is not yet implemented for storage in MongoDB");
		// logger.info("createResource() -> " + resource);
	}

	/**
	 * Find a Resource by ID.
	 * 
	 * @param id
	 *            the Resource ID
	 * @return the Resource
	 * @throws NotFoundException
	 *             if there exists no Resource with this ID
	 */
	@Override
	public ResourceModel readResource(String xri) throws NotFoundException {
		ResourceModel _resource = null;
		// TODO: implement readResource()
		throw new NotImplementedException(
			"method readResource() is not yet implemented for storage in MongoDB");
		// logger.info("readResource(" + xri + ") -> " + _resource);
	}

	@Override
	public ResourceModel updateResource(ResourceModel resource) throws NotFoundException {
		ResourceModel _resource = null;
		// TODO implement updateResource()
		throw new NotImplementedException(
				"method updateResource() is not yet implemented for storage in MongoDB.");
	}

	@Override
	public void deleteResource(String id) throws NotFoundException {
		// TODO implement deleteResource()
		throw new NotImplementedException(
				"method deleteResource() is not yet implemented for storage in MongoDB.");
	}

	@Override
	public int countResources() {
		int _count = -1;
		// TODO: implement countResources()
		throw new NotImplementedException(
				"method countResources() is not yet implemented for storage in MongoDB.");
		// logger.info("countResources() = " + _count);
	}


	/******************************** utility methods *****************************************/

}
