package com.utachiwana.athena.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.utachiwana.athena.R;

import java.util.Objects;


public class RepassFragment extends Fragment {

    public RepassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repass, container, false);
        Button buttonRepass = view.findViewById(R.id.btnRepassRepass);
        buttonRepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkEmail()) {
                    Toast.makeText(requireActivity().getApplicationContext(), "Почта не найдена", Toast.LENGTH_SHORT ).show();
                } else {
                    LoginFragment loginFragment = new LoginFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, loginFragment)
                            .addToBackStack(null)
                            .commit();
                    Toast.makeText(requireActivity().getApplicationContext(), "Письмо с новым паролем выслано на почту", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private boolean checkEmail() {
        // TODO: 19.12.2020 проверка существования почты
        return false;
    }
}