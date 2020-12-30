package com.jwhh.bookdrawer;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    String[] mTitles;
    String[] mTitlesShort;
    String[] mDescriptions;
    int[] mTopImageResourceIds = {
            R.drawable.db_programming_top_card,
            R.drawable.android_4_top_card,
            R.drawable.sys_dev_top_card,
            R.drawable.and_engine_top_card
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitles = getResources().getStringArray(R.array.book_titles);
        mTitlesShort = getResources().getStringArray(R.array.book_titles_short);
        mDescriptions = getResources().getStringArray(R.array.book_descriptions);
        mTitle = mTitles[0];

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        mTitle = mTitles[position];

        // update the main content by replacing fragments
        BookFragment newFragment =
                BookFragment.newInstance(mTitles[position], mDescriptions[position], mTopImageResourceIds[position]);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        mTitle = mTitles[number];
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.title_section1);
//                break;
//            case 2:
//                mTitle = getString(R.string.title_section2);
//                break;
//            case 3:
//                mTitle = getString(R.string.title_section3);
//                break;
//        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            //restoreActionBar();
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BookFragment extends Fragment {
        private static final String ARG_TITLE = "title";
        private static final String ARG_DESCRIPTION = "description";
        private static final String ARG_IMAGE_ID = "image id";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static BookFragment newInstance(String title, String description, int imageResourceId) {
            BookFragment fragment = new BookFragment();
            Bundle args = new Bundle();
            args.putString(ARG_TITLE, title);
            args.putString(ARG_DESCRIPTION, description);
            args.putInt(ARG_IMAGE_ID, imageResourceId);
            fragment.setArguments(args);
            return fragment;
        }

        public BookFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_book_info, container, false);

            TextView bookTitleView = (TextView)rootView.findViewById(R.id.bookTitle);
            TextView bookDescriptionView = (TextView)rootView.findViewById(R.id.bookDescription);
            ImageView topImageView = (ImageView)rootView.findViewById(R.id.topImage);

            Bundle args = getArguments();
            bookTitleView.setText(args.getString(ARG_TITLE));
            bookDescriptionView.setText(args.getString(ARG_DESCRIPTION));
            topImageView.setImageResource(args.getInt(ARG_IMAGE_ID));

            return rootView;
        }
    }

}
