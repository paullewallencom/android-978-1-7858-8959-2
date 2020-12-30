package com.jwhh.androidbooks;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements OnSelectedBookChangeListener {

    boolean mCreating = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        // Access the FragmentManager
        FragmentManager fragmentManager = getFragmentManager();
        // Get the book description fragment
        BookDescFragment bookDescFragment = (BookDescFragment)
                fragmentManager.findFragmentById
                        (R.id.fragmentDescription);

        // Check validity of fragment reference
        if(bookDescFragment == null || !bookDescFragment.isVisible()){
            // Use activity to display description
            if(!mCreating) {
                Intent intent = new Intent(this, BookDescActivity.class);
                intent.putExtra("bookIndex", bookIndex);
                startActivity(intent);
            }
        }
        else {
            // Use contained fragment to display description
            bookDescFragment.setBook(bookIndex);
        }
    }
}
