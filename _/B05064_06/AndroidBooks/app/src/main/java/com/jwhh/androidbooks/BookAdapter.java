package com.jwhh.androidbooks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jim on 12/29/2015.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private String[] mTitles;
    private int[] mImageResourceIds;

    public BookAdapter(String[] titles, int[] imageResourceIds) {
        mTitles = titles;
        mImageResourceIds = imageResourceIds;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_card_view, parent, false);

        ViewHolder vh = new ViewHolder(rootView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mTitles[position]);
        holder.mTextView.setTransitionName("title_text_" + position);
        holder.mImageView.setImageResource(mImageResourceIds[position]);
        holder.mImageView.setTransitionName("book_image_" + position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.bookTitle);
            mImageView = (ImageView)v.findViewById(R.id.topImage);
        }
    }


}