package Tests;
import android.app.Application;
//import android.test.ActivityUnitTestCase;
import android.test.ApplicationTestCase;

import de.hdodenhof.circleimageview.CircleImageView;
import shopping.with.friends.Adapters.PostListviewAdapter;
import android.test.AndroidTestCase;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import shopping.with.friends.Adapters.PostListviewAdapter.Holder;
import shopping.with.friends.MainActivity;
import shopping.with.friends.Objects.Post;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by andreahu on 4/1/15.
 */
public class PostViewItemsNotNullTest extends AndroidTestCase {
    // make sure the holders are not null
    // check the types of holder's data
    // instantiate new holder object

    private PostListviewAdapter plAdapter;

    public PostViewItemsNotNullTest() {
        super();
    }

    /**
     * This is the main test method
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context c = new MainActivity();
        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        plAdapter = new PostListviewAdapter(c, posts);
        ViewGroup viewGroup = new ViewGroup(c) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {
            }
        };
        plAdapter.getView(0, null, viewGroup);
        Holder testHolder = plAdapter.getHolder();
        assertNotNull(testHolder);
        assertNotNull(plAdapter.getHolderDescription());
        assertNotNull(plAdapter.getHolderImageView());
        assertNotNull(plAdapter.getHolderPrice());
        assertNotNull(plAdapter.getHolderProfilePicture());
        assertNotNull(plAdapter.getHolderTitle());
    }
}
