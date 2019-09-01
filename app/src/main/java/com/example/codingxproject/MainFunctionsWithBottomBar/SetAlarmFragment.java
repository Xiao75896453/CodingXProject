package com.example.codingxproject.MainFunctionsWithBottomBar;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.codingxproject.DailyDrugInfoAndAlarmSetting.ItemAdapter;
import com.example.codingxproject.R;

public class SetAlarmFragment extends Fragment {
    ListView lvAlarmList;
    String[] items;

    String[] alarms;

    String[] descriptions;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_set_alarm,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = getResources();
        lvAlarmList = (ListView) getView().findViewById(R.id.lvAlarmList);
        items = res.getStringArray(R.array.items);

        alarms = res.getStringArray(R.array.alarms);
        descriptions = res.getStringArray(R.array.descriptions);

        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), items,alarms,descriptions);
        lvAlarmList.setAdapter(itemAdapter);
    }
}
