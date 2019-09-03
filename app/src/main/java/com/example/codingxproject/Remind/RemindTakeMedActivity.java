package com.example.codingxproject.Remind;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.icu.lang.UCharacter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.codingxproject.R;

import java.util.ArrayList;

public class RemindTakeMedActivity extends AppCompatActivity {

    ImageButton drugImg1;
    ImageView drugImg2,drugImg3,drugImg4;
    ArrayList<RemindTakeMedCard> takeMedList=new ArrayList<RemindTakeMedCard>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_take_medicine);
        setTakeMedList(takeMedList);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.takeMedRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(new RemindTakeMedCardAdapter(this,takeMedList));
//        drugImg1=(ImageButton) findViewById(R.id.ivDrugImage1);
//        drugImg2=(ImageView)findViewById(R.id.ivDrugImage2);
//        drugImg3=(ImageView)findViewById(R.id.ivDrugImage3);
//        drugImg4=(ImageView)findViewById(R.id.ivDrugImage4);
//        Boolean imgOneClicked=false;

//        ImageButton a;
//        drugImg1.setColorFilter(R.color.transparent);
//        drugImg2.setColorFilter(R.color.transparentGray);
//        drugImg2.clearColorFilter();
    }

    public void setTakeMedList(ArrayList<RemindTakeMedCard> takeMedList){
        takeMedList.add(new RemindTakeMedCard(1,R.mipmap.ic_launcher,R.string.drug_Name,false));
        takeMedList.add(new RemindTakeMedCard(2,R.mipmap.ic_launcher,R.string.drug_Name,false));
        takeMedList.add(new RemindTakeMedCard(3,R.mipmap.ic_launcher,R.string.drug_Name,false));
        takeMedList.add(new RemindTakeMedCard(4,R.mipmap.ic_launcher,R.string.drug_Name,false));
        takeMedList.add(new RemindTakeMedCard(5,R.mipmap.ic_launcher,R.string.drug_Name,false));
        takeMedList.add(new RemindTakeMedCard(6,R.mipmap.ic_launcher,R.string.drug_Name,false));
        takeMedList.add(new RemindTakeMedCard(5,R.mipmap.ic_launcher,R.string.drug_Name,false));
        takeMedList.add(new RemindTakeMedCard(6,R.mipmap.ic_launcher,R.string.drug_Name,false));
    }

}
