package com.utachiwana.athena.ui.logic;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.utachiwana.athena.R;

import java.util.ArrayList;
import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    private final TimeSelectedListener listener;
    List<String> list = new ArrayList<>();
    int selectedItem = -1;

    public TimeAdapter(List<String> times, TimeSelectedListener listener) {
        if (times != null)
            list.addAll(times);
        this.listener = listener;
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
            itemView.setSelected(selectedItem == getAdapterPosition());
            if (itemView.isSelected()) {
                itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorPrimary));
            } else {
                itemView.setBackgroundColor(itemView.getResources().getColor(android.R.color.white));
            }
            time.setText(list.get(getAdapterPosition()));
            itemView.setOnClickListener(v -> {
                notifyItemChanged(selectedItem);
                if (selectedItem == getAdapterPosition()) {
                    selectedItem = -1;
                    listener.onTimeUnselected();
                } else {
                    selectedItem = getAdapterPosition();
                    listener.onTimeSelected();
                }
                notifyItemChanged(selectedItem);
            });
        }
    }
}
