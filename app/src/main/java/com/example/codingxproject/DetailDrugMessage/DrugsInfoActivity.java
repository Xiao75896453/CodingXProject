package com.example.codingxproject.DetailDrugMessage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.codingxproject.R;

import java.util.ArrayList;

//implements DrugListAdapter.OnCardListener
public class DrugsInfoActivity extends AppCompatActivity {

    public ArrayList<DrugCard> drugList=new ArrayList<DrugCard>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_drugs_info);

        drugList.add(new DrugCard(1,R.drawable.drug1_med,R.drawable.drug1_case,R.string.drug1_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=468854&SearchItem1=%B3B%A4%E8%B6%B0"));//http://140.116.253.135/NewHomePage/massrefer_handout%20forms.asp?D_id=583325%26SearchItem1=%BD%C3%B1%D0%B3%E6%B
        drugList.add(new DrugCard(2,R.drawable.drug2_med,R.drawable.drug2_case,R.string.drug2_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=403827&SearchItem1=%B3B%A4%E8%B6%B0"));
        drugList.add(new DrugCard(3,R.drawable.drug3_med,R.drawable.drug3_case,R.string.drug3_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=783921&SearchItem1=%B3B%A4%E8%B6%B0"));
        drugList.add(new DrugCard(4,R.drawable.drug4_med,R.drawable.drug4_case,R.string.drug4_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=583129&SearchItem1=%B3B%A4%E8%B6%B0"));
        drugList.add(new DrugCard(5,R.drawable.drug5_med,R.drawable.drug5_case,R.string.drug5_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=020125&SearchItem1=%B3B%A4%E8%B6%B0"));
        drugList.add(new DrugCard(6,R.drawable.drug6_med,R.drawable.drug6_case,R.string.drug6_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=721723&SearchItem1=%B3B%A4%E8%B6%B0"));
        drugList.add(new DrugCard(7,R.drawable.drug7_med,R.drawable.drug7_case,R.string.drug7_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=542924&SearchItem1=%B3B%A4%E8%B6%B0"));
        drugList.add(new DrugCard(8,R.drawable.drug8_med,R.drawable.drug8_case,R.string.drug8_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=286951&SearchItem1=%B3B%A4%E8%B6%B0"));
        drugList.add(new DrugCard(9,R.drawable.drug9_med,R.drawable.drug9_case,R.string.drug9_des,"http://140.116.253.135/NewHomePage/massrefer_prescription.asp?D_id=301927&SearchItem1=%B3B%A4%E8%B6%B0"));

        RecyclerView recyclerDrugList=(RecyclerView) findViewById(R.id.drugList);
        recyclerDrugList.setLayoutManager(new LinearLayoutManager(DrugsInfoActivity.this));
        recyclerDrugList.setAdapter(new DrugListAdapter(this,drugList));

    }

}
