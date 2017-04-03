package services;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IUserService;

public class UserServiceTest extends AbstractTest {
	
	@Inject
	private IUserService userService;
	
	/*@Test(expected=DuplicateKeyException.class)
	public void registerTest() {
		
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setEmail("abc");
		userCredentials.setPassword("123");
		userCredentials.setUserRole(UserRole.CLIENT);
		
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName("Коля");
		userProfile.setLastName("Солод");
		
		userService.register(userProfile, userCredentials);
		
		Assert.notNull(attributeFromDb, "The attribute must not be null");

		Assert.notNull(attributeFromDb.getAttributeName(), "The name attributeFromDb must not be null");

		Assert.notNull(attributeFromDb.getValue(), "The value attributeFromDb must not be null");
		
	}

	@Test
	public UserProfile getProfile() {
		return userService.getProfile(1);
	}

	@Test
	public UserCredentials getCredentials(Integer id) {
		return userCredentialsDao.get(id);
	}

	@Test
	public void update(UserProfile profile) {
		userProfileDao.update(profile);

	}

	@Test
	public void delete(Integer id) {
		// TODO Auto-generated method stub
	}

	@Test
	public List<UserProfile> getAll() {
		return userProfileDao.getAll();
	}

	@Test
	public void update(UserCredentials credentials) {
		userCredentialsDao.update(credentials);

	}*/
}
