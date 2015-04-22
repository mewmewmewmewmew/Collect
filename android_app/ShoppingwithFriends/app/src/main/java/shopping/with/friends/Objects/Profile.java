package shopping.with.friends.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Profile Object that stores all user data
 * To be used for current user or any other user
 * Created by Ryan Brooks on 2/19/15.
 */
public class Profile implements Serializable {

    private String id;
    private String name;
    private String email;
    private String password;
    private String username;
    private int thresholdPrice;
    private ArrayList<String> followersIds;
    private ArrayList<String> followingIds;

    /**
     * Public empty constructor
     */
    public Profile() {
    }

    /**
     * Constructor to be used when data is readily available
     *
     * @param id
     * @param name
     * @param email
     * @param password
     * @param username
     */
    public Profile(String id, String name, String email, String password, String username) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    /**
     * Full constructor with all necessary parameters
     *
     * @param id
     * @param name
     * @param email
     * @param password
     * @param username
     * @param followersIds
     * @param followingIds
     */
    public Profile(String id, String name, String email, String password, String username,
                   ArrayList<String> followersIds, ArrayList<String> followingIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.followersIds = followersIds;
        this.followingIds = followingIds;
    }

    /**
     * Getter for followers list
     *
     * @return list of all user's followers
     */
    public ArrayList<String> getFollowers() {
        return followersIds;
    }

    /**
     * Setter for followers list
     *
     * @param followers
     */
    public void setFollowers(ArrayList<String> followers) {
        this.followersIds = followers;
    }

    /**
     * Getter for following list
     *
     * @return list of all users user is following
     */
    public ArrayList<String> getFollowing() {
        return followingIds;
    }

    /**
     * Setter for following list
     *
     * @param following
     */
    public void setFollowing(ArrayList<String> following) {
        this.followingIds = following;
    }

    /**
     * Getter for username
     *
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for user's password (Encrypted)
     *
     * @return user's encrypted password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password (Encrypted)
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for user's email address
     *
     * @return user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for user's email address
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for user's real name
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for user's name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for user's unique id in the database
     *
     * @return user's unique id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for user's unique id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for user's defined Threshold price
     *
     * @return user's threshold price
     */
    public int getThresholdPrice() {
        return thresholdPrice;
    }

    /**
     * Setter for user's threshold price
     * 
     * @param thresholdPrice
     */
    public void setThresholdPrice(int thresholdPrice) {
        this.thresholdPrice = thresholdPrice;
    }
}
