package com.example.rubit1359.sindaynews.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rubit1359.sindaynews.BuildConfig;
import com.example.rubit1359.sindaynews.R;
import com.example.rubit1359.sindaynews.adapters.ArticleAdapter;
import com.example.rubit1359.sindaynews.api.ArticleApi;
import com.example.rubit1359.sindaynews.model.SearchRequest;
import com.example.rubit1359.sindaynews.model.SearchResult;
import com.example.rubit1359.sindaynews.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.handle;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {
    private SearchRequest mSearchRequest;
    private ArticleApi mArticleApi;
    private ArticleAdapter mArticleAdapter;
    private StaggeredGridLayoutManager mLayoutManager;

    @BindView(R.id.rvArticle)
    RecyclerView rvArticle;
    @BindView(R.id.pbLoadMore)
    ProgressBar pbLoadMore;
    @BindView(R.id.pbLoading)
    View pbLoading;

    private interface Listener {
        void onResult(SearchResult searchResult);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpApi();
        setUpViews();
        search();
    }

    private void setUpViews() {
        mArticleAdapter = new ArticleAdapter();
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvArticle.setLayoutManager(mLayoutManager);
        rvArticle.setAdapter(mArticleAdapter);
    }

    private void search() {
        pbLoading.setVisibility(View.VISIBLE);
        fetchArticles(searchResult -> mArticleAdapter.setArticles(searchResult.getArticles()));

    }

    private void searchMore() {
        pbLoadMore.setVisibility(View.VISIBLE);
        fetchArticles(searchResult -> mArticleAdapter.addArticles(searchResult.getArticles()));

    }

    private void setUpApi() {
        mSearchRequest = new SearchRequest();
        mArticleApi = RetrofitUtils.get().create(ArticleApi.class);
    }

    private void fetchArticles(Listener listerner) {
        mArticleApi.search(mSearchRequest.toQueryMap()).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                listerner.onResult(response.body());
                handleComplete();
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });
    }

    private void handleComplete() {
        pbLoading.setVisibility(View.GONE);
        pbLoadMore.setVisibility(View.GONE);
    }
}
