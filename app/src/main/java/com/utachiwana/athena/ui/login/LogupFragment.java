package com.utachiwana.athena.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.utachiwana.athena.R;
import com.utachiwana.athena.data.User;
import com.utachiwana.athena.network.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogupFragment extends Fragment {

    EditText mName, mLastName, mEmail, mPass, mPass2;
    ProgressBar progressBar;
    FrameLayout disableScreen;
    Button regBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logup, container, false);

        ActionBar actionBar = ((LoginActivity)getActivity()).getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mName = view.findViewById(R.id.etRegistFirstName);
        mLastName = view.findViewById(R.id.etRegistLastName);
        mEmail = view.findViewById(R.id.etRegistEmail);
        mPass = view.findViewById(R.id.etRegistPass);
        mPass2 = view.findViewById(R.id.etRegistPass2);
        regBtn = view.findViewById(R.id.btnRegistRegistration);
        progressBar = view.findViewById(R.id.progressBar);
        disableScreen = view.findViewById(R.id.disableScreen);
        return view;
    }

    void showLoading(boolean show){
        if (show){
            disableScreen.setClickable(true);
            disableScreen.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }else {
            disableScreen.setVisibility(View.GONE);
            disableScreen.setClickable(false);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        regBtn.setOnClickListener(v->{
            if (isCorrect()) {
                showLoading(true);
                User user = new User();
                user.setEmail(mEmail.getText().toString());
                user.setName(mName.getText().toString());
                user.setLastName(mLastName.getText().toString());
                user.setPassword(mPass.getText().toString());
                NetworkUtils.getApi().registration(user).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getContext(), getResources().getString(R.string.successfully), Toast.LENGTH_SHORT).show();
                            showLoading(false);
                            requireActivity().getSupportFragmentManager().popBackStack();
                            //TODO возврат к окну авторизации
                        }
                        else {
                            showLoading(false);
                            //todo ошибка запроса к серверу
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("______", "onFailure: " + t.getMessage());
                        Toast.makeText(getContext(), getResources().getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
                        showLoading(false);
                        //TODO ошибка запроса
                    }
                });
            } else {
                //todo вывод неправильно заполненных полей
            }
        });
    }

    private boolean isCorrect() {
        //TODO проверка полей на корректность
        return true;
    }



}