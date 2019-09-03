package com.example.codingxproject.Remind;

public class RemindTakeMedCard {

    private int id;
    private int drugImg;
    private int drugName;
    private Boolean drugClicked;

    public RemindTakeMedCard(int id, int drugImg,int drugName,Boolean drugClicked){
        super();
        this.id=id;
        this.drugImg=drugImg;
        this.drugName=drugName;
        this.drugClicked=drugClicked;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getDrugImg(){
        return this.drugImg;
    }

    public void setDrugImg(int drugImg){
        this.drugImg=drugImg;
    }

    public int getDrugName(){
        return this.drugName;
    }

    public void setDrugName(int drugName){
        this.drugName=drugName;
    }

    public Boolean getDrugClicked(){
        return this.drugClicked;
    }

    public void setDrugClicked(Boolean drugClicked){
        this.drugClicked=drugClicked;
    }
}
