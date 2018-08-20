package com.usha.mvplistapp.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.usha.mvplistapp.R;
import com.usha.mvplistapp.model.NewsResponse;
import com.usha.mvplistapp.view.adapter.viewholder.NewsListViewHolder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListViewHolder> {

    List<NewsResponse> newsList;

    Context context;
    public NewsListAdapter(List<NewsResponse> newsList, Context context) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news_card, parent, false);

        return new NewsListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        holder.newsHeadLine.setText(trim(fromHtml(newsList.get(position).getHeadline())));
        holder.newsContent.setText(trim(fromHtml(newsList.get(position).getContent())));
        Glide.with(context)
                .load(newsList.get(position).getImgurl())
                .into( holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void changeClick(int position) {
        notifyDataSetChanged();
    }

    public static Spanned fromHtml(String html){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                return Html.fromHtml(URLDecoder.decode(html, "UTF-8"), Html.FROM_HTML_MODE_LEGACY);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                return Html.fromHtml(URLDecoder.decode(html, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private CharSequence trim(CharSequence text) {
        if(text!=null && text.length()>0) {

            while (text.charAt(text.length() - 1) == '\n') {
                text = text.subSequence(0, text.length() - 1);
            }
            return text;
        } else {
            return "";
        }
    }
}
