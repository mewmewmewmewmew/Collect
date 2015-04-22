package Tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;

import shopping.with.friends.Fragments.MainFeed;
import shopping.with.friends.Objects.Post;

/**
 * Created by Ryan Brooks on 3/31/15.
 * Tests JSON parsing in MainFeed fragment
 */
public class GetPostsFromJSON extends ApplicationTestCase<Application> {
    private static final String JSONTESTPOST = "{\"status\":true,\"posts\":[{\"_id\":\"1\",\"title\":\"TEST1\",\"price\":\"100\",\"user\":\"RYAN\",\"description\":\"TEST\",\"__v\":0,\"location\":{\"lat\":\"1\",\"long\":\"1\"},\"date\":1}]}";
    private static final String JSONTESTPOST2 = "{\"status\":true,\"posts\":[{\"_id\":\"1\",\"title\":\"TEST1\",\"price\":\"100\",\"user\":\"RYAN\",\"description\":\"TEST\",\"__v\":0,\"location\":{\"lat\":\"1\",\"long\":\"1\"},\"date\":1},{\"_id\":\"2\",\"title\":\"TEST2\",\"price\":\"200\",\"user\":\"RYAN\",\"description\":\"TEST\",\"__v\":0,\"location\":{\"lat\":\"2\",\"long\":\"2\"},\"date\":2}]}";
    private static final String JSONTESTPOST3 = "{\"status\":true,\"posts\":[{\"_id\":\"1\",\"title\":\"TEST1\",\"price\":\"100\",\"user\":\"RYAN\",\"description\":\"TEST\",\"__v\":0,\"location\":{\"lat\":\"1\",\"long\":\"1\"},\"date\":1},{\"_id\":\"2\",\"title\":\"TEST2\",\"price\":\"200\",\"user\":\"RYAN\",\"description\":\"TEST\",\"__v\":0,\"location\":{\"lat\":\"2\",\"long\":\"2\"},\"date\":2},{\"_id\":\"3\",\"title\":\"TEST3\",\"price\":\"300\",\"user\":\"RYAN\",\"description\":\"TEST\",\"__v\":0,\"location\":{\"lat\":\"3\",\"long\":\"3\"},\"date\":3}]}";

    private ArrayList<Post> postArrayTestNull;
    private ArrayList<Post> postArrayTest1;
    private ArrayList<Post> postArrayTest2;
    private ArrayList<Post> postArrayTest3;

    private Post post1;
    private Post post2;
    private Post post3;

    /**
     * Tests Json parsing of posts in MainFeed
     * @throws Throwable
     */
    public GetPostsFromJSON() throws Throwable {
        super(Application.class);
        setUpPosts();

        MainFeed mainFeed = new MainFeed();
        postArrayTestNull = mainFeed.readJSONResponse(null);
        postArrayTest1 = mainFeed.readJSONResponse(JSONTESTPOST);
        postArrayTest2 = mainFeed.readJSONResponse(JSONTESTPOST2);
        postArrayTest3 = mainFeed.readJSONResponse(JSONTESTPOST3);

        //Testing size of response arrays
        assertEquals(postArrayTestNull.size(), 0);
        assertEquals(postArrayTest1.size(), 1);
        assertEquals(postArrayTest2.size(), 2);
        assertEquals(postArrayTest3.size(), 3);

        //Testing data of first object in each
        assertEquals(postArrayTest1.get(0).getTitle(), post1.getTitle());
        assertEquals(postArrayTest2.get(0).getTitle(), post1.getTitle());
        assertEquals(postArrayTest3.get(0).getTitle(), post1.getTitle());

        //Testing data of second object in each
        assertEquals(postArrayTest2.get(1).getTitle(), post2.getTitle());
        assertEquals(postArrayTest3.get(1).getTitle(), post2.getTitle());

        //Testing data of third object in each
        assertEquals(postArrayTest3.get(2).getTitle(), post3.getTitle());
    }

    /**
     * Sets up all variables needed for the tests to run
     * Declare variables and things here.
     */
    private void setUpPosts() {

        post1 = new Post();
        post2 = new Post();
        post3 = new Post();

        post1.setUserID("RYAN");
        post2.setUserID("RYAN");
        post3.setUserID("RYAN");

        post1.setTitle("TEST1");
        post2.setTitle("TEST2");
        post3.setTitle("TEST3");

        post1.setDescription("TEST");
        post2.setDescription("TEST");
        post3.setDescription("TEST");

        post1.setPrice(100);
        post2.setPrice(200);
        post3.setPrice(300);

        post1.setLatitiude(1);
        post2.setLongitude(2);
        post3.setLongitude(3);

        post1.setLongitude(1);
        post2.setLongitude(2);
        post3.setLongitude(3);

    }
}
