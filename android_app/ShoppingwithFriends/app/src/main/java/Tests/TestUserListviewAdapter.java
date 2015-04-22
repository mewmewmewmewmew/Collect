package Tests;

import android.app.Application;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.view.ViewGroup;

import java.util.ArrayList;

import shopping.with.friends.Adapters.UserListviewAdapter;
import shopping.with.friends.Adapters.UserListviewAdapter.Holder;
import shopping.with.friends.MainActivity;
import shopping.with.friends.Objects.Profile;


/**
 * Created by Domenico Valles on 4/1/2015.
 */

public class TestUserListviewAdapter extends ApplicationTestCase<Application> {

    private UserListviewAdapter adapter;

    public TestUserListviewAdapter() {
        super(Application.class);

        Context context = new MainActivity();
        ArrayList<Profile> users = new ArrayList<>();
        users.add(new Profile());
        users.add(new Profile());
        users.add(new Profile());
        adapter = new UserListviewAdapter(context, users);
        /**
        ViewGroup viewGroup = new ViewGroup(context) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {
            }
        };
        adapter.getView(0, null, viewGroup);
         */
        Holder mHolder = adapter.getHolder();
        assertNotNull(mHolder);
        assertNotNull(adapter.getNameTextView());
        assertNotNull(adapter.getUsernameTextview());
        assertNotNull(adapter.getFollowingImageview());
    }
}
