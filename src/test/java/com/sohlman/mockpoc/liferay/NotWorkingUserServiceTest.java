package com.sohlman.mockpoc.liferay;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;


/**
 * This is just example that what happens if the NewClassLoaderJUnitTestRunner is not used.
 * 
 * @author Sampsa Sohlman
 */
public class NotWorkingUserServiceTest {
	
	@Test
	@Ignore
	public void testOne() throws Exception {	
		User userMock = Mockito.mock(User.class);
		
		Mockito.when(userMock.getFullName()).thenReturn("You Sir");
		UserLocalService userLocalServiceMock = Mockito.mock(UserLocalService.class);
		
		Mockito.when(userLocalServiceMock.getUserByScreenName(Mockito.eq(_COMPANY_ID), Mockito.eq(_SCREENNAME))).thenReturn(userMock);
		
		BeanLocator beanLocator = Mockito.mock(BeanLocator.class);
		Mockito.when(beanLocator.locate(Mockito.eq(UserLocalService.class.getName()))).thenReturn(userLocalServiceMock);
		Mockito.when(beanLocator.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
	
		PortalBeanLocatorUtil.setBeanLocator(beanLocator);
		
		Assert.assertEquals("You Sir", UserLocalServiceUtil.getUserByScreenName(_COMPANY_ID, _SCREENNAME).getFullName());
	}
	
	@Test
	@Ignore
	public void testTwo() throws Exception {
		User userMock = Mockito.mock(User.class);
		
		Mockito.when(userMock.getFullName()).thenReturn("Ad Min");
		
		
		UserLocalService userLocalServiceMock = Mockito.mock(UserLocalService.class);
		
		Mockito.when(userLocalServiceMock.getUserByScreenName(Mockito.eq(_COMPANY_ID), Mockito.eq(_SCREENNAME))).thenReturn(userMock);
		
		BeanLocator beanLocator = Mockito.mock(BeanLocator.class);
		Mockito.when(beanLocator.locate(Mockito.eq(UserLocalService.class.getName()))).thenReturn(userLocalServiceMock);
		Mockito.when(beanLocator.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
		
		// Set the mocked bean locator to PortalBeanLocatorUtil
		PortalBeanLocatorUtil.setBeanLocator(beanLocator);
		
		// This is not samme value as 
		Assert.assertEquals("You Sir", UserLocalServiceUtil.getUserByScreenName(_COMPANY_ID, _SCREENNAME).getFullName());
	}	
	
	private long _COMPANY_ID = 10105L;
	private String _SCREENNAME = "yousir";
}
