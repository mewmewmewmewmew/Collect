package Tests;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;
import java.util.List;

import shopping.with.friends.Objects.DrawerMenuItem;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {


    public ApplicationTest() {
        super(Application.class);
        setup();
        testGetDrawerItems();
    }

    public void setup() {

    }

    public void testGetDrawerItems() {

    }
}