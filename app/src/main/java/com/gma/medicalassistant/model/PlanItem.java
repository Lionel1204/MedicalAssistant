package com.gma.medicalassistant.model;

public class PlanItem {
    private String mText;
    private Boolean mPurchaseState;

    public PlanItem(String t, Boolean ps) {
        this.mText = t;
        this.mPurchaseState = ps;
    }

    public String getItemText() {
        return mText;
    }

    public void setItemText(String t) {
        this.mText = t;
    }

    public Boolean getPurchaseState() {
        return mPurchaseState;
    }

    public void setPurchaseState(Boolean ps) {
        this.mPurchaseState = ps;
    }
}
