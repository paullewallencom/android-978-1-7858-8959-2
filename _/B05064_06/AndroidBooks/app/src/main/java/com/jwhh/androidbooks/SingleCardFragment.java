package com.jwhh.androidbooks;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleCardFragment extends Fragment {
    public static SingleCardFragment newInstance() {
        SingleCardFragment fragment = new SingleCardFragment();
        return fragment;
    }

    public SingleCardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.book_card_view, container, false);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSelectedBookChangeListener listener = (OnSelectedBookChangeListener)getActivity();

                listener.onSelectedBookChanged(rootView, 0);
            }
        });
        return rootView;
    }


}
