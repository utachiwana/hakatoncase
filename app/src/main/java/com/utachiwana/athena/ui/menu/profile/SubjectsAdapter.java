package com.utachiwana.athena.ui.menu.profile;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utachiwana.athena.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectsViewHolder> {


    List<Integer> listId = new ArrayList<Integer>();
    int lenghtArray;

    public SubjectsAdapter(List<Integer> listId, int lenghtArray) {
        if (listId != null){
            this.listId.addAll(listId);
        }
        this.lenghtArray = lenghtArray;
        Log.d("TAG", "SubjectsAdapter: lenghtArray: " + lenghtArray);
    }

    @NonNull
    @Override
    public SubjectsAdapter.SubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_topic, parent, false);
        return new SubjectsAdapter.SubjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsAdapter.SubjectsViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return lenghtArray;
    }

    public class SubjectsViewHolder extends RecyclerView.ViewHolder {

        CheckBox mCheckBox;

        public SubjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.cbItemDialog);
        }

        public void bind() {
//            mTime.setText(post.getTime());
            mCheckBox.setText(itemView.getResources().getStringArray(R.array.subjects)[getAdapterPosition()]);
            Log.d("TAG", "bind: " + itemView.getResources().getStringArray(R.array.subjects)[getAdapterPosition()]);
            mCheckBox.setChecked(listId.contains(getAdapterPosition()));
            mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked && listId.contains(getAdapterPosition())) {
                    listId.add(getAdapterPosition());
                } else {
                    listId.remove(getAdapterPosition());
                }
            });
        }
    }
}
