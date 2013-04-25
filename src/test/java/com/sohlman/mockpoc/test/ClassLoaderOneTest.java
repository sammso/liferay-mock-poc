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
public class ClassLoaderOneTest {
	@Test
	public void testOne() {
		Service service = Mockito.mock(Service.class);
		
		Mockito.when(service.getName()).thenReturn("one");
		ServiceUtil.setService(service);
		
		Assert.assertSame("one", ServiceUtil.getName());
	}
	
	@Test
	public void testTwo() {
		Service service = Mockito.mock(Service.class);
		
		Mockito.when(service.getName()).thenReturn("two");
		ServiceUtil.setService(service);
		
		// Not same since mock has to be done per unit test class
		
		Assert.assertSame("two", ServiceUtil.getName());
	}	
}
