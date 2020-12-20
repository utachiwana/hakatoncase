package com.utachiwana.athena.ui.menu.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
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
import com.utachiwana.athena.data.Prefs;
import com.utachiwana.athena.network.NetworkUtils;
import com.utachiwana.athena.ui.logic.DateAdapter;
import com.utachiwana.athena.ui.logic.TimeAdapter;
import com.utachiwana.athena.ui.logic.TimeSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTimeDialog extends DialogFragment {


    Spinner mSpinner;
    /*TimeAdapter mAdapter;
    RecyclerView mRecycler;*/
    Button mSignUpBtn;
    NumberPicker pickerHH1;
    NumberPicker pickerMM1;
    NumberPicker pickerHH2;
    NumberPicker pickerMM2;
    DateAdapter dateAdapter;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = i + "";
        }
        String[] min = new String[]{"0", "30"};

        pickerHH1.setMaxValue(hours.length-1);
        pickerHH2.setMaxValue(hours.length-1);
        pickerMM1.setMaxValue(min.length-1);
        pickerMM2.setMaxValue(min.length-1);
        pickerHH1.setDisplayedValues(hours);
        pickerMM1.setDisplayedValues(min);
        pickerHH2.setDisplayedValues(hours);
        pickerMM2.setDisplayedValues(min);


        String[] dates = getResources().getStringArray(R.array.dayWeek);
        dateAdapter = new DateAdapter(getContext(), R.layout.item_spinner, R.id.date_spinner, dates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dateAdapter);

        mSignUpBtn.setOnClickListener(v -> {
         /*   int valuePickerHH1 = pickerHH1.getValue();
            int valuePickerMM1 = pickerMM1.getValue();
            int valuePickerHH2 = pickerHH2.getValue();
            int valuePickerMM2 = pickerMM2.getValue();*/

            String startTime = pickerHH1.getValue() + ":" + pickerMM1.getValue() + "-";
            String startEnd = pickerHH2.getValue() + ":" + pickerMM2.getValue() + "-";

            NetworkUtils.getApi().newFreeTime(Prefs.getToken(), dateAdapter.getItem(mSpinner.getSelectedItemPosition()), startTime, startEnd).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Успешно", Toast.LENGTH_SHORT).show();
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), getResources().getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getContext(), getResources().getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
                }
            });
        });

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
