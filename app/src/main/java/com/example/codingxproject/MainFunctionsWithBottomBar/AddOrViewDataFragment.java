package com.example.codingxproject.MainFunctionsWithBottomBar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_DBP;
import com.example.codingxproject.DataRecord.DataRecord_BloodPressure_SBP;
import com.example.codingxproject.DataReview;
import com.example.codingxproject.R;


public class AddOrViewDataFragment extends Fragment {
    public Button bAddRecord,bViewRecord;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_data,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bAddRecord=(Button)getActivity().findViewById(R.id.bAddRecord);
        bViewRecord=(Button)getActivity().findViewById(R.id.bViewRecord);
        View.OnClickListener listener=new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.bAddRecord){
                    intent=new Intent(getActivity(), DataRecord_BloodPressure_SBP.class);
                }else if(view.getId()==R.id.bViewRecord){
                    intent=new Intent(getActivity(), DataReview.class);
                }
                startActivity(intent);
            }
        };
        bAddRecord.setOnClickListener(listener);
        bViewRecord.setOnClickListener(listener);
    }
}
