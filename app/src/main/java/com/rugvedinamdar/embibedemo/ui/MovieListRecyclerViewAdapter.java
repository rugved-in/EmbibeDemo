package com.rugvedinamdar.embibedemo.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.utils.Utility;

import java.util.List;

public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder> {

    private List<Movie> mData;
    Context mContext;
    private MovieListItemClickListener recyclerViewClickListener;
    private String TAG = getClass().getSimpleName();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_movie_list_row, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClickListener.onItemClicked(view, viewHolder.getLayoutPosition(), mData.get(viewHolder.getLayoutPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie model = mData.get(position);
        if (model != null) {
            holder.mTitle.setText(model.getTitle());
            holder.mReleaseYear.setText(model.getReleaseYear());
            holder.mRating.setText(String.valueOf(model.getRating()));
            if (model.getPosterImageUrl() != null && !TextUtils.isEmpty(model.getPosterImageUrl())) {
                Utility.setImage(mContext, holder.mPoster, model.getPosterImageUrl());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(Movie movie, int position) {
        position = position == -1 ? getItemCount() : position;
        mData.add(position, movie);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void append(List<Movie> mData) {
        mData.addAll(mData);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mReleaseYear;
        public TextView mRating;
        public ImageView mPoster;
        public ViewHolder(View rowView) {
            super(rowView);
            mTitle = rowView.findViewById(R.id.tv_title);
            mReleaseYear = rowView.findViewById(R.id.tv_release_year);
            mRating = rowView.findViewById(R.id.tv_rating);
            mPoster = rowView.findViewById(R.id.img_poster);
        }
    }

    public interface MovieListItemClickListener {
        public void onItemClicked(View view, int position, Movie movie);
    }
}
