package com.gma.medicalassistant.model;

public class PlanItem {
    private String mTitle;
    private String mContent;
    private Boolean mPurchaseState;

    public PlanItem(String t, String c, Boolean ps) {
        this.mTitle = t;
        this.mContent = c;
        this.mPurchaseState = ps;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String t) {
        this.mTitle = t;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String c) { this.mContent = c; }

    public Boolean getPurchaseState() {
        return mPurchaseState;
    }

    public void setPurchaseState(Boolean ps) {
        this.mPurchaseState = ps;
    }
}
