package com.example.rubit1359.sindaynews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rubit1359.sindaynews.R;
import com.example.rubit1359.sindaynews.model.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rubit1359 on 10/24/2016.
 */

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int IMAGE = 0;
    private final int NO_IMAGE = 1;

    private List<Article> mArticles;
        // Automatically add the Constructor here


    public ArticleAdapter() {
        this.mArticles = new ArrayList<>();
    }

    public void setArticles(List<Article> articles) {
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    public void addArticles(List<Article> articles) {
        int startPosition = mArticles.size();
        mArticles.addAll(articles);
        notifyItemRangeInserted(startPosition, articles.size());
    }

    // Implements compulsory methods

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case NO_IMAGE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_items_no_image,
                        parent, false);
                return new NoImageViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_items,
                        parent, false);
                return new ImageViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Article article = mArticles.get(position);
        if (article.getMultimedia() != null && article.getMultimedia().isEmpty()) {
            return IMAGE;
        }
        return NO_IMAGE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Article article = mArticles.get(position);
            if (holder instanceof NoImageViewHolder) {
                bindNoImage(article, (NoImageViewHolder) holder);
        } else {
                bindImage(article, (ImageViewHolder) holder);
            }
    }

    private void bindImage(Article article, ImageViewHolder holder) {
        holder.tvSnippet.setText(article.getSnippet());
        Glide.with(holder.itemView.getContext()).load(article.getMultimedia().get(0).getUrl())
                .into(holder.ivImage);
    }

    private void bindNoImage(Article article, NoImageViewHolder holder) {
        holder.tvSnippet.setText(article.getSnippet());
    }


    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class NoImageViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        @BindView(R.id.tvSnippet)
        TextView tvSnippet;


        NoImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onClick(View view) {

        }

    }
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvSnippet)
        TextView tvSnippet;


        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {

        }

    }


}
