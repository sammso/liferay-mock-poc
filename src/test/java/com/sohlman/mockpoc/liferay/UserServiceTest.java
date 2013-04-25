package com.sohlman.mockpoc.liferay;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.test.NewClassLoaderJUnitTestRunner;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * 
 * This example shows how to Mock Liferay Service interface inside util classes.
 * NOTE: you have to verify that the util class is following the PortalBeanLocator pattern.
 * 
 * @author Sampsa Sohlman
 */

@RunWith(NewClassLoaderJUnitTestRunner.class)
public class UserServiceTest {
	@Test
	public void testOne() throws Exception {
		// Mock User
		
		User userMock = Mockito.mock(User.class);
		
		Mockito.when(userMock.getFullName()).thenReturn("You Sir");
		
		// Mock User Local Service
		UserLocalService userLocalServiceMock = Mockito.mock(UserLocalService.class);
		
		// Mock Return Value
		
		Mockito.when(userLocalServiceMock.getUserByScreenName(Mockito.eq(_COMPANY_ID), Mockito.eq(_SCREENNAME))).thenReturn(userMock);
		
		//
		// Set the UserServiceUtil's through the PortalBeanLocator 
		// 
		
		// Mock BeanLocator to mock
		
		BeanLocator beanLocator = Mockito.mock(BeanLocator.class);
		Mockito.when(beanLocator.locate(Mockito.eq(UserLocalService.class.getName()))).thenReturn(userLocalServiceMock);
		Mockito.when(beanLocator.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
		
		// Set the mocked bean locator to PortalBeanLocatorUtil
		PortalBeanLocatorUtil.setBeanLocator(beanLocator);
		
		Assert.assertEquals("You Sir", UserLocalServiceUtil.getUserByScreenName(_COMPANY_ID, _SCREENNAME).getFullName());
	}
	
	@Test
	public void testTwo() throws Exception {
		// Mock User
		
		User userMock = Mockito.mock(User.class);
		
		Mockito.when(userMock.getFullName()).thenReturn("Ad Min");
		
		// Mock User Local Service
		UserLocalService userLocalServiceMock = Mockito.mock(UserLocalService.class);
		
		// Mock Return Value
		
		Mockito.when(userLocalServiceMock.getUserByScreenName(Mockito.eq(_COMPANY_ID), Mockito.eq(_SCREENNAME))).thenReturn(userMock);
		
		//
		// Set the UserServiceUtil's through the PortalBeanLocator 
		// 
		
		// Mock BeanLocator to mock
		
		BeanLocator beanLocator = Mockito.mock(BeanLocator.class);
		Mockito.when(beanLocator.locate(Mockito.eq(UserLocalService.class.getName()))).thenReturn(userLocalServiceMock);
		Mockito.when(beanLocator.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
		
		// Set the mocked bean locator to PortalBeanLocatorUtil
		PortalBeanLocatorUtil.setBeanLocator(beanLocator);
		
		Assert.assertEquals("Ad Min", UserLocalServiceUtil.getUserByScreenName(_COMPANY_ID, _SCREENNAME).getFullName());
	}	
	
	private long _COMPANY_ID = 10105L;
	private String _SCREENNAME = "yousir";
}
