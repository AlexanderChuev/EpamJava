package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IUserService;

public class UserServiceTest extends AbstractTest {

	@Inject
	private IUserService userService;
	
	@Test
	public void test() {
		Assert.notNull(userService);
	}

	@Test
	public void findUserCredentialsTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		userService.saveUser(userProfile, userCredentials);
		UserCredentials userCredentialsFromDb = userService.findUserCredentials(userCredentials.getEmail(),
				userCredentials.getPassword());

		Assert.notNull(userCredentialsFromDb);
		Assert.notNull(userCredentialsFromDb.getId());
		Assert.notNull(userCredentialsFromDb.getEmail());
		Assert.notNull(userCredentialsFromDb.getPassword());
		Assert.notNull(userCredentialsFromDb.getUserRole());

		Assert.isTrue(userCredentialsFromDb.getEmail().equals(userCredentials.getEmail()));
		Assert.isTrue(userCredentialsFromDb.getPassword().equals(userCredentials.getPassword()));
		Assert.isTrue(userCredentialsFromDb.getUserRole().equals(userCredentials.getUserRole()));

	}

	@Test
	public void getCredentialsTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		UserProfile userProfileFromDb = userService.saveUser(userProfile, userCredentials);
		Integer userCredentialsId = userProfileFromDb.getUserCredentialsId();
		UserCredentials userCredentialsFromDb = userService.getCredentials(userCredentialsId);

		Assert.notNull(userCredentialsFromDb);
		Assert.notNull(userCredentialsFromDb.getId());
		Assert.notNull(userCredentialsFromDb.getEmail());
		Assert.notNull(userCredentialsFromDb.getPassword());
		Assert.notNull(userCredentialsFromDb.getUserRole());

		Assert.isTrue(userCredentialsFromDb.getEmail().equals(userCredentials.getEmail()));
		Assert.isTrue(userCredentialsFromDb.getPassword().equals(userCredentials.getPassword()));
		Assert.isTrue(userCredentialsFromDb.getUserRole().equals(userCredentials.getUserRole()));
	}

	@Test
	public void updateUserCredentialsTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		UserProfile userProfileFromDb = userService.saveUser(userProfile, userCredentials);
		Integer userCredentialsId = userProfileFromDb.getUserCredentialsId();
		UserCredentials userCredentialsFromDb = userService.getCredentials(userCredentialsId);

		userCredentialsFromDb.setEmail("email" + new Date().getTime());
		userCredentialsFromDb.setUserRole(UserRole.ADMIN);

		userService.update(userCredentialsFromDb);

		UserCredentials modifiedUserCredentialsFromDb = userService.getCredentials(userCredentialsId);

		Assert.notNull(modifiedUserCredentialsFromDb);
		Assert.notNull(modifiedUserCredentialsFromDb.getId());
		Assert.notNull(modifiedUserCredentialsFromDb.getEmail());
		Assert.notNull(modifiedUserCredentialsFromDb.getPassword());
		Assert.notNull(modifiedUserCredentialsFromDb.getUserRole());

		Assert.isTrue(modifiedUserCredentialsFromDb.getId().equals(userCredentialsFromDb.getId()));
		Assert.isTrue(modifiedUserCredentialsFromDb.getEmail().equals(userCredentialsFromDb.getEmail()));
		Assert.isTrue(modifiedUserCredentialsFromDb.getPassword().equals(userCredentialsFromDb.getPassword()));
		Assert.isTrue(modifiedUserCredentialsFromDb.getUserRole().equals(userCredentialsFromDb.getUserRole()));

	}

	@Test
	public void saveUserTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();

		UserProfile userFromDb = userService.saveUser(userProfile, userCredentials);

		Assert.notNull(userFromDb, "The userFromDb must not be null");
		Assert.notNull(userFromDb.getId(), "The id from userFromDb must not be null");
		Assert.notNull(userFromDb.getFirstName(), "The FirstName from userFromDb must not be null");
		Assert.notNull(userFromDb.getLastName(), "The LastName from userFromDb must not be null");
		Assert.notNull(userFromDb.getUserCredentialsId(), "The UserCredentialsId from userFromDb must not be null");

	}

	@Test(expected = DuplicateKeyException.class)
	public void saveUserIsUniqueTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile1 = createUserProfile();
		UserProfile userProfile2 = createUserProfile();

		userService.saveUser(userProfile1, userCredentials);
		userService.saveUser(userProfile2, userCredentials);

	}

	@Test
	public void getUserProfileTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();

		UserProfile userFromDb = userService.saveUser(userProfile, userCredentials);
		UserProfile userProfileFromDb = userService.getUserProfile(userFromDb.getId());

		Assert.notNull(userProfileFromDb, "The userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getId(), "The id from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getFirstName(), "The FirstName from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getLastName(), "The LastName from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getUserCredentialsId(),
				"The UserCredentialsId from userFromDb must not be null");
	}

	@Test
	public void getAllTest() {

		List<UserProfile> allUserProfile = userService.getAll();

		for (UserProfile userProfileFromDb : allUserProfile) {
			Assert.notNull(userProfileFromDb, "The userFromDb must not be null");
			Assert.notNull(userProfileFromDb.getId(), "The id from userFromDb must not be null");
			Assert.notNull(userProfileFromDb.getFirstName(), "The FirstName from userFromDb must not be null");
			Assert.notNull(userProfileFromDb.getLastName(), "The LastName from userFromDb must not be null");
			Assert.notNull(userProfileFromDb.getUserCredentialsId(),
					"The UserCredentialsId from userFromDb must not be null");
		}
	}

	@Test
	public void updateUserProfileTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		UserProfile userProfileFromDb = userService.saveUser(userProfile, userCredentials);

		userProfileFromDb.setFirstName("FirstName" + new Date().getTime());
		userProfileFromDb.setLastName("LastName" + new Date().getTime());

		userService.update(userProfileFromDb);

		UserProfile modifiedUserProfileFromDb = userService.getUserProfile(userProfileFromDb.getId());

		Assert.notNull(modifiedUserProfileFromDb);
		Assert.notNull(modifiedUserProfileFromDb.getId());
		Assert.notNull(modifiedUserProfileFromDb.getFirstName());
		Assert.notNull(modifiedUserProfileFromDb.getLastName());
		Assert.notNull(modifiedUserProfileFromDb.getUserCredentialsId());

		Assert.isTrue(modifiedUserProfileFromDb.getId().equals(userProfileFromDb.getId()));
		Assert.isTrue(modifiedUserProfileFromDb.getFirstName().equals(userProfileFromDb.getFirstName()));
		Assert.isTrue(modifiedUserProfileFromDb.getLastName().equals(userProfileFromDb.getLastName()));
		Assert.isTrue(
				modifiedUserProfileFromDb.getUserCredentialsId().equals(userProfileFromDb.getUserCredentialsId()));
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void delete() { 
		
		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		UserProfile userProfileFromDb = userService.saveUser(userProfile, userCredentials);
		
		Integer id = userProfileFromDb.getId();
		
		userService.delete(id);
		userService.getUserProfile(id);
		
	}

}
