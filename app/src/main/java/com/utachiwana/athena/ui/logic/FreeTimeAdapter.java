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

public class FreeTimeAdapter extends RecyclerView.Adapter<FreeTimeAdapter.FreeTimeViewHolder> {

    List<String> list = new ArrayList<>();
    FreeTimeClickedListener freeTimeListener;

    public FreeTimeAdapter(FreeTimeClickedListener onClickListener) {
        this.freeTimeListener = onClickListener;
    }

    public void addData(List<String> times, boolean clear) {
        if (clear) list.clear();
        list.addAll(times);
    }

    public void removeTime(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
        if (list.size() == 0) {
            freeTimeListener.allItemsRemoved();
        }
    }

    @NonNull
    @Override
    public FreeTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_recycler, parent, false);
        return new FreeTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreeTimeViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(String time) {
        list.add(time);
        notifyItemInserted(list.size() - 1);
    }

    public class FreeTimeViewHolder extends RecyclerView.ViewHolder {

        TextView mTime;

        public FreeTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            mTime = itemView.findViewById(R.id.item_time);
        }

        public void bind() {
            itemView.setOnClickListener(v -> freeTimeListener.onClick(getAdapterPosition()));
            mTime.setText(list.get(getAdapterPosition()));
        }
    }
}
