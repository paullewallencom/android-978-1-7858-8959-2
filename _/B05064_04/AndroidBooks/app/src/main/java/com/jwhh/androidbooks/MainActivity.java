package com.jwhh.androidbooks;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements OnSelectedBookChangeListener {

    boolean mCreating = true;
    boolean mIsDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the book description fragment
        FragmentManager fm = getFragmentManager();
        Fragment bookDescFragment =
                fm.findFragmentById(R.id.fragmentDescription);

        // If not found than we're doing dynamic mgmt
        mIsDynamic = bookDescFragment == null ||
                !bookDescFragment.isInLayout();

        // Load the list fragment if necessary
        if (mIsDynamic) {
            // Begin transaction
            FragmentTransaction ft = fm.beginTransaction();

            // Create the Fragment and add
            BookListFragment2 listFragment = new BookListFragment2();
            ft.add(R.id.layoutRoot, listFragment);

            // Commit the changes
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCreating = false;
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

    @Override
    public void onSelectedBookChanged(int bookIndex) {
        BookDescFragment bookDescFragment;
        FragmentManager fm = getFragmentManager();

        // Check validity of fragment reference
        if(mIsDynamic){
            // Handle dynamic switch to description fragment
            FragmentTransaction ft = fm.beginTransaction();

            // Create the fragment and pass the book index
            bookDescFragment = BookDescFragment.newInstance(bookIndex);

            // Replace the book list with the description
            ft.replace(R.id.layoutRoot,
                    bookDescFragment);
            ft.addToBackStack(null);
            ft.setCustomAnimations(
                    android.R.animator.fade_in, android.R.animator.fade_out);
            ft.commit();
        }
        else {
            // Use the already visible description fragment
            bookDescFragment = (BookDescFragment)
                    fm.findFragmentById(R.id.fragmentDescription);
            bookDescFragment.setBook(bookIndex);
        }
    }
}
