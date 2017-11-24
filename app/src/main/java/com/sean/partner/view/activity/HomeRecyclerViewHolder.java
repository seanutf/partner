package com.sean.partner.view.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sean.partner.R;

/**
 * Created by sean on 2017/11/24.
 *
 * 主界面feed流的ViewHolder
 */

public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView imgAvatar;
    TextView tvContent;
    TextView tvLoc, tvIntroduction;
    ImageButton btnReview;
    ImageButton btnTogether;
    ImageButton btnShare;

    public HomeRecyclerViewHolder(View itemView) {
        super(itemView);
        imgAvatar = (ImageView) itemView.findViewById(R.id.img_rv_home_item);
        tvContent = (TextView) itemView.findViewById(R.id.text_content_rv_home_item);
        tvLoc = (TextView) itemView.findViewById(R.id.text_loc_rv_home_item);
        tvIntroduction = (TextView) itemView.findViewById(R.id.text_introduction_rv_home_item);
        btnReview = (ImageButton) itemView.findViewById(R.id.btn_review_rv_home_item);
        btnTogether = (ImageButton) itemView.findViewById(R.id.btn_together_rv_home_item);
        btnShare = (ImageButton) itemView.findViewById(R.id.btn_share_rv_home_item);
    }
}
