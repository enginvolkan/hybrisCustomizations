/**
 *
 */
package com.engin.customerreview.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.model.ModelService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Engin Volkan
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class EnginCustomerReviewServiceTest
{

	private UserModel sessionUser;
	private UserModel reviewUser;
	private ProductModel product;
	private CustomerReviewModel customerReview;

	@Mock
	ModelService modelService;

	@InjectMocks
	EnginCustomerReviewService enginCustomerReviewService;

	@Before
	public void setUp() throws Exception
	{

		sessionUser = new UserModel();
		reviewUser = new UserModel();
		product = new ProductModel();
		customerReview = new CustomerReviewModel();
		//		enginCustomerReviewService = new EnginCustomerReviewService();
	}

	/**
	 * Test method for
	 * {@link com.engin.customerreview.service.impl.EnginCustomerReviewService#customerLikedReview(de.hybris.platform.customerreview.model.CustomerReviewModel, de.hybris.platform.core.model.user.CustomerModel)}.
	 */
	@Test
	public void testCustomerLikedReview()
	{
		assertTrue("Customer review cannot be marked as liked!",
				enginCustomerReviewService.customerLikedReview(customerReview, sessionUser));
		assertEquals("Number of customers liked is not correct!", 1, customerReview.getCustomersLiked().size());
	}

	@Test
	public void testCustomerLikedOwnReview()
	{
		customerReview.setUser(reviewUser);
		assertFalse("Customer liked his/her own review",
				enginCustomerReviewService.customerLikedReview(customerReview, reviewUser));
	}

	@Test
	public void testCustomerLikedReviewAgain()
	{
		enginCustomerReviewService.customerLikedReview(customerReview, sessionUser);
		assertFalse("Customer liked the review again!",
				enginCustomerReviewService.customerLikedReview(customerReview, sessionUser));
	}

	@Test
	public void testCustomerLikedAfterDislike()
	{
		enginCustomerReviewService.customerDislikedReview(customerReview, sessionUser);
		assertFalse("Customer liked the review after disliking!",
				enginCustomerReviewService.customerLikedReview(customerReview, sessionUser));
	}

	/**
	 * Test method for
	 * {@link com.engin.customerreview.service.impl.EnginCustomerReviewService#customerDislikedReview(de.hybris.platform.customerreview.model.CustomerReviewModel, de.hybris.platform.core.model.user.CustomerModel)}.
	 */
	@Test
	public void testCustomerDislikedReview()
	{
		assertTrue("Customer review cannot be marked as disliked!",
				enginCustomerReviewService.customerDislikedReview(customerReview, sessionUser));
		assertEquals("Number of customers disliked is not correct!", 1, customerReview.getCustomersDisliked().size());
	}

	@Test
	public void testCustomerDislikedOwnReview()
	{
		customerReview.setUser(reviewUser);
		assertFalse("Customer disliked his/her own review",
				enginCustomerReviewService.customerDislikedReview(customerReview, reviewUser));
	}

	@Test
	public void testCustomerDislikedReviewAgain()
	{
		enginCustomerReviewService.customerDislikedReview(customerReview, sessionUser);
		assertFalse("Customer disliked the review again!",
				enginCustomerReviewService.customerDislikedReview(customerReview, sessionUser));
	}

	@Test
	public void testCustomerDislikedAfterLike()
	{
		enginCustomerReviewService.customerLikedReview(customerReview, sessionUser);
		assertFalse("Customer disliked the review after liking!",
				enginCustomerReviewService.customerDislikedReview(customerReview, sessionUser));
	}

}
