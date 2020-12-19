package com.utachiwana.athena.ui.menu.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utachiwana.athena.R;

import java.util.List;

public class DialogTopicFragment extends DialogFragment {

    SubjectsAdapter subjectsAdapter;
    RecyclerView mRecycler;
    // TODO: 19.12.2020 подтянуть список выбранных
    List<Integer> listInt;

    Button btnCancel;
    Button btnAccept;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_topic, container, false);

        mRecycler = view.findViewById(R.id.recycler_dialog);
        subjectsAdapter = new SubjectsAdapter(listInt, getResources().getStringArray(R.array.subjects).length);
        mRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecycler.setAdapter(subjectsAdapter);

        btnCancel = view.findViewById(R.id.btnDialogCancel);
        btnAccept = view.findViewById(R.id.btnDialogAccept);

        btnCancel.setOnClickListener(v -> getDialog().cancel());

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 19.12.2020 отправить массив айдишников на сервер
            }
        });
        return view;
    }
}
