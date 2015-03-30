package org.opentdc.resources;

import javax.servlet.ServletContext;

import org.opentdc.exception.NotImplementedException;
import org.opentdc.util.ServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageFactory {
	private static Logger logger = LoggerFactory
			.getLogger("org.opentdc.resources.StorageFactory");

	public static StorageProvider getStorageProvider(ServletContext context) {
		ServiceType _storageType = ServiceType.getStorageType(context
				.getInitParameter("storage.type"));
		logger.info("> getStorageProvider(): using storageType " + _storageType);

		StorageProvider _sp = null;
		switch (_storageType) {
		case TRANSIENT:
			throw new NotImplementedException(
				"Resource service is not yet implemented with transient storage");
		case FILE:
			throw new NotImplementedException(
				"Resource service is not yet implemented with file storage");
		case OPENCRX:
			throw new NotImplementedException(
					"Resource service is not yet implemented with OpenCRX storage");
		case MONGODB:
			throw new NotImplementedException(
					"Resource services is not yet implemented with MongoDB storage");
		default:
			throw new NotImplementedException(
					"Resource services has unknown storage type <" + _storageType
							+ "> configured.");
		}
		//return _sp;
	}
}
