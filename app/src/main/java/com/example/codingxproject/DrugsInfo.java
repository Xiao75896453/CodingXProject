package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

//implements DrugListAdapter.OnCardListener
public class DrugsInfo extends AppCompatActivity {

    public ArrayList<DrugCard> drugList=new ArrayList<DrugCard>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_info);

        drugList.add(new DrugCard(1,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.string.drug_descriptions,"http://140.116.253.135/NewHomePage/massrefer_handout%20forms.asp?D_id=583325&SearchItem1=%BD%C3%B1%D0%B3%E6%B"));
        drugList.add(new DrugCard(2,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.string.drug_descriptions,"http://www.google.com"));
        drugList.add(new DrugCard(3,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.string.drug_descriptions,"https://developer.android.com/guide/webapps/webview#java"));
        drugList.add(new DrugCard(4,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.string.drug_descriptions,"https://spicyboyd.blogspot.com/2018/03/app-recyclerview-cardview.html"));
        drugList.add(new DrugCard(5,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.string.drug_descriptions,"https://medium.com/@stevesohcot/andriod-studio-webview-tutorial-4651701d7d1a"));
        drugList.add(new DrugCard(6,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.string.drug_descriptions,"https://medium.com/@evanhou/%E6%B7%BA%E8%AB%87android-recyclerview-375f9c0eea58"));

        RecyclerView recyclerDrugList=(RecyclerView) findViewById(R.id.drugList);
        recyclerDrugList.setLayoutManager(new LinearLayoutManager(DrugsInfo.this));
        recyclerDrugList.setAdapter(new DrugListAdapter(this,drugList));

    }

}
