package com.jwhh.bookbrowser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    String[] mTitles;
    String[] mTitlesShort;
    String[] mDescriptions;
    int[] mTopImageResourceIds = {
            R.drawable.db_programming_top_card,
            R.drawable.android_4_top_card,
            R.drawable.sys_dev_top_card,
            R.drawable.and_engine_top_card
    };
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    PagerTitleStrip mTitleStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitles = getResources().getStringArray(R.array.book_titles);
        mTitlesShort = getResources().getStringArray(R.array.book_titles_short);
        mDescriptions = getResources().getStringArray(R.array.book_descriptions);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a BookFragment (defined as a static inner class below).
            return BookFragment.newInstance(
                    mTitles[position], mDescriptions[position], mTopImageResourceIds[position]);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitlesShort[position];
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BookFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

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
