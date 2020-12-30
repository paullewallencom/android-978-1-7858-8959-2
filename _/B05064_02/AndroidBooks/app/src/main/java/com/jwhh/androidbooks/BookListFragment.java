package com.jwhh.androidbooks;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment  implements RadioGroup.OnCheckedChangeListener{


    public BookListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewHierarchy =
                inflater.inflate(R.layout.fragment_book_list,
                        container, false);
        // Connect the listener to the radio group
        RadioGroup group = (RadioGroup)
                viewHierarchy.findViewById(R.id.bookSelectGroup);
        group.setOnCheckedChangeListener(this);

        return viewHierarchy;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // Translate radio button to book index
        int bookIndex = translateIdToIndex(checkedId);

        // Get parent Activity and send notification
        OnSelectedBookChangeListener listener =
                (OnSelectedBookChangeListener) getActivity();
        listener.onSelectedBookChanged(bookIndex);
    }

    int translateIdToIndex(int id) {
        int index = -1;
        switch (id) {
            case R.id.dynamicUiBook:
                index = 0 ;
                break;
            case R.id.android4NewBook:
                index = 1 ;
                break;
            case R.id.androidSysDevBook:
                index = 2 ;
                break;
            case R.id.androidEngineBook:
                index = 3 ;
                break;
            case R.id.androidDbProgBook:
                index = 4 ;
                break;
        }

        return index;
    }
}
