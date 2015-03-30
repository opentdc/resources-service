package org.opentdc.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StorageProvider {
	protected static Map<String, ResourceModel> index = null;

	// instance variables
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public void initStorageProvider() {
		logger.info("> initStorageProvider()");

		if (index == null) {
			index = new HashMap<String, ResourceModel>();
		}

		logger.info("initStorageProvider() initialized");
	}

	/************************* resources *****************************/
	public abstract ArrayList<ResourceModel> listResources();

	public abstract ResourceModel createResource(ResourceModel resource) throws DuplicateException;

	public abstract ResourceModel readResource(String id) throws NotFoundException;

	public abstract ResourceModel updateResource(ResourceModel resource) throws NotFoundException;

	public abstract void deleteResource(String id) throws NotFoundException;

	public abstract int countResources();

}
