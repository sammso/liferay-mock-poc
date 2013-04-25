package com.sohlman.mockpoc.service;

/**
 * @author Sampsa Sohlman
 */
public class ServiceUtil {
	
	public static String getName() {
		return _service.getName();
	}
	
	public static void setService(Service service) {
		if(_service==null) {
			_service = service;
		}
	}

	private static Service _service;
}
