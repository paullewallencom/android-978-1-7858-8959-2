package com.jwhh.androidbooks;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity
    implements OnSelectedBookChangeListener
{
    String[] mTitles;
    String[] mDescriptions;

    int[] mImageLargeResourceIds = {
            R.drawable.db_programming_large,
            R.drawable.dynamic_ui_large,
            R.drawable.sys_dev_large,
            R.drawable.and_engine_large
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitles = getResources().getStringArray(R.array.book_titles);
        mDescriptions = getResources().getStringArray(R.array.book_descriptions);

        // Create the Fragment and add
        Slide slideLeftTransition = new Slide(Gravity.LEFT);
        slideLeftTransition.setDuration(500);

        BookListFragment listFragment = BookListFragment.newInstance();
//        SingleCardFragment listFragment = SingleCardFragment.newInstance();
        listFragment.setExitTransition(slideLeftTransition);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .add(R.id.layoutRoot, listFragment)
                .commit();
    }

    @Override
    public void onSelectedBookChanged(View view, int bookIndex) {

        TextView titleTextView = (TextView)view.findViewById(R.id.bookTitle);
        ImageView bookImageView = (ImageView)view.findViewById(R.id.topImage);

        Slide slideBottomTransition = new Slide(Gravity.BOTTOM);
        slideBottomTransition.setDuration(500);

        ChangeBounds changeBoundsTransition = new ChangeBounds();

        ChangeTransform changeTransformTransition = new ChangeTransform();

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(changeBoundsTransition);
        transitionSet.addTransition(changeTransformTransition);
        transitionSet.setDuration(500);

        BookDescFragment bookDescFragment =
                BookDescFragment.newInstance(mTitles[bookIndex], mDescriptions[bookIndex],
                        mImageLargeResourceIds[bookIndex], bookIndex);
        bookDescFragment.setEnterTransition(slideBottomTransition);
        bookDescFragment.setAllowEnterTransitionOverlap(false);
        bookDescFragment.setSharedElementEnterTransition(transitionSet);


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layoutRoot, bookDescFragment)
                .addSharedElement(bookImageView, "book_image_" + bookIndex)
                .addSharedElement(titleTextView, "title_text_" + bookIndex)
                .addToBackStack(null)
                .commit();

    }

}
