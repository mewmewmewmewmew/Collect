package shopping.with.friends.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import shopping.with.friends.Activities.ProfileActivity;
import shopping.with.friends.Adapters.UserListviewAdapter;
import shopping.with.friends.Api.ApiInterface;
import shopping.with.friends.MainApplication;
import shopping.with.friends.Objects.Profile;
import shopping.with.friends.R;

/**
 * Created by Ryan Brooks on 2/19/15.
 */
public class Search extends Fragment {

    private Button searchButton;
    private ListView userListView;
    private ArrayList<Profile> userList;
    private Profile userProfile;


    public Search() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        MainApplication mainApplication = (MainApplication) getActivity().getApplicationContext();
        userProfile = mainApplication.getProfile();

        userListView = (ListView) view.findViewById(R.id.sf_search_listview);
        searchButton = (Button) view.findViewById(R.id.sf_all_users_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.clear();
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://" + getString(R.string.server_address))
                        .build();

                ApiInterface apiInterface = restAdapter.create(ApiInterface.class);
                apiInterface.getAllUsers(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject jsonObject, retrofit.client.Response response) {

                        UserListviewAdapter ulvw = new UserListviewAdapter(getActivity().getApplicationContext(), readJSONResponse(jsonObject.toString(), userProfile.getId()));
                        userListView.setAdapter(ulvw);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("Failure", error.getMessage());
                    }
                });
            }
        });

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profile clickedProfile = userList.get(position);
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                i.putExtra("user_id", clickedProfile.getId());
                startActivity(i);
            }
        });


        return view;
    }

    /**
     * readJSONResponse reads API response and sets the profile
     * object attributes
     * @param  jsonObjectString API response
     * @param  profileId        profileId of current user
     * @return                  ArrayList<Profile> userList of all profiles minus current user
     */
    public ArrayList<Profile> readJSONResponse(String jsonObjectString, profileId) {
        ArrayList<Profile> userList = new ArrayList<>();
        try {
            // test this edge case
            if (jsonObjectString == null) {
                return userList;
            } else {
                Log.d("Success", jsonObjectString);
                JSONObject mainObject = new JSONObject(jsonObjectString);
                JSONArray userArray = mainObject.getJSONArray("users");
                for (int i = 0; i < userArray.length(); i++) {
                    JSONObject user = userArray.getJSONObject(i);

                    Profile profile = new Profile();
                    profile.setId(user.getString("_id"));
                    profile.setEmail(user.getString("email"));
                    profile.setPassword(user.getString("password"));
                    profile.setUsername(user.getString("username"));
                    profile.setName(user.getString("name"));

                    if (!profileId.equals(profile.getId())) {
                        userList.add(profile);
                    }
                }
            }
        } catch (JSONExcepton e) {
            e.printStackTrace();
        }

        return userList;
    }
}