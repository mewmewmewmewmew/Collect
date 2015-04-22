package shopping.with.friends;

import android.app.Application;

import shopping.with.friends.Objects.Profile;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Main application class to store global variables
 * Also allows for use of custom fonts with Calligraphy
 * Created by Ryan Brooks on 2/17/15.
 */
public class MainApplication extends Application {

    private Profile profile;

    /**
     * Getter for profile
     *
     * @return user profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Setter for profile
     *
     * @param profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * OnCreate application method overridden for use of custom fonts
     * Uses Calligraphy library
     */
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/DroidSans-Bold.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/DroidSans.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/fontAwesome.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
