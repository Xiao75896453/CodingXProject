package com.example.codingxproject.Remind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codingxproject.MainFunctionsWithBottomBar.HomePageActivity;
import com.example.codingxproject.R;
import java.util.ArrayList;

public class RemindTakeMedActivity extends AppCompatActivity {

    Button bRemingAgain,bConfirm;
    ArrayList<RemindTakeMedCard> takeMedList=new ArrayList<RemindTakeMedCard>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_take_medicine);
        bRemingAgain=(Button)findViewById(R.id.bRemindAgain);
        bConfirm=(Button)findViewById(R.id.bTakeMedConfirm);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.takeMedRecyclerView);
        setTakeMedList(takeMedList);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.bRemindAgain){

                }else if(view.getId()==R.id.bTakeMedConfirm){

                }
                Intent intent=new Intent(RemindTakeMedActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        };
        bRemingAgain.setOnClickListener(listener);
        bConfirm.setOnClickListener(listener);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(new RemindTakeMedCardAdapter(this,takeMedList));
    }



    public void setTakeMedList(ArrayList<RemindTakeMedCard> takeMedList){
        takeMedList.add(new RemindTakeMedCard(1,R.drawable.drug1_case,R.string.drug1_name,false));
        takeMedList.add(new RemindTakeMedCard(2,R.drawable.drug2_case,R.string.drug2_name,false));
        takeMedList.add(new RemindTakeMedCard(3,R.drawable.drug3_case,R.string.drug3_name,false));
        takeMedList.add(new RemindTakeMedCard(4,R.drawable.drug4_case,R.string.drug4_name,false));
        takeMedList.add(new RemindTakeMedCard(5,R.drawable.drug5_case,R.string.drug5_name,false));
//        takeMedList.add(new RemindTakeMedCard(6,R.drawable.drug6_case,R.string.drug6_name,false));

    }

}
