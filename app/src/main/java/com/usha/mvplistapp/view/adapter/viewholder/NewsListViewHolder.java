package com.usha.mvplistapp.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.usha.mvplistapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.news_image)
    public ImageView newsImage;
    @BindView(R.id.news_head_line)
    public TextView newsHeadLine;
    @BindView(R.id.news_content)
    public TextView newsContent;

    public NewsListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }
}