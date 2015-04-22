package shopping.with.friends.Adapters;

/**
 * Created by Ryan Brooks on 2/19/15.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import shopping.with.friends.Api.ApiInterface;
import shopping.with.friends.MainApplication;
import shopping.with.friends.Objects.Profile;
import shopping.with.friends.R;

public class UserListviewAdapter extends BaseAdapter {

    private ArrayList<Profile> users;
    private Context context;
    private Profile userProfile;
    private Profile followingProfile;
    private ArrayList<String> followersIdsList, followingIdsList;
    private Holder mHolder;

    public UserListviewAdapter(Context context, ArrayList<Profile> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Profile getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public TextView getNameTextView() {
        return mHolder.name;
    }

    public TextView getUsernameTextview() {
        return mHolder.username;
    }

    public ImageView getFollowingImageview() {
        return mHolder.followButton;
    }

    public View getView(int pos, View child, ViewGroup parent) {
        MainApplication mainApplication = (MainApplication) context.getApplicationContext();
        userProfile = mainApplication.getProfile();
        LayoutInflater layoutInflater;
        final Profile profile = users.get(pos);
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listview_item_users, null);
            mHolder = new Holder();
            mHolder.name = (TextView) child.findViewById(R.id.lvu_name_textview);
            mHolder.username = (TextView) child.findViewById(R.id.lvu_username_textview);
            mHolder.followButton = (ImageView) child.findViewById(R.id.lvu_follow_button);
            child.setTag(mHolder);
        } else {
            mHolder = (Holder) child.getTag();
        }

        mHolder.name.setText(profile.getName());
        mHolder.username.setText(profile.getUsername());
        if (!userProfile.getFollowing().contains(profile.getId())) {
            /**
            * Follow
            */
            mHolder.followButton.setImageDrawable(context.getResources().getDrawable(R.drawable.follow_check));
            mHolder.followButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHolder.followButton.setEnabled(false);
                    followingProfile = profile;

                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setEndpoint("http://" + context.getString(R.string.server_address))
                            .build();

                    ApiInterface apiInterface = restAdapter.create(ApiInterface.class);
                    apiInterface.followUser(userProfile.getId(), followingProfile.getId(), new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            Log.d("JSON follow", jsonObject.toString());
                            try {
                                JSONObject mainObject = new JSONObject(jsonObject.toString());
                                if (mainObject.getBoolean("status")) {
                                    followersIdsList = new ArrayList<>();
                                    followingIdsList = new ArrayList<>();
                                    JSONObject userObject = mainObject.getJSONObject("user");
                                    JSONArray followersArray = userObject.getJSONArray("followers");
                                    JSONArray followingArray = userObject.getJSONArray("following");
                                    for (int i = 0; i < followersArray.length(); i++) {
                                        String userId = followersArray.getString(i);

                                        followersIdsList.add(userId);
                                    }
                                    for (int i = 0; i < followingArray.length(); i++) {
                                        String userId = followingArray.getString(i);

                                        followingIdsList.add(userId);
                                    }
                                    userProfile.setFollowers(followersIdsList);
                                    userProfile.setFollowing(followingIdsList);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("Error", error.toString());
                            mHolder.followButton.setEnabled(true);
                        }
                    });

                    apiInterface.addFollower(followingProfile.getId(), userProfile.getId(), new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            Log.d("JSON add follower", jsonObject.toString());
                            mHolder.followButton.setImageDrawable(context.getResources().getDrawable(R.drawable.following_check));
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("Error", error.toString());
                            mHolder.followButton.setEnabled(true);
                        }
                    });
                }
            });
        } else {
            /**
             * Unfollow
             */
            mHolder.followButton.setImageDrawable(context.getResources().getDrawable(R.drawable.following_check));
            mHolder.followButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHolder.followButton.setEnabled(false);
                    followingProfile = profile;

                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setEndpoint("http://" + context.getString(R.string.server_address))
                            .build();

                    ApiInterface apiInterface = restAdapter.create(ApiInterface.class);
                    apiInterface.unFollowUser(userProfile.getId(), followingProfile.getId(), new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            Log.d("JSON un-follow", jsonObject.toString());
                            try {
                                JSONObject mainObject = new JSONObject(jsonObject.toString());
                                if (mainObject.getBoolean("status")) {
                                    followersIdsList = new ArrayList<>();
                                    followingIdsList = new ArrayList<>();
                                    JSONObject userObject = mainObject.getJSONObject("user");
                                    JSONArray followersArray = userObject.getJSONArray("followers");
                                    JSONArray followingArray = userObject.getJSONArray("following");
                                    for (int i = 0; i < followersArray.length(); i++) {
                                        String userId = followersArray.getString(i);

                                        followersIdsList.add(userId);
                                    }
                                    for (int i = 0; i < followingArray.length(); i++) {
                                        String userId = followingArray.getString(i);

                                        followingIdsList.add(userId);
                                    }
                                    userProfile.setFollowers(followersIdsList);
                                    userProfile.setFollowing(followingIdsList);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("Error", error.toString());
                            mHolder.followButton.setEnabled(true);
                        }
                    });

                    apiInterface.removeFollower(followingProfile.getId(), userProfile.getId(), new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            Log.d("JSON remove follower", jsonObject.toString());
                            mHolder.followButton.setImageDrawable(context.getResources().getDrawable(R.drawable.follow_check));
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("Error", error.toString());
                            mHolder.followButton.setEnabled(true);
                        }
                    });
                }
            });
        }

        return child;
    }

    public Holder getHolder() {
        return mHolder;
    }

    public class Holder {
        TextView name;
        TextView username;
        ImageView followButton;
    }
}
