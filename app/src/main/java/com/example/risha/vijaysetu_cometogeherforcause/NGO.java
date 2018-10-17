package com.example.risha.vijaysetu_cometogeherforcause;

public class NGO {
    String NGOId;
    String NGOName;

    public String getNGOCategory() {
        return NGOCategory;
    }

    public void setNGOCategory(String NGOCategory) {
        this.NGOCategory = NGOCategory;
    }

    String NGOCategory;
    String NGODescription;
    String NGOPhone;

    public NGO() {
    }

    public NGO(String NGOId, String NGOName,String NGOCategory, String NGODescription, String NGOPhone) {
        this.NGOId = NGOId;
        this.NGOName = NGOName;
        this.NGOCategory=NGOCategory;
        this.NGODescription = NGODescription;
        this.NGOPhone = NGOPhone;
    }

    public String getNGOId() {
        return NGOId;
    }

    public void setNGOId(String NGOId) {
        this.NGOId = NGOId;
    }

    public String getNGOName() {
        return NGOName;
    }

    public void setNGOName(String NGOName) {
        this.NGOName = NGOName;
    }

    public String getNGODescription() {
        return NGODescription;
    }

    public void setNGODescription(String NGODescription) {
        this.NGODescription = NGODescription;
    }

    public String getNGOPhone() {
        return NGOPhone;
    }

    public void setNGOPhone(String NGOPhone) {
        this.NGOPhone = NGOPhone;
    }
}
