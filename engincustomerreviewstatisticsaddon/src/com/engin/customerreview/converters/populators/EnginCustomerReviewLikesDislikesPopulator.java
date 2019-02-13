/**
 *
 */
package com.engin.customerreview.converters.populators;

import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import reactor.util.Assert;


/**
 * @author Engin Volkan
 *
 */
public class EnginCustomerReviewLikesDislikesPopulator implements Populator<CustomerReviewModel, ReviewData>
{
	@Override
	public void populate(final CustomerReviewModel source, final ReviewData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		final UserModel currentUser = getUserService().getCurrentUser();

		Set<UserModel> customers = source.getCustomersLiked();
		if (!CollectionUtils.isEmpty(customers))
		{
			target.setNumberOfLikes(source.getCustomersLiked().size());
			if (customers.contains(currentUser))
			{
				target.setIsUserAlreadyLiked(true);
			}
			else
			{
				target.setIsUserAlreadyLiked(false);
			}
		}
		else
		{
			target.setNumberOfLikes(0);
			target.setIsUserAlreadyLiked(false);
		}

		customers = source.getCustomersDisliked();
		if (!CollectionUtils.isEmpty(customers))
		{
			target.setNumberOfDislikes(source.getCustomersDisliked().size());
			if (customers.contains(currentUser))
			{
				target.setIsUserAlreadyDisliked(true);
			}
			else
			{
				target.setIsUserAlreadyDisliked(false);
			}
		}
		else
		{
			target.setNumberOfDislikes(0);
			target.setIsUserAlreadyDisliked(false);
		}
	}

	private UserService userService;

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
