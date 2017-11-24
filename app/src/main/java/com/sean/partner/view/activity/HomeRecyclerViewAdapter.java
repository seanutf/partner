package com.sean.partner.view.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.partner.R;

/**
 * Created by zhangyi on 2017/11/24.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {

    private int numberItems;

    public HomeRecyclerViewAdapter(int numberOfItems) {
        this.numberItems = numberOfItems;
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

    }

    @Override
    public int getItemCount() {
        return numberItems;
    }
}
