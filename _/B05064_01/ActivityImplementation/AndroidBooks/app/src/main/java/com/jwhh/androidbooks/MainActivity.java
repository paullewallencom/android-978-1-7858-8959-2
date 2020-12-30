package com.jwhh.androidbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupClickListener(R.id.dynamicUiBook);
        setupClickListener(R.id.android4NewBook);
        setupClickListener(R.id.androidSysDevBook);
        setupClickListener(R.id.androidEngineBook);
        setupClickListener(R.id.androidDbProgBook);
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

    private void setupClickListener(int childViewId) {
        View childView = findViewById(childViewId);
        childView.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        int bookDescriptionResourceId = 0;

        switch (id) {
            case R.id.dynamicUiBook:
                bookDescriptionResourceId = R.string.dynamicUIDescription ;
                break;
            case R.id.android4NewBook:
                bookDescriptionResourceId = R.string.android4NewDescription ;
                break;
            case R.id.androidSysDevBook:
                bookDescriptionResourceId = R.string.androidSysDevDescription ;
                break;
            case R.id.androidEngineBook:
                bookDescriptionResourceId = R.string.androidEngineDescription ;
                break;
            case R.id.androidDbProgBook:
                bookDescriptionResourceId = R.string.androidDbProgDescription ;
                break;
        }

        if(bookDescriptionResourceId != 0) {
            TextView bookDescriptionTextView = (TextView) findViewById(R.id.bookDescription);
            bookDescriptionTextView.setText(bookDescriptionResourceId);
        }

    }

}
