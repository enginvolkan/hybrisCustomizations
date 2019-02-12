/**
 *
 */
package com.engin.customerreview.facades.impl;

import static org.junit.Assert.assertFalse;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.user.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.engin.customerreview.service.impl.EnginCustomerReviewService;


/**
 * @author Engin Volkan
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class EnginProductFacadeTest
{
	@Mock
	public UserService userService;
	@Mock
	public EnginCustomerReviewService enginCustomerReviewService;
	@InjectMocks
	public EnginProductFacade enginProductFacade;
	@Mock
	public CustomerData customerData;
	@Mock
	public CustomerData corruptedCustomerData;

	public CustomerModel customerModel;
	public CustomerReviewModel customerReview;
	final static String CUSTOMER_UID = "1234";
	final static String CUSTOMER_UID2 = "123455";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		customerReview = new CustomerReviewModel();
		customerModel = new CustomerModel();

		Mockito.when(customerData.getUid()).thenReturn(CUSTOMER_UID);
		Mockito.when(corruptedCustomerData.getUid()).thenReturn(CUSTOMER_UID2);
		Mockito.when(userService.getUserForUID(CUSTOMER_UID)).thenReturn(customerModel);
		Mockito.when(userService.getUserForUID(CUSTOMER_UID2)).thenReturn(null);

		Mockito.when(enginCustomerReviewService.customerLikedReview(customerReview, customerModel)).thenReturn(true);
		Mockito.when(enginCustomerReviewService.customerDislikedReview(customerReview, customerModel)).thenReturn(true);

	}

	@Test
	public void testCustomerLikedReview()
	{
		enginProductFacade.customerLikedReview(customerReview, customerData);
		Mockito.verify(enginCustomerReviewService).customerLikedReview(customerReview, customerModel);
	}

	@Test
	public void testCustomerLikedReviewWithCorruptedCustomerData()
	{
		assertFalse("Review customer like is recorder for a corrupted customer data!",
				enginProductFacade.customerLikedReview(customerReview, corruptedCustomerData));
	}

	@Test
	public void testCustomerDislikedReview()
	{
		enginProductFacade.customerDislikedReview(customerReview, customerData);
		Mockito.verify(enginCustomerReviewService).customerDislikedReview(customerReview, customerModel);
	}

	@Test
	public void testCustomerDislikedReviewWithCorruptedCustomerData()
	{
		assertFalse("Review customer like is recorder for a corrupted customer data!",
				enginProductFacade.customerDislikedReview(customerReview, corruptedCustomerData));
	}
}
