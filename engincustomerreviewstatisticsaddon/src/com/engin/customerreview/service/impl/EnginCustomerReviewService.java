/**
 *
 */
package com.engin.customerreview.service.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.impl.DefaultCustomerReviewService;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;



/**
 * @author Engin Volkan
 *
 */
public class EnginCustomerReviewService extends DefaultCustomerReviewService
{
	public boolean customerLikedReview(final CustomerReviewModel customerReview, final UserModel user)
	{
		if (customerReview == null || user == null || user.equals(customerReview.getUser())
				|| (customerReview.getCustomersDisliked() != null && customerReview.getCustomersDisliked().contains(user)))
		{
			return false;
		}

		Set<UserModel> currentCustomers = customerReview.getCustomersLiked();
		if (CollectionUtils.isEmpty(currentCustomers))
		{
			currentCustomers = new HashSet<UserModel>();
		}

		if (currentCustomers.add(user))
		{
			customerReview.setCustomersLiked(currentCustomers);
			getModelService().save(customerReview);

			return true;
		}
		else
		{
			return false;
		}


	}

	public boolean customerDislikedReview(final CustomerReviewModel customerReview, final UserModel user)
	{
		if (customerReview == null || user == null || user.equals(customerReview.getUser())
				|| (customerReview.getCustomersLiked() != null && customerReview.getCustomersLiked().contains(user)))
		{
			return false;
		}

		Set<UserModel> currentCustomers = customerReview.getCustomersDisliked();
		if (CollectionUtils.isEmpty(currentCustomers))
		{
			currentCustomers = new HashSet<UserModel>();
		}

		if (currentCustomers.add(user))
		{
			customerReview.setCustomersDisliked(currentCustomers);
			getModelService().save(customerReview);

			return true;
		}
		else
		{
			return false;
		}
	}
}
