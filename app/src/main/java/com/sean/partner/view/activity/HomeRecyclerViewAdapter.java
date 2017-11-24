package com.sean.partner.view.activity;

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
                        holder.tvIntroduction.setText("个人简介：热情开朗大方，年芳二八，肤白貌美，能担水，会砍柴，力拔山兮气盖世");
                        holder.tvContent.setText("【安河桥北宽窄巷】12：32，一起看电影《给我来俩带血大腰子2》，我会戴个鸭舌帽和六边形墨镜，右手带一白手套");
                        holder.tvLoc.setText("离你有5.5公里");

            }
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }
}
