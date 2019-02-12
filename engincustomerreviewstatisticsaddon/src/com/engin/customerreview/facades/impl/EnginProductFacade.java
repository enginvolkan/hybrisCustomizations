/**
 *
 */
package com.engin.customerreview.facades.impl;

import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

import org.junit.Assert;

import com.engin.customerreview.service.impl.EnginCustomerReviewService;


/**
 * @author Engin Volkan
 *
 */
public class EnginProductFacade extends DefaultProductFacade
{
	private EnginCustomerReviewService enginCustomerReviewService;

	/**
	 * @return the enginCustomerReviewService
	 */
	public EnginCustomerReviewService getEnginCustomerReviewService()
	{
		return enginCustomerReviewService;
	}

	/**
	 * @param enginCustomerReviewService
	 *           the enginCustomerReviewService to set
	 */
	public void setEnginCustomerReviewService(final EnginCustomerReviewService enginCustomerReviewService)
	{
		this.enginCustomerReviewService = enginCustomerReviewService;
	}

	public boolean customerLikedReview(final CustomerReviewModel review, final CustomerData customerData)
	{
		Assert.assertNotNull("ReviewModel parameter can not be null", review);
		Assert.assertNotNull("CustomerData parameter can not be null", customerData);
		return getEnginCustomerReviewService().customerLikedReview(review, getUserService().getUserForUID(customerData.getUid()));

	}

	public boolean customerDislikedReview(final CustomerReviewModel review, final CustomerData customerData)
	{
		Assert.assertNotNull("ReviewModel parameter can not be null", review);
		Assert.assertNotNull("CustomerData parameter can not be null", customerData);
		return getEnginCustomerReviewService().customerDislikedReview(review,
				getUserService().getUserForUID(customerData.getUid()));

	}
}
