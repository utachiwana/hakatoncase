package com.utachiwana.athena.ui.menu.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

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

public class SelectTimeDialog extends DialogFragment {


    Spinner mSpinner;
    /*TimeAdapter mAdapter;
    RecyclerView mRecycler;*/
    Button mSignUpBtn;
    NumberPicker pickerHH1;
    NumberPicker pickerMM1;
    NumberPicker pickerHH2;
    NumberPicker pickerMM2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_time_profile, container, false);
        mSpinner = view.findViewById(R.id.date_spinner);
        /*mRecycler = view.findViewById(R.id.time_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));*/

        //***

        pickerHH1 = view.findViewById(R.id.hh1);
        pickerMM1 = view.findViewById(R.id.mm1);
        pickerHH2 = view.findViewById(R.id.hh2);
        pickerMM2 = view.findViewById(R.id.mm2);

        //***
        mSignUpBtn = view.findViewById(R.id.signup_button);

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valuePickerHH1 = pickerHH1.getValue();
                int valuePickerMM1 = pickerMM1.getValue();
                int valuePickerHH2 = pickerHH2.getValue();
                int valuePickerMM2 = pickerMM2.getValue();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        mSignUpBtn.setEnabled(false);
        pickerHH1.setMinValue(0);
        pickerHH1.setMaxValue(23);
        pickerMM1.setMinValue(0);
        pickerMM1.setMaxValue(59);

        pickerHH2.setMinValue(0);
        pickerHH2.setMaxValue(23);
        pickerMM2.setMinValue(0);
        pickerMM2.setMaxValue(59);

        String[] hours = getResources().getStringArray(R.array.hours);
        String[] min = new String[60];
        for (int i = 0; i < 60; i++) {
            min[i] = String.valueOf(i);
        }

        pickerHH1.setDisplayedValues(hours);
        pickerMM1.setDisplayedValues(min);
        pickerHH2.setDisplayedValues(hours);
        pickerMM2.setDisplayedValues(min);


        String[] dates = getResources().getStringArray(R.array.dayWeek);
        DateAdapter adapter = new DateAdapter(getContext(), R.layout.item_spinner, R.id.date_spinner, dates);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);



/*        double duration = Double.parseDouble(getArguments().getString("duration").split(" ")[0]);
        Integer startHour = Integer.parseInt(dates[0].split(" ")[1].split(":")[0]);
        Integer endHour = Integer.parseInt(dates[0].split(" ")[3].split(":")[0]);
        Calendar cal = Calendar.getInstance();
//        List<String> list = new ArrayList<>();
//        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        cal.set(Calendar.MINUTE, 0);
        for (int i = startHour; i <= endHour - duration; i++) {
            cal.set(Calendar.HOUR_OF_DAY, i);
            String str = f.format(cal.getTime());
            cal.set(Calendar.HOUR_OF_DAY, (int) (i + Math.floor(duration)));
            str+= " - " + f.format(cal.getTime());
            list.add(str);
        }
        mAdapter = new TimeAdapter(list, this);
        mRecycler.setAdapter(mAdapter);*/
    }


}
