package shopping.with.friends.Objects;

/**
 * Post object for storing information for a Post
 * Created by Ryan Brooks on 3/4/15.
 */
public class Post {

    private String title;
    private String description;
    private int price;
    private String userID;
    private double longitude;
    private double latitiude;

    /**
     * Public empty constructor
     */
    public Post() {
    }

    /**
     * Getter for post item's price
     *
     * @return item's price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter for user's price
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter for title
     *
     * @return Post's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the post's title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the post's description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(double latitiude) {
        this.latitiude = latitiude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
