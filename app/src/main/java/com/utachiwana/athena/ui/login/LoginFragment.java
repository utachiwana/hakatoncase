package com.utachiwana.athena.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ServiceCompat;
import androidx.fragment.app.Fragment;

import com.utachiwana.athena.AthenaApp;
import com.utachiwana.athena.R;

import java.util.Objects;


public class LoginFragment extends Fragment {

    public static final String TAG = "TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = view.findViewById(R.id.btnLogin);
        TextView logupButton = view.findViewById(R.id.btnLogup);
        TextView repassButton = view.findViewById(R.id.btnRepass);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkField()) {

                } else {
                    if (!checkUser()) {

                    } else {
                        // TODO: 19.12.2020 входим в приложение
                        //putExtrta
                        startActivity(new Intent(getActivity(), AthenaApp.class));
                    }
                }
            }
        });

        loginButton.setClickable(true);
        logupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogupFragment logupFragment= new LogupFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, logupFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        repassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepassFragment repassFragment= new RepassFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, repassFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    private boolean checkField() {
        // TODO: 19.12.2020 проверка корректности полей
        return false;
    }

    private boolean checkUser() {
        // TODO: 19.12.2020 проверка пользователя в базе
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}