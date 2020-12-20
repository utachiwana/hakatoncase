package com.utachiwana.athena.ui.menu.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.utachiwana.athena.DataGenerator;
import com.utachiwana.athena.R;
import com.utachiwana.athena.ui.logic.FreeTimeAdapter;
import com.utachiwana.athena.ui.logic.FreeTimeClickedListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements FreeTimeClickedListener {

    DialogTopicFragment dialogTopicFragment;
    TextView textFirstName;
    TextView textLastName;
    TextView textEmail;
    Button btnSelectTopic;
    Button btnSelectTime;
    Button btnRole;
    RecyclerView mRecycler;
    FreeTimeAdapter mAdapter;
    ImageView noFreeTime;
    int myRole;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        textFirstName = view.findViewById(R.id.tvProfileFirstName);
        textLastName = view.findViewById(R.id.tvProfileLastName);
        textEmail = view.findViewById(R.id.tvProfileEmail);
        btnSelectTopic = (Button) view.findViewById(R.id.btnProfileSelectTopic);
        btnSelectTime = (Button) view.findViewById(R.id.btnProfileSelectTime);
        btnRole = (Button) view.findViewById(R.id.btnProfileRole);
        mRecycler = view.findViewById(R.id.profile_time_recycler);
        noFreeTime = view.findViewById(R.id.no_free_time_image);
        mAdapter = new FreeTimeAdapter(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // TODO: 19.12.2020 инициализация данных пользователя
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(getArguments().getString("data"), JsonObject.class);
        JsonObject userInfo = gson.fromJson(data, JsonObject.class);
        textFirstName.setText(userInfo.get("name").getAsString());
        textLastName.setText(userInfo.get("lastname").getAsString());
        textEmail.setText(userInfo.get("email").getAsString());
        btnRole.setText(userInfo.get("role").getAsString());
        myRole = -1;

        loadFreeTimes();

        btnSelectTopic.setOnClickListener(v -> {
            dialogTopicFragment = new DialogTopicFragment();
            dialogTopicFragment.show(requireActivity().getSupportFragmentManager(), "1");
        });

        btnRole.setOnClickListener(v -> {
            final String[] arrayRoles = getResources().getStringArray(R.array.arrayRoles);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Хотите сменить роль?")
                    // добавляем переключатели
                    .setSingleChoiceItems(arrayRoles, 0/*// TODO: тут инициализируется роль */,
                            (dialog, item) -> {
                                //смена роли
                                //Toast.makeText(getActivity(), "Выбрана роль: " + arrayRoles[item], Toast.LENGTH_SHORT).show();
                            })
                    .setPositiveButton("OK", (dialog, id) -> {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        // TODO: 19.12.2020 смена роли

                        Toast.makeText(getActivity(), "Ваша роль", Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).create().show();
        });

        btnSelectTime.setOnClickListener(v -> {
            SelectTimeDialog dialog = new SelectTimeDialog(this);
            Bundle bundle = new Bundle();
            dialog.setArguments(bundle);
            dialog.show(requireActivity().getSupportFragmentManager(), null);
        });
    }


    @Override
    public void onClick(int pos) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.confirmdelete)
                .setMessage(R.string.confirmdeletedescription)
                .setPositiveButton(R.string.remove, (dialogInterface, i) -> {
                    mAdapter.removeTime(pos);
                    Toast.makeText(getContext(), getResources().getString(R.string.successfully), Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.cancel())
                .create();
        dialog.show();
    }

    @Override
    public void allItemsRemoved() {
        noFreeTime.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.INVISIBLE);
    }

    @Override
    public void addFreeTime(String time) {
        mAdapter.addItem(time);
        noFreeTime.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadFreeTimes() {
        try {
            List<String> list = new ArrayList<>();
/*        for (JsonElement el : data.get("time").getAsJsonArray()) {
            list.add(el.getAsString());
        }*/
            Gson gson = new Gson();
            for (JsonElement el : gson.fromJson(DataGenerator.getTimeArray(), JsonArray.class)) {
                list.add(el.getAsString());
            }
            mAdapter.addData(list, true);
            mRecycler.setAdapter(mAdapter);
        } catch (Exception e){
            mRecycler.setVisibility(View.INVISIBLE);
            noFreeTime.setVisibility(View.VISIBLE);
        }
    }

}