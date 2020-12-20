package com.utachiwana.athena.ui.menu.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utachiwana.athena.R;
import com.utachiwana.athena.ui.logic.DateAdapter;
import com.utachiwana.athena.ui.logic.TimeAdapter;
import com.utachiwana.athena.ui.logic.TimeSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SignUpDialog extends DialogFragment implements TimeSelectedListener {

    Spinner mSpinner;
    TimeAdapter mAdapter;
    RecyclerView mRecycler;
    Button mSignUpBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_signup, container, false);
        mSpinner = view.findViewById(R.id.date_spinner);
        mRecycler = view.findViewById(R.id.time_recycler);
        mSignUpBtn = view.findViewById(R.id.signup_button);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mSignUpBtn.setEnabled(false);

        String[] dates = getArguments().getStringArray("time");
        DateAdapter adapter = new DateAdapter(getContext(), R.layout.item_spinner, R.id.date_spinner, dates);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        double duration = Double.parseDouble(getArguments().getString("duration").split(" ")[0]);
        int startHour = Integer.parseInt(dates[0].split(" ")[1].split(":")[0]);
        int endHour = Integer.parseInt(dates[0].split(" ")[3].split(":")[0]);
        Calendar cal = Calendar.getInstance();
        List<String> list = new ArrayList<>();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        cal.set(Calendar.MINUTE, 0);
        for (int i = startHour; i <= endHour - duration; i++) {
            cal.set(Calendar.HOUR_OF_DAY, i);
            String str = f.format(cal.getTime());
            cal.set(Calendar.HOUR_OF_DAY, (int) (i + Math.floor(duration)));
            str += " - " + f.format(cal.getTime());
            list.add(str);
        }
        mAdapter = new TimeAdapter(list, this);
        mRecycler.setAdapter(mAdapter);

        mSignUpBtn.setOnClickListener(v -> {
            //todo отправка создания запроса репетитору на сервер
            dismiss();
            Toast.makeText(getContext(), getResources().getString(R.string.sentrequest), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onTimeSelected() {
        mSignUpBtn.setEnabled(true);
    }

    @Override
    public void onTimeUnselected() {
        mSignUpBtn.setEnabled(false);
    }
}
