package com.example.codingxproject.Remind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codingxproject.MainFunctionsWithBottomBar.HomePageActivity;
import com.example.codingxproject.R;

public class RemindRecordActivity extends AppCompatActivity {

    Button bRemingAgain,bConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_record);

        bRemingAgain=(Button)findViewById(R.id.bRemindAgain);
        bConfirm=(Button)findViewById(R.id.bFillRecord);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.bRemindAgain){

                }else if(view.getId()==R.id.bFillRecord){

                }
                Intent intent=new Intent(RemindRecordActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        };

        bRemingAgain.setOnClickListener(listener);
        bConfirm.setOnClickListener(listener);
    }


}
