package Tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;

import shopping.with.friends.Fragments.Search;
import shopping.with.friends.Objects.Profile;

/**
 * Tests JSON parsing in Search fragment
 */
public class GetPostsFromJSON extends ApplicationTestCase<Application> {
    private static final String JSONTESTProfile = "{\"users\":[{\"name\":\"jackson\"\"username\":\"jackson\",\"password\":\"$2a$10$gZJi1sH.UFL.GC6NBrTpsuKQ0pg8W4LlCYrPRrA7xf1q2./XUDh7S\",\"email\":\"jackson@gmail.com\"\"followers\":[],\"following\":[],\"posts\":[]}]}";
    private ArrayList<Profile> profileArrayTest;
    private Profile profile1;

    /**
     * Tests Json parsing of posts in Search
     * @throws Throwable
     */
    public GetPostsFromJSON() throws Throwable {
        super(Application.class);
        setUpProfiles();

        Search search = new Search();
        profileArrayTest = search.readJSONResponse(JSONTESTProfile);

        // Testing size of resp arrays
        assertEquals(profileArrayTest.size(), 1);

        // Testing data of objects
        assertEquals(profileArrayTest.get(0).getName(), profile1.getName());
        assertEquals(profileArrayTest.get(0).getUsername(), profile1.getUsername());
        assertEquals(profileArrayTest.get(0).getPassword(), profile1.getPassword());
        assertEquals(profileArrayTest.get(0).getEmail(), profile1.getEmail());
    }

    /**
     * Sets up all variables needed for the tests to run
     * Declare variables and things here.
     */
    private void setUpProfiles() {
        profile1 = new Profile();

        profile.setName("jackson");
        profile.setUsername("jackson");
        profile.setPassword("$2a$10$gZJi1sH.UFL.GC6NBrTpsuKQ0pg8W4LlCYrPRrA7xf1q2./XUDh7S");
        profile.setEmail("jackson@gmail.com");
    }
}
