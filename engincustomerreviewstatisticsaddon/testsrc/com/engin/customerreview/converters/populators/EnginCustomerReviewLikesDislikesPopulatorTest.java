/**
 *
 */
package com.engin.customerreview.converters.populators;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Engin Volkan
 *
 */

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class EnginCustomerReviewLikesDislikesPopulatorTest
{
	@Mock
	private UserService userService;
	@Mock
	private UserModel user;
	private CustomerReviewModel customerReview;
	private ReviewData reviewData;
	@InjectMocks
	private EnginCustomerReviewLikesDislikesPopulator enginCustomerReviewLikesDislikesPopulator;

	final static String UID = "1234";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		reviewData = new ReviewData();
		customerReview = new CustomerReviewModel();
		final HashSet<UserModel> currentLikedCustomers = new HashSet<UserModel>();
		user.setUid(UID);
		currentLikedCustomers.add(user);
		customerReview.setCustomersLiked(currentLikedCustomers);

		Mockito.when(userService.getCurrentUser()).thenReturn(user);
	}

	/**
	 * Test method for {@link com.engin.customerreview.converters.populators.EnginCustomerReviewLikesDislikesPopulator#populate(de.hybris.platform.customerreview.model.CustomerReviewModel, de.hybris.platform.commercefacades.product.data.ReviewData)}.
	 */
	@Test
	public void testPopulate()
	{
		enginCustomerReviewLikesDislikesPopulator.populate(customerReview, reviewData);
		Assert.assertEquals("Number of likes is not correct", 1, reviewData.getNumberOfLikes());
		Assert.assertEquals("Number of dislikes is not correct", 0, reviewData.getNumberOfDislikes());
		Assert.assertEquals("Customer is not in liked customers list", true, reviewData.isIsUserAlreadyLiked());
		Assert.assertEquals("Customer is in disliked customers list", false, reviewData.isIsUserAlreadyDisliked());



		;
	}

}
