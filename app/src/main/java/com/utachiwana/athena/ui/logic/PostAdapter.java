package com.utachiwana.athena.ui.logic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utachiwana.athena.R;
import com.utachiwana.athena.data.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    List<Post> posts = new ArrayList<>();

    public void addData(List<Post> data, boolean clean) {
        if (clean) posts.clear();
        posts.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_recycler, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
