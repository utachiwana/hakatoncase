package com.utachiwana.athena.ui.logic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utachiwana.athena.R;

import java.util.ArrayList;
import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    List<String> list = new ArrayList<>();

    public TimeAdapter(List<String> times) {
        if (times != null)
            list.addAll(times);
    }

    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_recycler, parent, false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {

        TextView time;

        public TimeViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.item_time);
        }

        public void bind() {
            time.setText(list.get(getAdapterPosition()));
        }
    }
}
