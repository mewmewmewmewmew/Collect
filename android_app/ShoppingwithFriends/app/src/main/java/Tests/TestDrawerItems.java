package Tests;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.List;

import shopping.with.friends.MainActivity;
import shopping.with.friends.Objects.DrawerMenuItem;

/**
 * Created by Ryan Brooks on 3/29/15.
 */
public class TestDrawerItems extends ActivityInstrumentationTestCase2 {

    private List<DrawerMenuItem> expectedMenuItems;

    public TestDrawerItems() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context c = new MainActivity();
        expectedMenuItems = new ArrayList<>();
        DrawerMenuItem item1 = new DrawerMenuItem();
        DrawerMenuItem item2 = new DrawerMenuItem();
        DrawerMenuItem item3 = new DrawerMenuItem();
        DrawerMenuItem item4 = new DrawerMenuItem();
        DrawerMenuItem item5 = new DrawerMenuItem();
        item1.setItemText("Main Feed");
        item2.setItemText("My Collections");
        item3.setItemText("Search");
        item4.setItemText("Map");
        item5.setItemText("Settings");

        expectedMenuItems.add(item1);
        expectedMenuItems.add(item2);
        expectedMenuItems.add(item3);
        expectedMenuItems.add(item4);
        expectedMenuItems.add(item5);

        //expectedMenuItems == generateDrawerMenuItems
        assertArrayEquals(expectedMenuItems, c.generateDrawerMenuItems());
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();

    }

}
