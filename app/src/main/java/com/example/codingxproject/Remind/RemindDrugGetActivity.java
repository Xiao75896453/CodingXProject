package com.example.codingxproject.Remind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codingxproject.MainFunctionsWithBottomBar.HomePageActivity;
import com.example.codingxproject.R;

import java.util.Calendar;

public class RemindDrugGetActivity extends AppCompatActivity {

    Button bRemingAgain,bConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_drug_get);

        bRemingAgain=(Button)findViewById(R.id.bRemindAgain);
        bConfirm=(Button)findViewById(R.id.bDrugGetConfirm);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.bRemindAgain){

                }else if(view.getId()==R.id.bDrugGetConfirm){


                }
                Intent intent=new Intent(RemindDrugGetActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        };

        bRemingAgain.setOnClickListener(listener);
        bConfirm.setOnClickListener(listener);
    }


}
