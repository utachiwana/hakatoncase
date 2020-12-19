package com.utachiwana.athena.ui.logic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utachiwana.athena.R;
import com.utachiwana.athena.data.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Post> posts = new ArrayList<>();
    PostClickListener mListener;

    public PostAdapter(PostClickListener mListener) {
        this.mListener = mListener;
    }

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
        holder.bind(posts.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public static class PostViewHolder extends RecyclerView.ViewHolder {

        TextView mType, mAuthor, mTime, mSubject, mPrice;
        Button rightBtn, leftBtn;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            mType = itemView.findViewById(R.id.item_type);
            mAuthor = itemView.findViewById(R.id.item_name);
            mPrice = itemView.findViewById(R.id.item_price);
            mSubject = itemView.findViewById(R.id.item_subject);
            mTime = itemView.findViewById(R.id.item_time);
            rightBtn = itemView.findViewById(R.id.item_right_button);
            leftBtn = itemView.findViewById(R.id.item_left_button);
            rightBtn.setText(R.string.moredetails);
            leftBtn.setText(R.string.signup);
        }

        public void bind(Post post, PostClickListener listener) {
            mTime.setText(post.getTime());
            mSubject.setText(post.getSubject());
            mPrice.setText(post.getPrice());
            mAuthor.setText(post.getAuthor());
            mType.setText(post.getType());
            rightBtn.setOnClickListener(v-> listener.moreDetailsClicked(post));
            leftBtn.setOnClickListener(v-> listener.signUpClicked(post));
        }
    }
}
