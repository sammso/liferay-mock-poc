package com.sohlman.mockpoc.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.liferay.portal.kernel.test.NewClassLoaderJUnitTestRunner;
import com.sohlman.mockpoc.service.Service;
import com.sohlman.mockpoc.service.ServiceUtil;

/**
 * To Verify that NewClassLoaderJUnitTestRunner works with different test on same test class
 * 
 * @author Sampsa Sohlman
 */
@RunWith(NewClassLoaderJUnitTestRunner.class)
public class ClassLoaderTwoTest {
	@Test
	public void testThree() {
		Service service = Mockito.mock(Service.class);
		
		Mockito.when(service.getName()).thenReturn("three");
		ServiceUtil.setService(service);
		
		Assert.assertSame("three", ServiceUtil.getName());
	}
}
