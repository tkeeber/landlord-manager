package com.tek.landlord;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        this.mTitle = getTitle();

        // Set up the drawer.
        this.mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        switch (position) {
        case 0:
            fragment = new HomeFragment();
            break;
        case 1:
            fragment = new NewsfeedFragment();
            break;
        case 2:
            fragment = new PropertyFragment();
            break;
        case 3:
            fragment = new RentFragment();
            break;
        case 4:
            fragment = new TenantFragment();
            break;
        case 5:
            fragment = new FinancialReportFragment();
            break;
        case 6:
            fragment = new DamageReportFragment();
            break;
        default:
            fragment = new HomeFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
        case 1:
            this.mTitle = getString(R.string.title_newsfeed);
            break;
        case 2:
            this.mTitle = getString(R.string.title_properties);
            break;
        case 3:
            this.mTitle = getString(R.string.title_rents);
            break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(this.mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!this.mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
