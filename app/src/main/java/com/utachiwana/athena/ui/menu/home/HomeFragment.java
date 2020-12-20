package com.utachiwana.athena.ui.menu.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.utachiwana.athena.R;
import com.utachiwana.athena.data.Post;
import com.utachiwana.athena.data.Prefs;
import com.utachiwana.athena.network.NetworkUtils;
import com.utachiwana.athena.ui.logic.PostAdapter;
import com.utachiwana.athena.ui.logic.PostClickListener;
import com.utachiwana.athena.ui.menu.MenuPresenter;
import com.utachiwana.athena.ui.menu.MenuView;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements MenuView, PostClickListener {

    RecyclerView mRecycler;
    PostAdapter mAdapter;
    MenuPresenter mPresenter;
    TextView mErrorView, currentPageText;
    SwipeRefreshLayout mRefreshLayout;
    ImageView rightArr, leftArr;
    int currentPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecycler = view.findViewById(R.id.post_recycler);
        mRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mErrorView = view.findViewById(R.id.error_view);
        rightArr = view.findViewById(R.id.next_page);
        leftArr = view.findViewById(R.id.prev_page);
        currentPageText = view.findViewById(R.id.current_page_text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        mAdapter = new PostAdapter(this);
        mRecycler.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(() -> mRefreshLayout.post(this::loadPosts));
        mPresenter = new MenuPresenter(this);
        currentPage = 1;
        currentPageText.setText(getResources().getString(R.string.page) + " " + currentPage);

        leftArr.setVisibility(View.INVISIBLE);
        leftArr.setEnabled(false);
        rightArr.setOnClickListener(v -> {
            currentPage++;
            mRefreshLayout.post(this::loadPosts);
            if (!leftArr.isEnabled()) {
                leftArr.setVisibility(View.VISIBLE);
                leftArr.setEnabled(true);
            }
        });
        leftArr.setOnClickListener(v -> {
            currentPage--;
            mRefreshLayout.post(this::loadPosts);
            if (currentPage == 1) {
                leftArr.setVisibility(View.INVISIBLE);
                leftArr.setEnabled(false);
            }
        });
        loadPosts();
    }

    private void loadPosts() {
        currentPageText.setText(getResources().getString(R.string.page) + " " + currentPage);
        mPresenter.loadPosts(false, false, true);
    }

    @Override
    public void showPosts(List<Post> data) {
        int maxSize = getResources().getStringArray(R.array.subjects).length;
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setSubject(getResources().getStringArray(R.array.subjects)[new Random().nextInt(maxSize)]);
        }
        mAdapter.addData(data, true);
        hideLoading();
        mRecycler.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
        mErrorView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        hideLoading();
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void signUpClicked(Post post) {

        SignUpDialog dialog = new SignUpDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArray("time", post.getTime().split("\n"));
        bundle.putString("duration", post.getDuration());
        dialog.setArguments(bundle);
        dialog.show(requireActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void moreDetailsClicked(Post post) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", post);
        Intent intent = new Intent(getContext(), PostDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
