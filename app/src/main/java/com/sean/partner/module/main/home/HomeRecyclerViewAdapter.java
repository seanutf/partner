package com.sean.partner.module.main.home;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sean.partner.R;
import com.sean.partner.utils.ImageResourcesURL;

/**
 * Created by sean on 2017/11/24.
 *
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {

    private int numberItems;
    private Activity activity;

    public HomeRecyclerViewAdapter(int numberOfItems, Activity activity) {
        this.numberItems = numberOfItems;
        this.activity = activity;
    }

    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_home_normal, parent, false);
        HomeRecyclerViewHolder viewHolder = new HomeRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeRecyclerViewHolder holder, int position) {
            if(holder != null){
                        Glide.with(activity).load(ImageResourcesURL.getImgURLs()[position])
                        .into(holder.imgAvatar);
                        holder.tvIntroduction.setText(activity.getString(R.string.text_test_1_activity_home));
                        holder.tvContent.setText(activity.getString(R.string.text_test_2_activity_home));
                        holder.tvLoc.setText(activity.getString(R.string.text_test_3_activity_home));

            }
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }
}
