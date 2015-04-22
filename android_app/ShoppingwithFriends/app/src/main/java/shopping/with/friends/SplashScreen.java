package shopping.with.friends;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

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
import shopping.with.friends.Login.LoginSelectorActivity;
import shopping.with.friends.Objects.Profile;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ryan Brooks on 2/24/15.
 * Splash screen for start of app to allow for persistence
 * Network calls made while animation is running
 */
public class SplashScreen extends ActionBarActivity {

    //Variables
    private SharedPreferences preferences;
    private String emailPref, passwordPref;
    private ArrayList<String> followersIdsList, followingIdsList;
    private boolean loginSuccessful;
    private float oldAngle = 0;
    private float newAngle = 55;
    private float pivotX = .5f;
    private float pivotY = .295f;
    private ImageView rotator;
    private RotateAnimation tagAnimation;

    /**
     * Allows for custom fonts using Calligraphy
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Usual onCreate.
     * In this case, an animation is started and runs for 12 seconds max. A network request
     * is made with the saved username and password and if no response in 2 seconds, the user
     * is sent to Login/Register selection screen
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        rotator = (ImageView) findViewById(R.id.ss_logo);
        tagAnimation = new RotateAnimation(oldAngle, newAngle, Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
        tagAnimation.setDuration(12000);
        tagAnimation.setRepeatMode(Animation.RESTART);
        tagAnimation.setRepeatCount(Animation.INFINITE);
        CycleInterpolator change = new CycleInterpolator(10f);
        tagAnimation.setInterpolator(change);
        rotator.startAnimation(tagAnimation);

        followersIdsList = new ArrayList<>();
        followingIdsList = new ArrayList<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emailPref = preferences.getString(getString(R.string.email_pref), null);
        passwordPref = preferences.getString(getString(R.string.password_pref), null);

        if (emailPref != null && passwordPref != null ) {
            //Retrofit
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://" + getString(R.string.server_address))
                    .build();

            ApiInterface apiInterface = restAdapter.create(ApiInterface.class);
            apiInterface.loginUser(emailPref, passwordPref, new Callback<JsonObject>() {
                @Override
                public void success(JsonObject s, Response response) {
                    Log.d("Response JSON", s.toString());
                    try {
                        JSONObject mainObject = new JSONObject(s.toString());
                        loginSuccessful = mainObject.getBoolean("status");

                        if(loginSuccessful) {
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
                            Profile profile = new Profile();
                            profile.setId(userObject.getString("_id"));
                            profile.setEmail(userObject.getString("email"));
                            profile.setPassword(userObject.getString("password"));
                            profile.setUsername(userObject.getString("username"));
                            profile.setName(userObject.getString("name"));
                            profile.setThresholdPrice(Integer.parseInt(userObject.getString("threshold")));
                            profile.setFollowers(followersIdsList);
                            profile.setFollowing(followingIdsList);

                            MainApplication mainApplication = (MainApplication) getApplicationContext();
                            mainApplication.setProfile(profile);

                            Intent i = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            rotator.getAnimation().cancel();

                        } else {
                            Toast.makeText(getBaseContext(), "Error logging in. Please login again.", Toast.LENGTH_SHORT).show();
                            new CountDownTimer(500, 500) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {
                                    Intent i = new Intent(SplashScreen.this, LoginSelectorActivity.class);
                                    startActivity(i);
                                    finish();
                                    rotator.clearAnimation();
                                }
                            }.start();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(SplashScreen.this, "Couldn't connect, check IP", Toast.LENGTH_SHORT).show(); // TODO: Debug only
                    Intent i = new Intent(SplashScreen.this, LoginSelectorActivity.class);
                    startActivity(i);
                    finish();
                    rotator.clearAnimation();
                }
            });
        } else {
            Log.d("No ", "saved login data.");
            new CountDownTimer(2000, 2000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(SplashScreen.this, LoginSelectorActivity.class);
                    startActivity(i);
                    finish();
                    rotator.clearAnimation();
                }
            }.start();
        }
    }
}
