package com.utachiwana.athena.ui.menu.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.utachiwana.athena.R;

public class ProfileFragment extends Fragment {

    DialogTopicFragment dialogTopicFragment;
    TextView textFirstName;
    TextView textLastName;
    TextView textEmail;
    Button btnSelectTopic;
    Button btnSelectTime;
    Button btnRole;
    ListView listTime;
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
        listTime = (ListView) view.findViewById(R.id.lvProfileListTime);

        // TODO: 19.12.2020 инициализация данных пользователя
        textFirstName.setText("Вася");
        textLastName.setText("Пупкин");
        textEmail.setText("vasa@ya.ru");
        myRole = -1;

        btnSelectTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTopicFragment = new DialogTopicFragment();
                dialogTopicFragment.show(requireActivity().getSupportFragmentManager(), "1");
            }
        });

        btnRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] arrayRoles = getResources().getStringArray(R.array.arrayRoles);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Хотите сменить роль?")
                        // добавляем переключатели
                        .setSingleChoiceItems(arrayRoles, 0/*// TODO: тут инициализируется роль */,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int item) {
                                        //смена роли
                                        //Toast.makeText(getActivity(), "Выбрана роль: " + arrayRoles[item], Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                                // TODO: 19.12.2020 смена роли

                                Toast.makeText(getActivity(), "Ваша роль", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });

        return view;
    }


}