package com.example.codingxproject.Remind;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingxproject.R;

import java.util.ArrayList;

class RemindTakeMedCardAdapter extends RecyclerView.Adapter<RemindTakeMedCardAdapter.ViewHolder>{
    private  Context context;
    private  ArrayList<RemindTakeMedCard> takeMedList;

    public RemindTakeMedCardAdapter(Context context, ArrayList<RemindTakeMedCard> takeMedList) {
        this.context=context;
        this.takeMedList=takeMedList;
    }

    @NonNull
    @Override
    public RemindTakeMedCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.remind_take_med_card, parent, false);
        view.getLayoutParams().height = parent.getHeight()/2;
        view.getLayoutParams().width = parent.getWidth()/2;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RemindTakeMedCardAdapter.ViewHolder holder, int position) {
        final RemindTakeMedCard takeMedCard=takeMedList.get(position);
        holder.imageId.setImageResource(takeMedCard.getDrugImg());
        holder.drugName.setText(takeMedCard.getDrugName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(takeMedCard.getDrugClicked()==true){
//                    findViewById(takeMedCard.getDrugImg()).setColorFilter(R.color.transparentGray);
                    holder.imageId.setColorFilter(R.color.transparentGray);
                    takeMedCard.setDrugClicked(false);
                }else{
//                    (takeMedCard.getDrugImg()).clearColorFilter(R.color.transparentGray);
                    holder.imageId.clearColorFilter();
                    takeMedCard.setDrugClicked(true);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {


                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return takeMedList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageId;
        TextView drugName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageId=(ImageView)itemView.findViewById(R.id.takeMedImgId);
            drugName=(TextView)itemView.findViewById(R.id.takeMedNameId);
        }
    }
}
