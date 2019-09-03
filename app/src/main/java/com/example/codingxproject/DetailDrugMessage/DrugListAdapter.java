package com.example.codingxproject.DetailDrugMessage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingxproject.R;

import java.util.ArrayList;

class DrugListAdapter extends RecyclerView.Adapter<DrugListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DrugCard> drugCardArrayList = new ArrayList<DrugCard>();


    public DrugListAdapter(Context context, ArrayList<DrugCard> drugCardArrayList) {
        this.context = context;
        this.drugCardArrayList = drugCardArrayList;
    }

    @NonNull
    @Override
    public DrugListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_drug_card, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DrugListAdapter.ViewHolder holder, int position) {
        final DrugCard drugCard = drugCardArrayList.get(position);
        holder.drugBag.setImageResource(drugCard.getImage_drugBag());
        holder.pills.setImageResource(drugCard.getImage_pills());
        holder.descriptions.setText(drugCard.getDrugDescriptions());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast toast = Toast.makeText(context, "213", Toast.LENGTH_SHORT);
//                toast.show();
                Intent toDetail = new Intent(context, DetailDrugInfoActivity.class);
                toDetail.putExtra("destinSite",drugCard.getDetailInfo());
                context.startActivity(toDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drugCardArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView drugBag;
        ImageView pills;
        TextView descriptions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drugBag = (ImageView) itemView.findViewById(R.id.drugBag);
            pills = (ImageView) itemView.findViewById(R.id.drugPills);
            descriptions = (TextView) itemView.findViewById(R.id.drugDescriptions);

        }
    }


}