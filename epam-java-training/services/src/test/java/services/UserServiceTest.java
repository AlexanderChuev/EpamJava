package services;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;

public class UserServiceTest extends AbstractTesst {

	@Test
	public void test() {
		Assert.notNull(userService, "The userService must not be null");
	}

	@Test
	public void registrationTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();

		Integer registrationID = userService.registration(userProfile, userCredentials);
		Assert.notNull(registrationID, "not null");
		
		UserProfile userProfileFromDb = userService.getUserProfile(registrationID);
		
		
		Assert.notNull(userProfileFromDb, "The userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getId(), "The id from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getFirstName(), "The FirstName from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getLastName(), "The LastName from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getUserCredentialsId(),
				"The UserCredentialsId from userFromDb must not be null");

	}

	@Test
	public void getCredentialsTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		Integer userCredentialsId = userService.registration(userProfile, userCredentials);
		UserCredentials userCredentialsFromDb = userService.getUserCredentials(userCredentialsId);

		checkUserCredentials(userCredentialsFromDb, userCredentials);
	}

	@Test
	public void updateUserCredentialsTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		Integer userCredentialsId = userService.registration(userProfile, userCredentials);

		UserCredentials userCredentialsFromDb = userService.getUserCredentials(userCredentialsId);

		userCredentialsFromDb.setEmail("new email" + new Date().getTime());
		userCredentialsFromDb.setUserRole(UserRole.ADMIN);

		userService.update(userCredentialsFromDb);

		UserCredentials modifiedUserCredentialsFromDb = userService.getUserCredentials(userCredentialsId);

		checkUserCredentials(modifiedUserCredentialsFromDb, userCredentialsFromDb);
		Assert.isTrue(modifiedUserCredentialsFromDb.getId().equals(userCredentialsFromDb.getId()), "");

	}

	@Test(expected = DuplicateKeyException.class)
	public void registrationUniqueTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile1 = createUserProfile();
		UserProfile userProfile2 = createUserProfile();

		userService.registration(userProfile1, userCredentials);
		userService.registration(userProfile2, userCredentials);

	}

	@Test
	public void getUserProfileTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();

		Integer registrationId = userService.registration(userProfile, userCredentials);
		UserProfile userProfileFromDb = userService.getUserProfile(registrationId);

		Assert.notNull(userProfileFromDb, "The userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getId(), "The id from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getFirstName(), "The FirstName from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getLastName(), "The LastName from userFromDb must not be null");
		Assert.notNull(userProfileFromDb.getUserCredentialsId(),
				"The UserCredentialsId from userFromDb must not be null");
		// + check on equals
	}

	@Test
	public void getAllTest() {

		CommonFilter commonFilter = new CommonFilter(1, 2, "first_name", "asc");
		List<UserProfile> allUserProfile = userService.getAll(commonFilter);

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
		Integer registrationId = userService.registration(userProfile, userCredentials);
		
		UserProfile userProfileFromDb = userService.getUserProfile(registrationId);

		userProfileFromDb.setFirstName("FirstName" + new Date().getTime());
		userProfileFromDb.setLastName("LastName" + new Date().getTime());

		userService.update(userProfileFromDb);

		UserProfile modifiedUserProfileFromDb = userService.getUserProfile(userProfileFromDb.getId());

		Assert.notNull(modifiedUserProfileFromDb, "");
		Assert.notNull(modifiedUserProfileFromDb.getId(), "");
		Assert.notNull(modifiedUserProfileFromDb.getFirstName(), "");
		Assert.notNull(modifiedUserProfileFromDb.getLastName(), "");
		Assert.notNull(modifiedUserProfileFromDb.getUserCredentialsId(), "");

		Assert.isTrue(modifiedUserProfileFromDb.getId().equals(userProfileFromDb.getId()), "");
		Assert.isTrue(modifiedUserProfileFromDb.getFirstName().equals(userProfileFromDb.getFirstName()), "");
		Assert.isTrue(modifiedUserProfileFromDb.getLastName().equals(userProfileFromDb.getLastName()), "");
		Assert.isTrue(modifiedUserProfileFromDb.getUserCredentialsId().equals(userProfileFromDb.getUserCredentialsId()),
				"");
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteUserTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		Integer registrationId = userService.registration(userProfile, userCredentials);


		userService.delete(registrationId);
		userService.getUserProfile(registrationId);

	}

	@Test
	public void findUserCredentialsTest() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();
		userService.registration(userProfile, userCredentials);
		UserCredentials userCredentialsFromDb = userService.getByEmailAndPassword(userCredentials.getEmail(),
				userCredentials.getPassword());

		checkUserCredentials(userCredentialsFromDb, userCredentials);

	}

	private void checkUserCredentials(UserCredentials userCredentialsFirst, UserCredentials userCredentialsSecond) {
		Assert.notNull(userCredentialsFirst, "");
		Assert.notNull(userCredentialsFirst.getId(), "");
		Assert.notNull(userCredentialsFirst.getEmail(), "");
		Assert.notNull(userCredentialsFirst.getPassword(), "");
		Assert.notNull(userCredentialsFirst.getUserRole(), "");

		Assert.isTrue(userCredentialsFirst.getEmail().equals(userCredentialsSecond.getEmail()), "");
		Assert.isTrue(userCredentialsFirst.getPassword().equals(userCredentialsSecond.getPassword()), "");
		Assert.isTrue(userCredentialsFirst.getUserRole().equals(userCredentialsSecond.getUserRole()), "");
	}

}
