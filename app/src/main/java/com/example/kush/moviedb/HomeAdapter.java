package com.example.kush.moviedb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kush.moviedb.utilities.MoviePosterClass;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by saini on 26-Jul-16.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.OurHolder> {

    public class OurHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        public OurHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.movie_image);
        }
    }
    Context mContext;
    ArrayList<MoviePosterClass> data;

    public HomeAdapter(ArrayList<MoviePosterClass> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public OurHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.movie_card,parent,false);
        return new OurHolder(v);
    }

    @Override
    public void onBindViewHolder(OurHolder holder, int position) {
        final MoviePosterClass h = data.get(position);
        Picasso.with(mContext).load(h.getImagePicPath()).into(holder.poster);
        holder.poster.setOnClickListener(new RecyclerView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext,DetailsActivity.class);
                intent.putExtra("movieId",h.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}