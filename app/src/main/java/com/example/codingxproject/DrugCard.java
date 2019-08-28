package com.example.codingxproject;

public class DrugCard {

    private int id;
    private int image_drugBag;
    private int image_pills;
    private int drugDescriptions;
    private String detailInfo;

    public DrugCard(int id, int image_drugBag, int image_pills, int drugDescriptions,String detailInfo) {
        super();
        this.id = id;
        this.image_drugBag = image_drugBag;
        this.image_pills = image_pills;
        this.drugDescriptions = drugDescriptions;
        this.detailInfo=detailInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setimage_drugbag(int image_drugBag) {
        this.image_drugBag = image_drugBag;
    }

    public int getImage_drugBag() {
        return this.image_drugBag;
    }

    public void setImage_pills() {
        this.image_pills = image_pills;
    }

    public int getImage_pills() {
        return this.image_pills;
    }

    public void setDrugDescriptions(int drugDescriptions) {
        this.drugDescriptions = drugDescriptions;
    }

    public int getDrugDescriptions() {
        return this.drugDescriptions;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public String getDetailInfo() {
        return detailInfo;
    }
}