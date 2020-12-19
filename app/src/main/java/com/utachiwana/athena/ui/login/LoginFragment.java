package com.utachiwana.athena.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ServiceCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.utachiwana.athena.AthenaApp;
import com.utachiwana.athena.R;
import com.utachiwana.athena.network.NetworkUtils;
import com.utachiwana.athena.ui.menu.MenuActivity;

import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    EditText nameEt;
    EditText passEt;

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
        nameEt = view.findViewById(R.id.etAuthName);
        passEt = view.findViewById(R.id.etAuthPass);

        loginButton.setOnClickListener(new View.OnClickListener() {
            final String name = nameEt.getText().toString();
            final String pass = passEt.getText().toString();
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuActivity.class));
                //todo убрать
                //getActivity().finish();
                if (true) return;
                if (!checkField()) {

                } else {
                    NetworkUtils.getApi().authorization(name, pass).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()) {
                                // TODO: 19.12.2020 входим в приложение
                                //putExtrta
                                startActivity(new Intent(getActivity(), MenuActivity.class));
                                getActivity().finish();
                            } else {
                                // TODO: 19.12.2020 ошибка с сервера
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            // TODO: 19.12.2020 ошибка запроса
                        }
                    });
                }
            }
        });

        loginButton.setClickable(true);
        logupButton.setOnClickListener(v -> {
            LogupFragment logupFragment = new LogupFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, logupFragment)
                    .addToBackStack(null)
                    .commit();
        });
        repassButton.setOnClickListener(v -> {
            RepassFragment repassFragment = new RepassFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, repassFragment)
                    .addToBackStack(null)
                    .commit();
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